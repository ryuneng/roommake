package com.roommake.channel.dto;

import com.roommake.dto.Pagination;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PostReplyListDto {

    private int totalReplyCount;
    private List<?> postReplies;
    private Pagination pagination;
}
