package com.roommake.admin.management.enums;

import lombok.Getter;

@Getter
public enum BannerStatusEnum {

    EXPECT("expect"), ACTIVE("active"), END("end");

    private final String status;

    private BannerStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
