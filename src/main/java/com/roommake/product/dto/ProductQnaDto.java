package com.roommake.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
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
    @JsonFormat(pattern = "yyyy년 M월 dd일")
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
    private String loginEmail;

    @JsonGetter
    private String getSecretContent() {
        if ("N".equals(privateYn)) {
            return content;
        }
        if (loginEmail == null) {
            return "비밀글입니다.";
        }
        if (userEmail.equals(loginEmail)) {
            return content;
        }
        return "비밀글입니다.";
    }
}
