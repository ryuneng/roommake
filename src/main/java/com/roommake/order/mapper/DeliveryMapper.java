package com.roommake.order.mapper;

import com.roommake.order.vo.Delivery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeliveryMapper {

    List<Delivery> getDeliveriesByUserId(int userId);

    void createDelivery(Delivery delivery);

    void deleteDelivery(int id);

    Delivery getDeliveryById(int id);

    void modifyDelivery(Delivery delivery);

    void updateDefaultDeliveryByUserId(int id);
}
