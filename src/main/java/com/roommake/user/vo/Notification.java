package com.roommake.user.vo;

import com.roommake.admin.management.vo.NotificationCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Notification {

    private int id;                          // 알림 번호
    private User userId;                     // 알림받는 유저
    private User actionUserId;               // 알림 발생원인 유저
    private NotificationCategory CategoryId; // 알림 카테고리 번호
    private String content;                  // 알림 내용
    private Date notificationDate;           // 알림 발생일시
    private String readYn;                   // 알림 확인여부
    private String deleteYn;                 // 알림 삭제여부
    private Date readDate;                   // 알림 확인일시
}
