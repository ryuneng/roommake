package com.roommake.order.vo;

import com.roommake.product.vo.Product;
import com.roommake.product.vo.ProductDetail;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderItem {

    private int id;                      // 주문상세번호
    private Order order;                 // 주문
    private Product product;             // 상품
    private ProductDetail productDetail; // 상품상세
    private int amount;                  // 상품수량
    private int price;                   // 상품가격
    private OrderStatus orderStatus;     // 주문상세상태
    private String imageName;            // 상품 이미지명
}
