package com.roommake.community.dto;

import com.roommake.dto.Pagination;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CommReplyListDto {

    private int totalReplyCount;
    private List<CommReplyDto> communityReplies;
    private Pagination pagination;
}
