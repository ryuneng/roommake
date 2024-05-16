package com.roommake.user.vo;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {

    private int id;      // 유저권한 번호
    private User user; // 유저 번호
    private String name; // 권한 이름
}
