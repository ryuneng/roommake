package com.roommake.user.exception;

public class HomeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public HomeException() {
    }

    public HomeException(String message) {
        super(message);
    }

    public HomeException(String message, Throwable cause) {
        super(message, cause);
    }
}
