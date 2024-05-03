package com.roommake.admin.order.service;

import com.roommake.order.mapper.OrderMapper;
import com.roommake.order.vo.Exchange;
import com.roommake.order.vo.Order;
import com.roommake.order.vo.OrderCancel;
import com.roommake.order.vo.Refund;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminOrderService {
    private final OrderMapper orderMapper;

    @Transactional
    public void updateExchange(int id) {
        orderMapper.updateExchangeApprovalYn(id);
    }

    public List<Order> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    public int updateOrderStatus(Order order) {
        return orderMapper.updateOrderStatus(order);
    }

    public List<Refund> getAllRefund() {
        return orderMapper.getRefund();
    }

    public List<Exchange> getAllExchanges() {
        return orderMapper.getAllExchanges();
    }

    public List<OrderCancel> getAllorderCancel() {
        return orderMapper.getAllorderCancels();
    }
}
