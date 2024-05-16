package com.roommake.admin.refund;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AdminRefundDto {
    private int id;
    private Date createDate;
    private Date updateDate;
    private String status;
    private int amount;
    private String userNickname;
    private String paymentMethod;
}
//        int id, Date createDate, Date updateDate, String status,
//        int amount, String userNickname
//) {
//
//}
