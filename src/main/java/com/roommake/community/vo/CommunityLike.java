package com.roommake.community.vo;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityLike {
    private int commId;   // 커뮤니티번호
    private int userId;   // 유저번호
}
