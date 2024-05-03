package com.roommake.product.vo;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private int id;                     // 상품번호
    private String name;                // 상품이름
    private String content;             // 상품설명
    private String statusYn;            // 판매상태
    private Date createDate;            // 상품 등록일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;            // 상품 수정일
    private Date deleteDate;            // 상품 삭제일
    private String deleteYn;            // 상품삭제여부
    private int price;                  // 상품 가격
    private int discount;               // 상품 할인율
    private ProductCategory category;   // 카테고리번호
    private int parentsId;              // 추가상품번호

    public String getUpdateDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return (updateDate != null) ? formatter.format(updateDate) : null;
    }

    public String getProductTag(int productId, int categoryId) {

        ProductTag productTag = new ProductTag();
        productTag.getProduct().setId(productId);
        productTag.getCategory().setId(categoryId);
        return null;
    }
}
