package com.roommake.community.enums;

import lombok.Getter;

@Getter
public enum CommCatEnum {
    HOUSE(1), KNOW_HOW(2);

    private final int commCatNo;

    private CommCatEnum(int commCatNo) {
        this.commCatNo = commCatNo;
    }

    public int getCatNo() {
        return commCatNo;
    }
}
