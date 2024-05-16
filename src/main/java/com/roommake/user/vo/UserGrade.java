package com.roommake.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserGrade {

    private int id;        // 등급번호
    private String name;   // 등급명
    private int pointRate; // 등글별 적립률
}
