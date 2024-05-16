package com.roommake.community.vo;

import com.roommake.user.vo.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityReply {

    private int id;                 // 댓글번호
    private Community community;    // 커뮤니티번호
    private User user;              // 유저번호
    private String content;         // 댓글내용
    private Date createDate;        // 댓글등록일
    private Date updateDate;        // 댓글수정일
    private Date deleteDate;        // 댓글삭제일
    private String status;          // 댓글상태
    private String deleteYn;        // 댓글삭제여부
    private int likeCount;          // 댓글좋아요수
    private int complaintCount;     // 댓글신고수
    private int groupId;            // 댓글 그룹아이디(부모 댓글번호)
    private int parentsId;          // 부모댓글번호
}
