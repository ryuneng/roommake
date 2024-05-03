package com.roommake.product.dto;

import com.roommake.product.vo.Product;
import com.roommake.product.vo.ProductReview;
import com.roommake.user.vo.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductReviewDto {

    private int id;
    private String content;
    private int rating;
    private Date createDate;
    private int voteCount;
    private String nickname;
}
