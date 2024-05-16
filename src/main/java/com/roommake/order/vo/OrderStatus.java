package com.roommake.order.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderStatus {
    
    private int id;      // 주문상태 번호
    private String name; // 주문상태 이름
}
