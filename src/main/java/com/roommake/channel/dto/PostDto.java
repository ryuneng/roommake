package com.roommake.channel.dto;

import com.roommake.channel.vo.ChannelPost;
import com.roommake.community.vo.ComplaintCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDto {
    private ChannelPost post;
    private boolean isLike;
    private boolean isFollow;
    private List<ComplaintCategory> complaintCategories;
    private List<ChannelPost> recommendChPosts;
}
