package com.roommake.utils;

import java.security.SecureRandom;

public class UniqueRecommendCodeUtils {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 8;

    /**
     * 회원가입시 자동으로 부여되는 랜덤 추천인 코드 생성
     *
     * @return 추천인 코드
     */
    public static String createUniqueRecommendCode() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = secureRandom.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }
}
