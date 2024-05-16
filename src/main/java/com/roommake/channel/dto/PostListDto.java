package com.roommake.channel.dto;

import com.roommake.channel.vo.Channel;
import com.roommake.user.vo.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class PostListDto {
    private int id;             // 채널글번호
    private Channel channel;    // 채널번호
    private User user;          // 유저번호
    private String title;       // 채널글 제목
    private String content;     // 채널글 내용
    private int viewCount;      // 채널글 조회수
    private Date createDate;    // 채널글 등록일
    private Date updateDate;    // 채널글 수정일
    private Date deleteDate;    // 채널글 삭제일
    private String status;      // 채널글 상태
    private String deleteYn;    // 채널글 삭제여부
    private int likeCount;      // 채널글 좋아요수
    private int complaintCount; // 채널글 신고수
    private String imageName;   // 이미지이름
    private int likeStatus;     // 채널글 좋아요 여부
}
