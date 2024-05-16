package com.roommake.admin.management.vo;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaqCategory {

    private int id;         // 질문카테고리번호
    private String name;    // 질문카테고리이름
}
