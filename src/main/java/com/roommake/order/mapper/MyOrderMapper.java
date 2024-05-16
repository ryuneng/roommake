package com.roommake.order.mapper;

import com.roommake.order.dto.MyOrderCriteria;
import com.roommake.order.dto.OrderListDto;
import com.roommake.order.dto.UserOrderInfoDto;
import com.roommake.order.vo.OrderItem;
import com.roommake.order.vo.OrderStatus;
import com.roommake.order.vo.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyOrderMapper {

    List<OrderListDto> getAllOrdersByUserId(@Param("userId") int userId,
                                            @Param("criteria") MyOrderCriteria criteria);

    List<OrderItem> getOrderItemsByOrderId(int id);

    Payment getPaymentByOrderId(int id);

    OrderStatus getOrderStatusByOrderId(int id);

    int getTotalRows(@Param("criteria") MyOrderCriteria criteria, @Param("userId") int userId);

    UserOrderInfoDto getUserOrderInfoByUserId(int id);
}
