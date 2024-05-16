package com.roommake.admin.product.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDetailForm {
    private int productId;
    private String uniqueId;
    private String size;
    private String color;
    private int stock;
}
