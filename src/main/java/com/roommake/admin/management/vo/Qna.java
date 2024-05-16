package com.roommake.admin.management.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.roommake.product.vo.Product;
import com.roommake.user.vo.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Qna {

    private int id;                 // 문의사항 번호
    private QnaCategory category; // 문의사항 카테고리 번호
    private String title;           // 문의사항 제목
    private String content;         // 문의사항 내용
    private User user;            // 문의글 작성자
    private String privateYn;       // 문의사항 비밀글 여부
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;        // 문의사항 작성일
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deleteDate;        // 문의사항 삭제일
    private String deleteYn;          // 문의사항 삭제여부
    private User answerWriter;      // 문의글 답변자
    private String answer;          // 문의사항 답변내용
    private String answerYn;        // 문의사항 답변여부
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;        // 문의사항 답변작성일
    private Product product;        // 문의 관련 상품
}
