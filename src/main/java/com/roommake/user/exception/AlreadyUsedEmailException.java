package com.roommake.user.exception;

/**
 * 이미 사용된 이메일 예외 클래스
 */
public class AlreadyUsedEmailException extends HomeException {
    private static final long serialVersionUID = 1L;

    public AlreadyUsedEmailException(String message) {
        super(message);
    }
}
