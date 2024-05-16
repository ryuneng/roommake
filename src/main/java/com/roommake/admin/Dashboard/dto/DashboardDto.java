package com.roommake.admin.Dashboard.dto;

import com.roommake.admin.Dashboard.vo.SalesData;
import com.roommake.admin.management.dto.ComplaintDto;
import com.roommake.admin.management.vo.Qna;
import lombok.Data;

import java.util.List;

@Data
public class DashboardDto {

    // 주문 상태 관련 추가 예정
    private List<OrderStatusData> orderStatusDataList;
    // 매출 정보
    private List<SalesData> salesDataList;
    // 미처리 신고 내역 추가 예정
    private List<ComplaintDto> noConfirmComplaints;
    // 미응답 CS
    private List<Qna> noAnswerQnas;
    // 고객현황
    private int newUserCnt;         // 신규 가입자수
    private int totalUserCnt;       // 총 회원수

    private int newVisitorCnt;
    private int visitorCounts; // 일자별 방문자 누적 리스트
}
