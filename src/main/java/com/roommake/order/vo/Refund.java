package com.roommake.order.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Refund {

    private int id;                // 환불번호
    private Date createDate;       // 환불생성일자
    private Date updateDate;       // 환불수정일자
    private String status;         // 환불처리상태
    private int amount;            // 환불금액
    private ItemReturn itemReturn; // 반품번호
    private Payment payment;       // 결제번호
}
