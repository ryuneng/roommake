package com.roommake.utils;

import java.util.Random;

public class EmailVerifyCodeUtils {

    /**
     * 랜덤 이메일 인증 코드 생성
     *
     * @return 6자리 숫자 인증코드
     */
    public String createEmailVerifyCode() {
        Random random = new Random();
        int num = random.nextInt(999999);
        return String.format("%06d", num);
    }
}
