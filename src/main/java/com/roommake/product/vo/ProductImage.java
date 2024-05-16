package com.roommake.product.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductImage {

    private int id;
    private Product productId; // 상품번호
    private String name;       // 상품이미지 이름
}
