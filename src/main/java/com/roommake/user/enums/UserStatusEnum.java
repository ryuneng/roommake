package com.roommake.user.enums;

import lombok.Getter;

@Getter
public enum UserStatusEnum {

    ACTIVE("active"), BLOCK("block"), DELETE("delete");

    private final String status;

    private UserStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
