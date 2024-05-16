package com.roommake.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy년 M월 dd일")
    private Date createDate;
    private int voteCount;
    private String nickname;
    private String email;
    private String loginEmail;
    private String imageName;
}
