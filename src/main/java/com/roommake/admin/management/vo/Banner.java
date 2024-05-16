package com.roommake.admin.management.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.roommake.user.vo.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Banner {

    private int id;                 // 배너번호
    private User user;              // 등록자
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;        // 배너 등록일
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deleteDate;        // 배너 삭제일
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;         // 배너 게시시작일
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;           // 배너 게시종료일
    private String status;          // 배너 상태
    private String imageOriginName; // 배너 원본 이미지명
    private String imageUploadName; // 배너 업로드 이미지명
    private String deleteYn;        // 배너 삭제여부
    private String description;     // 배너 설명
    private String url;             // 배너 연결 url
}