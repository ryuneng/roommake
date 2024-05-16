package com.roommake.admin.user.service;

import com.roommake.user.mapper.UserGradeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserGradeService {

    private final UserGradeMapper userGradeMapper;

    /**
     * 매일 오전 9시 전날 유저의 결제 누적 금액을 수정한다
     */
    @Scheduled(cron = "0 0 9 * * *")
    public void modifyUserActualPrice() {
        // 현재는 단기적인 확인을 위해 1일 단위로 전날 금액을 저장한다.
        userGradeMapper.modifyUserActualPrice();
        userGradeMapper.modifyUserGrade();
        log.info("유저 결제 누적금액 반영 후 등급 수정");
    }
}
