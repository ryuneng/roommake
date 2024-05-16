package com.roommake.user.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TermAgreement {

    private String agree1;      // 약관 1
    private String agree2;      // 약관 2
    private String agree3;      // 약관 3
    private Date createDate;    // 약관동의 생성일
    private Date updateDate;    // 약관동의 수정일
    private User user;          // 유저 번호
    private Term term;          // 약관 번호
}
