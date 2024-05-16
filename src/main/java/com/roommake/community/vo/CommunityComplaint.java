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
public class CommunityComplaint {

    private int id;                         // 신고번호
    private User user;                      // 유저번호
    private Community community;            // 커뮤니티번호
    private ComplaintCategory complaintCat; // 신고 카테고리번호
    private Date createDate;                // 신고 생성일
    private Date updateDate;                // 신고 수정일
    private String complaintYn;             // 신고 승인여부
    private String complaintDeleteYn;       // 신고 삭제여부
}
