package com.roommake.user.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PointHistoryDto {
    private int id;             // 히스토리 id
    private int amount;         // 포인트 금액
    private Date createDate;    // 발생일자 
    private int userId;         // 해당 유저
    private int typeId;         // 타입아이디
    private String typeName;    // 타입 이름
    private String parentTypeName;  // 부모타입이름
    private String reason;      // 상세사유
    private String category;    // plus, minus 유형 구분
}
