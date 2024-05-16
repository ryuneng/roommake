package com.roommake.order.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserOrderInfoDto {

    private int userId;                  // 유저 번호
    private String uniqueRecommendCode;  // 유저 추천코드 (가입시 자동부여)
    private int point;                   // 유저 보유포인트
    private int userGradeId;             // 유저 등급 번호
    private String userGradeName;        // 유저 등급 이름
    private int userPointRate;           // 유저 등급별 적립률
}
