package com.roommake.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProductScrap {

    private int productId;                  // 상품 번호
    private String productImageName;        // 상품 이미지
    private String productName;             // 상품 이름
    private int productPrice;               // 상품 가격
    private int productDiscount;            // 상품 할인율
    private String categoryName;            // 상품 자식 카테고리 이름
    private String prodTagCategoryNames;    // 상품 태그 카테고리 이름
}
