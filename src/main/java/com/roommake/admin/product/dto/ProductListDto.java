package com.roommake.admin.product.dto;

import com.roommake.product.vo.ProductTag;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductListDto {
    private int id;
    private String name;
    private String price;
    private String imageName;
    private String category;
    private String categoryName;
    private String content;
    private Date createDate;
    private ProductTag productTag;
    private int discount;
    private int starRating;
}
