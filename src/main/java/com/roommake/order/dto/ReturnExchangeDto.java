package com.roommake.order.dto;

import com.roommake.order.vo.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ReturnExchangeDto {

    private int orderId;                   // 주문 번호
    private int itemReturnId;              // 반품 번호
    private int exchangeId;                // 교환 번호
    private Date createDate;               // 반품교환 생성일자
    private Date updateDate;               // 반품교환 수정일자
    private Payment payment;               // 결제
    private Refund refund;                 // 환불
    private Delivery collectionDelivery;   // 회수지
    private Delivery reDelivery;           // 재배송지
    private String collectionMemo;         // 회수요청사항
    private String deliveryMemo;           // 배송메모
    private ExchangeDetail exchangeDetail; // 교환상세내역
    private ReturnExchangeReason reason;   // 반품교환사유
    private String detailedReason;         // 반품교환 상세사유
    private String status;                 // 반품교환 처리상태
    private String approvalYn;             // 반품교환 승인여부
    private OrderItemDto item;             // 상품정보, 상품상세정보가 담긴 객체 단품
}
