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
public class Notice {

    private int id;             // 공지사항 번호
    private String title;       // 공지사항 제목
    private String content;     // 공지사항 내용
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;    // 공지 작성일
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;    // 공지 수정일
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deleteDate;    // 공지 삭제일
    private String deleteYn;    // 공지 삭제여부
    private int priority;       // 공지사항 우선순위
    private User updateByUser;  // 공지사항 수정자
    private User createByUser;  // 공지사항 작성자

    public String getHtmlContent() {
        if (content == null) {
            return null;
        }
        return content.replaceAll("\n", "<br />");
    }
}
