package com.roommake.order.vo;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private int id;          // 결제번호
    private String tid;      // 카카오페이 결제고유번호
    private Order order;     // 주문
    private int price;       // 결제금액
    private Date createDate; // 결제생성일자
    private Date updateDate; // 결제수정일자
    private String method;   // 결제수단
    private int usePoint;    // 사용포인트
}
