package com.roommake.product.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductTag {

    private Product product;                          // 상품 번호
    private ProductTagCategory category;              // 태그 카테고리 번호
}
