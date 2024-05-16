package com.roommake.user.exception;

/**
 * Email 예외 처리 클래스
 */
public class EmailException extends HomeException {
    /**
     * 생성자: 메시지와 원인을 받아 예외 객체를 생성
     *
     * @param message 예외 발생 이유 설명
     * @param cause   예외의 원인이 되는 상위 예외 객체
     */
    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }
}