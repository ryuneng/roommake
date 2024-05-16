package com.roommake.order.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class OrderCancel {

    private int id;                         // 주문취소번호
    private Order order;                    // 주문
    private Date createDate;                // 주문취소 생성일
    private Date updateDate;                // 주문취소 수정일
    private OrderCancelReason reason;       // 주문취소사유
}
