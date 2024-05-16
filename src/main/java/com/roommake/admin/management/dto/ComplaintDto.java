package com.roommake.admin.management.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ComplaintDto {
    private int id;
    private int contentId;
    private String content;                 // 신고당한 콘텐츠 내용(제목 혹은 댓글내용)
    private int contentWriterId;            // 신고당한 콘텐츠 유저
    private int complaintUserId;            // 신고한 유저
    private String complaintUserNickname;   // 신고한 유저 닉네임
    private int categoryId;
    private String categoryContent;
    private Date createDate;
    private Date updateDate;
    private String complaintYn;
    private String deleteYn;
    private String type;        // 콘텐츠 타입(post, community, postReply, commReply)
}
