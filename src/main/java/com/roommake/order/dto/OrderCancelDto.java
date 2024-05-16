package com.roommake.order.dto;

import com.roommake.order.vo.OrderCancelReason;
import com.roommake.order.vo.Payment;
import com.roommake.order.vo.Refund;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderCancelDto {

    private int orderId;              // 주문 번호
    private int orderCancelId;        // 주문취소 번호
    private Date createDate;          // 주문취소 생성일자
    private Date updateDate;          // 주문취소 수정일자
    private Payment payment;          // 결제
    private Refund refund;            // 환불
    private OrderCancelReason reason; // 주문취소사유
    private OrderItemDto item;        // 상품정보, 상품상세정보가 담긴 객체 단품 (부분취소 시 사용)
    private List<OrderItemDto> items; // 상품정보, 상품상세정보가 담긴 객체 리스트
}
