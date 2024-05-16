package com.roommake.channel.vo;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelPostReplyLike {

    private int userId;   // 유저번호
    private int replyId;  // 채널글 댓글번호
}
