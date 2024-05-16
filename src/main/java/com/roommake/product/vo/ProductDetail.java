package com.roommake.product.vo;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetail {

    private int id;            // 상품 상세번호
    private int uniqueId;      // 상품 고유번호
    private String size;       // 상품 크기
    private String color;      // 상품 색상
    private int stock;         // 상품 재고
    private Product product;   // 상품 번호
}
