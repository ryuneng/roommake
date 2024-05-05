package com.roommake.user.service;

import com.roommake.dto.Criteria;
import com.roommake.dto.ListDto;
import com.roommake.dto.Pagination;
import com.roommake.user.dto.*;
import com.roommake.user.exception.AlreadyUsedEmailException;
import com.roommake.user.exception.EmailException;
import com.roommake.user.mapper.UserMapper;
import com.roommake.user.mapper.UserRoleMapper;
import com.roommake.user.vo.*;
import com.roommake.utils.S3Uploader;
import com.roommake.utils.UniqueRecommendCodeUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;  // 비밀번호 암호화
    private final JavaMailSender mailSender;        // 이메일 발송
    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;
    private final S3Uploader s3Uploader;

    private Map<String, UserService.VerificationDetails> verifyCodes = new ConcurrentHashMap<>();

    // 닉네임 중복 확인
    public boolean isNicknameUnique(String nickname) {
        User foundUser = userMapper.getUserByNickname(nickname);
        return foundUser == null;
    }

    // 모든 유저 조회
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    /**
     * 조건에 맞는 유저 목록 조회(페이징, 검색, 정렬, 필터링 조건 추가 가능)
     *
     * @param criteria 검색할 조건
     * @return ListDto<USer>로 조건에 맞게 반환된 유저 목록
     */
    public ListDto<User> getUsers(Criteria criteria) {
        int totalRows = userMapper.getTotalRows(criteria);
        Pagination pagination = new Pagination(criteria.getPage(), totalRows, criteria.getRows());

        criteria.setBegin(pagination.getBegin());
        criteria.setEnd(pagination.getEnd());

        List<User> userList = userMapper.getUsers(criteria);
        ListDto<User> dto = new ListDto<>(userList, pagination);
        return dto;
    }

    // 이메일로 유저 조회
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    // 유저 등록
    @Transactional
    public User createUser(UserSignupForm form) {
        String email = form.getEmail1() + "@" + form.getEmail2();
        User foundUser = userMapper.getUserByEmail(email);
        if (foundUser != null) {
            throw new AlreadyUsedEmailException("[" + email + "]는 이미 사용중인 이메일입니다.");
        }

        // 랜덤 추천인 코드 생성 및 중복 체크
        String UniqueRecommendCode;
        do {
            UniqueRecommendCode = UniqueRecommendCodeUtils.createUniqueRecommendCode();
        } while (userMapper.existRecommendCode(UniqueRecommendCode)); // 중복된 코드가 있으면 다시 생성

        // User 객체 생성
        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(form.getPassword()))
                .nickname(form.getNickname())
                .uniqueRecommendCode(UniqueRecommendCode)
                .optionRecommendCode(form.getOptionRecommendCode())
                .build();

        userMapper.createUser(user);

        // UserRole 객체 생성
        UserRole userRole = UserRole.builder()
                .user(user)
                .name("USER")
                .build();

        userRoleMapper.createUserRole(userRole);

        // 추천인 조회
        User recommendUser = userMapper.getByRecommendCode(form.getOptionRecommendCode());

        // 적립일로부터 1년 후의 날짜를 계산
        LocalDateTime plusExpireDate = LocalDateTime.now().plusYears(1);

        // LocalDateTime을 Date로 변환
        Date expireDate = Date.from(plusExpireDate.atZone(ZoneId.systemDefault()).toInstant());

        // 추천인에게 포인트 적립
        if (recommendUser != null) {
            PlusPointHistory existMemberPlus = new PlusPointHistory();
            existMemberPlus.setUser(recommendUser);
            existMemberPlus.setAmount(500);
            existMemberPlus.setExpireDate(expireDate);                                 // 만료일 설정
            existMemberPlus.setPointType(PointType.getPointType(3));                    // 포인트 유형 설정
            userMapper.addPlusPointForExistUser(existMemberPlus);                            // 추천인 포인트 적립
            userMapper.modifyUserPoint(recommendUser.getId(), existMemberPlus.getAmount());  // 추천인 포인트 업데이트
        }

        // 신규 회원에게 포인트 적립
        PlusPointHistory newMemberPlus = new PlusPointHistory();
        newMemberPlus.setUser(user);
        newMemberPlus.setAmount(1000);
        newMemberPlus.setExpireDate(expireDate);                                           // 만료일 설정
        newMemberPlus.setPointType(PointType.getPointType(2));                             // 포인트 유형 설정
        userMapper.addPlusPointForNewUser(newMemberPlus);                                       // 신규 회원 포인트 적립
        userMapper.modifyNewUserPoint(user.getId(), newMemberPlus.getAmount());        // 신규회원 포인트 업데이트

        return user;
    }

    /**
     * 주어진 이메일 주소로 인증 코드를 발송
     *
     * @param toEmail 인증 코드를 받을 이메일 주소
     * @throws EmailException 이메일 발송 실패시 예외를 발생
     */
    public void sendVerifyCode(String toEmail) throws EmailException {
        String code = generateVerificationCode();
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("hyunji9886@knou.ac.kr");
            helper.setTo(toEmail);
            helper.setSubject("[룸메이크] 인증코드 안내");

            String content = loadHtmlTemplate(code);
            helper.setText(content, true);

            mailSender.send(message);

            LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(3);
            verifyCodes.put(toEmail, new UserService.VerificationDetails(code, expirationTime));
        } catch (MessagingException e) {
            throw new EmailException("이메일 서비스 중 오류 발생", e);
        }
    }

    /**
     * 이메일 주소와 인증 코드를 검증
     *
     * @param email 검증할 이메일 주소
     * @param code  검증할 인증 코드
     * @return boolean 코드가 유효하면 true,아니면 false 반환
     */
    public boolean verifyEmail(String email, String code) {
        UserService.VerificationDetails details = verifyCodes.get(email);
        return details != null && details.getCode().equals(code) &&
                LocalDateTime.now().isBefore(details.getExpirationTime());
    }

    /**
     * 6자리 랜덤 숫자 인증 코드를 생성
     *
     * @return String 생성된 인증 코드
     */
    private String generateVerificationCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    /**
     * HTML 이메일 템플릿을 로드하고 인증 코드를 삽입
     *
     * @param code 삽입할 인증 코드
     * @return String 완성된 HTML 문자열
     */
    private String loadHtmlTemplate(String code) throws EmailException {
        ClassPathResource resource = new ClassPathResource("templates/user/verify-email.html");
        String htmlTemplate;
        try {
            htmlTemplate = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new EmailException("이메일 템플릿을 로드하는 도중 오류가 발생했습니다.", e);
        }
        return htmlTemplate.replace("${code}", code);
    }

    /**
     * 인증 코드와 만료 시간을 관리하기 위한 내부 클래스
     */
    private static class VerificationDetails {
        private String code;
        private LocalDateTime expirationTime;

        public VerificationDetails(String code, LocalDateTime expirationTime) {
            this.code = code;
            this.expirationTime = expirationTime;
        }

        public String getCode() {
            return code;
        }

        public LocalDateTime getExpirationTime() {
            return expirationTime;
        }
    }

    // 모든 약관 조회
    public List<Term> getAllTerms() {
        return userMapper.getAllTerms();
    }

    // 유저 약관 동의 저장
    public void agreeToTerms(TermAgreement termAgreement) {
        userMapper.createTermAgreement(termAgreement);
    }

    // 약관 ID로 약관 정보를 조회하는 메서드
    public Term getTermById(int id) {
        return userMapper.getTermById(id);
    }

    // 입력받은 추천인 코드가 유효한지 조회
    public boolean checkRecommendCodeExists(String recommendCode) {
        return userMapper.existRecommendCode(recommendCode);
    }

    // 이메일 중복 검사
    public boolean isEmailAvailable(String email) {
        return userMapper.ExistEmail(email);
    }

    @Transactional(rollbackFor = Exception.class)
    public void modifyUserSettings(UserSettingForm form, String username) throws Exception {
        User existingUser = userMapper.getUserByEmail(username);
        boolean isChanged = false; // 변경 감지 플래그

        // 이미지 처리
        MultipartFile image = form.getImage();
        if (!image.isEmpty()) {
            String imageUrl = s3Uploader.saveFile(image);
            if (!imageUrl.equals(existingUser.getProfilePhoto())) {
                existingUser.setProfilePhoto(imageUrl);
                isChanged = true;
            }
        } else {
            // 이미지가 비어 있는 경우 사용자 설정 폼에서 이미지 URL을 비어 있는 문자열로 설정
            form.setProfilePhotoUrl("");
        }

        // 다른 필드 변경 확인 및 업데이트
        if (form.getNickname() != null) {
            existingUser.setNickname(form.getNickname());
            isChanged = true;
        }
        if (form.getEmail() != null) {
            existingUser.setEmail(form.getEmail());
            isChanged = true;
        }
        if (form.getBirthday() != null) {
            existingUser.setBirthDate(form.getBirthday());
            isChanged = true;
        }
        if (form.getIntroduction() != null) {
            existingUser.setIntroduction(form.getIntroduction());
            isChanged = true;
        }
        if (form.getSns() != null) {
            existingUser.setSns(form.getSns());
            isChanged = true;
        }

        // 변경이 감지된 경우에만 데이터베이스 업데이트
        if (isChanged) {
            userMapper.modifyUser(existingUser);
        }
    }

    // 회원 정보 수정
    public void modifyUser(User user) {
        userMapper.modifyUser(user);
    }

    // 모든 스크랩 조회
    public List<AllScrap> getAllScraps(int userId) {
        return userMapper.getAllScraps(userId);
    }

    /*
    유저의 모든 스크랩 폴더명 조회
    public List<String> getScrapFolderName(int userId) {
        return userMapper.getScrapFolderName(userId);
    }
    */

    // 스크랩 폴더 조회
    public List<AllScrap> getScrapFolders(int id) {
        return userMapper.getScrapFolders(id);
    }

    // 유저의 모든 상품 스크랩 조회
    public List<UserProductScrap> getProductScraps(int id) {
        return userMapper.getProductScraps(id);
    }

    // 유저의 모든 커뮤니티 스크랩 조회
    public List<UserCommScrap> getCommunityScraps(int userId) {
        return userMapper.getCommunityScraps(userId);
    }

    @Transactional
    public void deleteAndMoveScrapFolder(int userId, int folderId) {

        // 상품 스크랩을 기본 폴더로 이동
        userMapper.modifyProductScrapToDefaultFolder(userId, folderId);

        // 커뮤니티 스크랩을 기본 폴더로 이동
        userMapper.modifyCommunityScrapToDefaultFolder(userId, folderId);

        // 스크랩 폴더 삭제
        userMapper.deleteScrapFolder(userId, folderId);
    }

    // 폴더별 유저의 모든 스크랩 조회
    public List<AllScrap> getAllScrapsByFolderId(int userId, int folderId) {
        return userMapper.getAllScrapsByFolderId(userId, folderId);
    }

    // 특정 아이템을 다른 폴더로 이동
    public void modifyScrapItemToFolder(int userId, int itemId, int targetFolderId, String type) {
        userMapper.modifyScrapItemToFolder(itemId, userId, targetFolderId, type);
    }

    // 특정 아이템을 삭제
    public void deleteScrapItem(int userId, int itemId, String type) {
        userMapper.deleteScrapItem(itemId, userId, type);
    }

    // 스크랩 폴더 이름 및 설명 수정
    public void modifyScrapFolder(int folderId, int userId, String folderName, String folderDescription) {
        Map<String, Object> params = new HashMap<>();
        params.put("folderId", folderId);
        params.put("userId", userId);
        params.put("folderName", folderName);
        params.put("folderDescription", folderDescription);
        userMapper.modifyScrapFolder(params);
    }

    // 새로운 스크랩 폴더 추가
    public void addScrapFolder(int userId, String folderName, String folderDescription) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("folderName", folderName);
        params.put("folderDescription", folderDescription);
        userMapper.addScrapFolder(params);
    }
}

