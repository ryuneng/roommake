package com.roommake.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CartListDto {
    // 상품 리스트
    private List<CartItemDto> items;

    // 총 상품금액
    public int getTotalItemPrice() {
        int total = 0;
        for (CartItemDto item : items) {
            total += item.getItemPrice();
        }
        return total;
    }

    // 총 할인금액
    public int getTotalDiscountedPrice() {
        int total = 0;
        for (CartItemDto item : items) {
            total += ((item.getPrice() - item.getDiscountedPrice()) * item.getAmount());
        }
        return total;
    }

    // 총 결제금액
    public int getTotalPayPrice() {
        return getTotalItemPrice() - getTotalDiscountedPrice();
    }
}
