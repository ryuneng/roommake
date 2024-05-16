package com.roommake.user.vo;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Term {

    private int id;           // 이용약관 번호
    private String title;     // 이용약관 제목
    private String content;   // 이용약관 내용
    private Date createDate;  // 이용약관 생성일
    private Date updateDate;  // 이용약관 수정일
    private Date deleteDate;  // 이용약관 삭제일
    private String deleteYn;  // 삭제여부
    private String requireYn; // 필수여부
}
