package com.roommake.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PointType {

    private int id;        // 포인트 유형 번호
    private int parentsId; // 부모 포인트 유형 번호
    private String name;   // 포인트 유형 이름

    public static PointType getPointType(int id) {
        PointType pointType = new PointType();
        pointType.setId(id);
        return pointType;
    }
}
