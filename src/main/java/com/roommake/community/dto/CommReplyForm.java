package com.roommake.community.dto;

import lombok.Data;

@Data
public class CommReplyForm {
    private String content;
    private int parentsReplyId;
}
