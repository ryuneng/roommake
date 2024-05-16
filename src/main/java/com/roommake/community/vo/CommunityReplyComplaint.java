package com.roommake.community.vo;

import com.roommake.user.vo.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityReplyComplaint {

    private int id;                          // 신고번호
    private User user;                       // 유저번호
    private CommunityReply reply;            // 커뮤니티 댓글번호
    private ComplaintCategory complaintCat;  // 신고 카테고리번호
    private Date createDate;                 // 신고 생성일
    private Date updateDate;                 // 신고 수정일
    private String complaintYn;              // 신고승인여부
    private String complaintDeleteYn;        // 신고삭제여부
}
