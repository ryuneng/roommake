package com.roommake.admin.Dashboard.mapper;

import com.roommake.admin.Dashboard.dto.OrderStatusData;
import com.roommake.admin.Dashboard.vo.SalesData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DashboardMapper {
    // 매출 데이터 입력
    void createSalesData(String date);

    // 매출 데이터 조회
    List<SalesData> getSalesData(@Param("date") String date,
                                 @Param("days") int days);

    // 신규가입자수
    int getNewUserCnt(String date);

    // 누적 가입자수
    int getUserCntByDate(String date);

    // 주문 상태별 조회
    List<OrderStatusData> getOrderStatusData(String date);

    // 방문자 기록(ip별로 1회 접속기록)
    void createVisitor(String ip);

    // 전날 방문자 수
    int getDailyVisitorCount();

    // 일자별 방문자수 저장
    void createVisitorCount();

    // 누적 방문자수 조회
    int getTotalVisitorCount();
}
