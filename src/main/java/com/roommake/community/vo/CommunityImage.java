package com.roommake.community.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommunityImage {

    private int id;             // 이미지번호
    private Community commId;   // 커뮤니티번호
    private String name;        // 이미지이름

}
