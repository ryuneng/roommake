package com.roommake.product.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductCategory {

    private int id;        // 카테고리 번호
    private String name;   // 카테고리 이름
    private int parentsId; // 부모카테고리 번호
}
