package com.roommake.order.mapper;

import com.roommake.order.dto.OrderCancelDto;
import com.roommake.order.dto.OrderItemDto;
import com.roommake.order.dto.ReturnExchangeCreateForm;
import com.roommake.order.dto.ReturnExchangeDto;
import com.roommake.order.vo.*;
import com.roommake.product.vo.ProductDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderClaimMapper {

    List<OrderCancelReason> getAllCancelReasons();

    OrderItemDto getOrderItemDtoByOrderItemId(int orderItemId);

    void createOrderCancel(OrderCancel orderCancel);

    void createCancelRefund(Refund refund);

    void updateCancelOrderStatus(int orderId);

    void updateCancelOrderItemStatus(int orderId);

    OrderCancelDto getOrderCancelByOrderId(int orderId);

    Refund getRefundByPaymentId(int paymentId);

    OrderCancelReason getCancelReasonByCancelId(int orderCancelId);

    OrderCancelReason getCancelReasonById(int reasonId);

    List<ReturnExchangeReason> getAllReturnExchangeReasons();

    void createItemReturn(ReturnExchangeCreateForm form);

    ReturnExchangeDto getItemReturnByOrderItemId(int id);

    Delivery getReturnCollectionDeliveryByReturnId(int id);

    ReturnExchangeReason getReturnReasonByReturnId(int id);

    void updateClaimOrderItemStatus(@Param("itemId") int itemId, @Param("statusId") int statusId);

    OrderItem getOrderItemByOrderItemId(int id);

    ReturnExchangeReason getReturnExchangeReasonById(int id);

    ProductDetail getProductDetailById(int id);

    void createExchange(Exchange exchange);

    void createExchangeDetail(ExchangeDetail exchangeDetail);

    ReturnExchangeReason getExchangeReasonByExchangeId(int id);

    ExchangeDetail getExchangeDetailByExchangeId(int id);

    ReturnExchangeDto getExchangeByOrderItemId(int id);
}
