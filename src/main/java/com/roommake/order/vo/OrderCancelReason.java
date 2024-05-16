package com.roommake.order.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderCancelReason {

    private int id;      // 주문취소사유 번호
    private String name; // 주문취소사유 이름
}
