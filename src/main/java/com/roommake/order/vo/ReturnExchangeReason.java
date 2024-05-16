package com.roommake.order.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReturnExchangeReason {

    private int id;              // 반품교환사유 번호
    private String name;         // 반품교환사유 이름
}
