package com.roommake.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 마이페이지 설정 입력폼
 */
@Getter
@Setter
@ToString
public class UserSettingForm {

    private String profilePhotoUrl;         // 기존 사용자 프로필 사진 URL
    private MultipartFile image;     // 파일 업로드를 위한 필드

    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    @Size(min = 2, max = 20, message = "닉네임은 최소 2글자 이상, 최대 20글자 이하만 가능합니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,20}$", message = "닉네임은 특수문자를 제외한 2~20자리여야 합니다.")
    private String nickname;             // 유저 닉네임

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Pattern(regexp = "^[^\\s@]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$ ", message = "유효하지 않은 이메일 주소입니다.")
    private String email;               // 유저 이메일

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;              // 유저 생일

    @Size(max = 50, message = "최대 50글자까지만 입력 가능합니다.")
    private String introduction;         // 유저 소개

    @Pattern(regexp = "^[^\\s].+\\.[^\\s]{2,}$", message = "url 형식에 맞게 입력해 주세요.(https://example.com)")
    private String sns;                  // 유저 SNS
}
