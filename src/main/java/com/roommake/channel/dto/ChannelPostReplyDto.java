package com.roommake.channel.dto;

import com.roommake.channel.vo.ChannelPost;
import com.roommake.user.vo.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelPostReplyDto {

    private int id;             // 댓글번호
    private ChannelPost post;   // 채널글번호
    private User user;          // 유저번호
    private String content;     // 댓글 내용
    private Date createDate;    // 댓글 등록일
    private Date updateDate;    // 댓글 수정일
    private Date deleteDate;    // 댓글 삭제일
    private String status;      // 댓글 상태
    private String deleteYn;   // 댓글 삭제여부
    private int likeCount;      // 댓글 좋아요수
    private int complaintCount; // 댓글 신고수
    private int groupId;        // 댓글 그룹아이디(부모 댓글번호)
    private int parentsId;      // 부모 댓글번호
    private int likeStatus;     // 댓글 좋아요여부(1 또는 0)
}
