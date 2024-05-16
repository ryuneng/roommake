package com.roommake.community.vo;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintCategory {

    private int id;         // 카테고리번호
    private String content; // 카테고리내용
}
