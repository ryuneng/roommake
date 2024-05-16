package com.roommake.order.dto;

import com.roommake.order.vo.OrderItem;
import com.roommake.order.vo.OrderStatus;
import com.roommake.order.vo.Payment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderListDto {

    private int id;                     // 주문 번호
    private int userId;                 // 주문자
    private Date createDate;            // 주문 일자
    private Date updateDate;            // 주문 변경 일자
    private int totalPrice;             // 총 주문금액
    private int paymentPrice;           // 결제금액
    private String invoiceNumber;       // 송장번호
    private OrderStatus status;         // 주문상태
    private List<OrderItem> orderItems; // 주문상세 리스트
    private Payment payment;            // 결제
}
