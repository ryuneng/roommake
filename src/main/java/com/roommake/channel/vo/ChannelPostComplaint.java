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
public class ChannelPostComplaint {

    private int id;                             // 신고번호
    private ChannelPost post;                   // 채널글번호
    private User user;                          // 신고한 유저번호
    private ComplaintCategory complaintCat;     // 신고 카테고리번호
    private Date createDate;                    // 신고생성일
    private Date updateDate;                    // 신고수정일
    private String complaintYn;                 // 신고 승인여부
    private String complaintDeleteYn;           // 신고 삭제여부
}
