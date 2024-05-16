package com.roommake.order.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderCancelForm {

    private int orderId;                // 주문 번호
    private int paymentId;              // 결제 번호
    private String tid;                 // 카카오페이 결제 고유번호
    private int totalPrice;             // 전체취소 금액
    private int reasonId;               // 주문취소사유 번호
    private int usePoint;               // 사용 포인트
    private List<Integer> orderItemIds; // 주문상세 번호
}
