package com.roommake.cart.vo;

import com.roommake.product.vo.Product;
import com.roommake.product.vo.ProductDetail;
import com.roommake.user.vo.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Cart {
    private int id;                       // 장바구니번호
    private Product product;              // 상품번호
    private ProductDetail productDetail;  // 상품상세번호
    private User user;                    // 유저번호
    private int amount;               // 카트 상품수량
}
