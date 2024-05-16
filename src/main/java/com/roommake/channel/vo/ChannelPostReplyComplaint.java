package com.roommake.channel.vo;

import com.roommake.community.vo.ComplaintCategory;
import com.roommake.user.vo.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelPostReplyComplaint {

    private int id;                         // 신고번호
    private ChannelPostReply reply;         // 채널글 댓글번호
    private User user;                      // 유저번호
    private ComplaintCategory complaintCat; // 신고 카테고리번호
    private Date createDate;                // 신고생성일
    private Date updateDate;                // 신고수정일
    private String complaintYn;             // 신고 승인여부
    private String complaintDeleteYn;       // 신고 삭제여부
}
