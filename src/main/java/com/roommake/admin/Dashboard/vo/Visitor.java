package com.roommake.admin.Dashboard.vo;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Visitor {
    private int id;     // id
    private Date date;  // 방문날짜
    private String ip;  // 방문자 ip
}
