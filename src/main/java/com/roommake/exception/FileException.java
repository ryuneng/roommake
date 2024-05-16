package com.roommake.exception;

import com.roommake.user.exception.HomeException;

public class FileException extends HomeException {

    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
