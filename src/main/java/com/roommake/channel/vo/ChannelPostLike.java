package com.roommake.channel.vo;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelPostLike {

    private int postId;    // 채널글번호
    private int userId;    // 유저번호
}
