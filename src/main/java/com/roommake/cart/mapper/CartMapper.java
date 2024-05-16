package com.roommake.cart.mapper;

import com.roommake.cart.dto.CartItemDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartMapper {

    List<CartItemDto> getCartsByUserId(int userId);

    List<CartItemDto> getItemOptionsByProductId(int productId);

    void deleteCart(@Param("cartIds") List<Integer> cartIds);

    void updateCartAmount(int cartId, int amount);

    void updateCartOption(int cartId, int productDetailId);

    void deleteExpiredCartItems();
}
