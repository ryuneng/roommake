package com.roommake.product.dto;

import com.roommake.product.vo.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductQnaDto {

    private int productId;
    private String answer;
    private String answerWriter;
    private String answerYn;
    private int categoryId;
    private String content;
    private Date createDate;
    private Date deleteDate;
    private String deleteYn;
    private int qnaId;
    private String privateYn;
    private String title;
    private Date updateDate;
    private String userNickname;
    private String userEmail;
    private int userId;
}
