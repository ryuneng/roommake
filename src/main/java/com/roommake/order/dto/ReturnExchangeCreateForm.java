package com.roommake.order.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReturnExchangeCreateForm {

    private int orderId;              // 주문 번호
    private int paymentId;            // 결제 번호
    private int orderItemId;          // 주문상세 번호
    private int collectionDeliveryId; // 회수 배송지 번호
    private int reDeliveryId;         // 재배송지 번호
    private int beforeDetailId;       // 교환 전 상품상세번호
    private int afterDetailId;        // 교환 후 상품상세번호
    private int reasonId;             // 반품교환사유
    private String detailedReason;    // 반품교환 상세사유
    private String collectionMemo;    // 회수요청사항
    private String deliveryMemo;      // 배송메모
    private String type;              // 타입 (반품 혹은 교환)
}
