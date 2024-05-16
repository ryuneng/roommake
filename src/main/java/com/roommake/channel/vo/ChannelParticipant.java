package com.roommake.channel.vo;

import com.roommake.user.vo.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChannelParticipant {

    private Channel channel;  // 채널번호
    private User user;        // 유저번호

    public void toParticipant(int channelId, int userId) {
        Channel channel = new Channel();
        channel.setId(channelId);
        User user = new User();
        user.setId(userId);

        this.channel = channel;
        this.user = user;
    }
}