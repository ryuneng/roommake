package com.roommake.cart.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartCreateForm {

    private int productId;         // 상품 번호
    private int productDetailId;   // 상품상세 번호
    private int cartId;            // 장바구니 번호
    private int amount;            // 상품 수량
    private int itemPrice;         // 주문일 기준 상품금액
    private int amountToItemPrice; // 상품*수량 합계금액 (할인가격 포함)
}
