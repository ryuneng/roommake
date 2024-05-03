package com.roommake.order.vo;

import com.roommake.user.vo.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Order {

    private int id;               // 주문 번호
    private User user;            // 주문자
    private Date createDate;      // 주문 일자
    private Date updateDate;      // 주문 변경 일자
    private int totalPrice;       // 총 주문금액
    private Delivery delivery;    // 배송지
    private int paymentPrice;     // 결제금액
    private OrderStatus orderStatus;   // 주문상태
}
