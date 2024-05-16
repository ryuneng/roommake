package com.roommake.community.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommunityCategory {

    private int id;         // 카테고리번호
    private String name;    // 카테고리이름

    public CommunityCategory(int id) {
        this.id = id;
    }
}
