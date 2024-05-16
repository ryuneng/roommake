package com.roommake.user.enums;

import lombok.Getter;

@Getter
public enum UserGradeEnum {

    BRONZE("bronze"), SILVER("silver"), GOLD("gold"), VIP("vip");

    private final String grade;

    private UserGradeEnum(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }
}
