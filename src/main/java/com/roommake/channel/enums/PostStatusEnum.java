package com.roommake.channel.enums;

import lombok.Getter;

@Getter
public enum PostStatusEnum {
    BLOCK("block"), DELETE("delete");

    private final String status;

    private PostStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
