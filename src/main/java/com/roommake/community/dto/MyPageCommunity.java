package com.roommake.community.dto;

import com.roommake.user.vo.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 마이페이지 - 커뮤니티 DTO
 */
@Getter
@Setter
@ToString
public class MyPageCommunity {
    private int id;             // 커뮤니티 번호
    private String title;       // 커뮤니티 제목
    private String content;     // 커뮤니티 내용
    private int viewCount;      // 커뮤니티 조회수
    private int replyCount;     // 커뮤니티 댓글수
    private String imageName;   // 커뮤니티 대표사진
    private Date createDate;    // 커뮤니티 작성일
    private User user;          // 유저 닉네임
}
