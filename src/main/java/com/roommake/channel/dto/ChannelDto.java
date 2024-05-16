package com.roommake.channel.dto;

import com.roommake.channel.vo.Channel;
import com.roommake.dto.Pagination;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChannelDto {
    private Channel channel;
    private boolean isParticipant;
    private int channelParticipantCount;
    private int channelPostCount;
    private List<?> channelPosts;
    private Pagination paging;
}
