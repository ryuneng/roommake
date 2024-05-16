package com.roommake.user.vo;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Follow {

    private int followerUserId;    // 팔로우하는유저
    private int followeeUserId;    // 팔로우받는유저
}
