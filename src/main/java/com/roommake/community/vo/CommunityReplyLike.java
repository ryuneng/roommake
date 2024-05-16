package com.roommake.community.vo;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityReplyLike {
    private int commReplyId;     // 커뮤니티 댓글번호
    private int userId;                    // 유저번호
}
