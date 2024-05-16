package com.roommake.admin.management.vo;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QnaCategory {

    private int id;      // 문의사항 카테고리 번호
    private String name; // 문의사항 카테고리 이름
}
