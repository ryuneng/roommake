package com.roommake.community.dto;

import com.roommake.community.vo.Community;
import com.roommake.community.vo.ComplaintCategory;
import com.roommake.dto.Pagination;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommDetailDto {
    private List<ComplaintCategory> complaintCategories;
    private Community community;
    private boolean isLike;
    private boolean isScrap;
    private boolean isFollow;
    private int totalReplyCount;
    private List<CommReplyDto> communityReplies;
    private Pagination replyPagination;
    private List<Community> recommendCommunities;
}
