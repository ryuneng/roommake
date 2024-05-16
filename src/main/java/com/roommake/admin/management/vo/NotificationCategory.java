package com.roommake.admin.management.vo;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NotificationCategory {

    private int id;      // 알림 카테고리 번호
    private String name; // 알림 카테고리 이름
}
