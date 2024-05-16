package com.roommake.admin.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AdminExchangeDto {
    private int exchangeId;         // 교환번호
    private String status;          // 교환 상태
    private String approvalYn;      // 교환승인여부
    private String reasonName;      // 교환이유
    private String OrderItemId;     // 주문번호
    private Date createDate;        // 교환신청일자
    private int productId;          // 상품번호
    private String productName;     // 상품이름
    private String userNickname;    // 주문자명
    private String deliveryAddress; // 재배송지
    private int afterId;            // 교환받을 상품
    private int beforeId;           // 교환전 상품
}
