package com.roommake.admin.management.vo;

import com.roommake.user.vo.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Faq {

    private int id;                 // 질문번호
    private String title;           // 질문제목
    private String content;         // 질문내용
    private Date createDate;        // 질문등록일
    private Date updateDate;        // 질문수정일
    private Date deleteDate;        // 질문삭제일
    private String deleteYn;        // 질문삭제여부
    private FaqCategory category; // 질문카테고리번호
    private User createByUser;    // 질문작성자
    private User updateByUser;    // 질문수정자

    public FaqCategory getFaqCategory(int categoryId) {
        this.category.setId(categoryId);
        return category;
    }
}
