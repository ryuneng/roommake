package com.roommake.community.enums;

import lombok.Getter;

@Getter
public enum CommStatusEnum {

    ACTIVE("active"), BLOCK("block"), DELETE("delete");

    private final String status;

    private CommStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

