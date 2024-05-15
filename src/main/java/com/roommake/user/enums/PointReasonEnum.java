package com.roommake.user.enums;

import lombok.Getter;

@Getter
public enum PointReasonEnum {

    DAILY_CHECK("출석체크"),
    SIGN_UP("회원가입으로 인한 적립"),
    RECOMMEND("추천인가입으로 인한 적립"),
    CONFIRM_ORDER("상품구매확정 - 주문번호 : "),
    CANCEL_ORDER("취소한 주문번호 : "),
    PHOTO_REVIEW_WRITE("포토리뷰작성으로 인한 적립"),
    NORMAL_REVIEW_WRITE("일반리뷰작성으로 인한 적립");

    private final String reason;

    private PointReasonEnum(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
