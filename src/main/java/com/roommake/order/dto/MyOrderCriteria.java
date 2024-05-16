package com.roommake.order.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyOrderCriteria {

    private int page;           // 요청한 페이지 번호
    private int rows;           // 한번에 표시할 데이터 개수
    private int begin;          // 검색시작 범위
    private int end;            // 검색종료 범위
    private String beginDate;   // 조회 시작일
    private String endDate;     // 조회 종료일
    private String statusId;    // 주문상태
}
