package com.roommake.user.vo;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScrapFolder {

    private int id;             // 스크랩 폴더 번호
    private String name;        // 스크랩 폴더 이름
    private String description; // 스크랩 폴더 설명
    private User user;          // 유저 번호
    private Date createDate;    // 스크랩 폴더 생성일
    private Date deleteDate;    // 스크랩 폴더 삭제일
    private String deleteYn;    // 스크랩 폴더 삭제여부

    public ScrapFolder(int id) {
        this.id = id;
    }
}
