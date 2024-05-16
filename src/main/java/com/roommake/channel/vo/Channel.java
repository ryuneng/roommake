package com.roommake.channel.vo;

import com.roommake.user.vo.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Channel {

    private int id;             // 채널번호
    private User user;          // 유저번호
    private String title;       // 채널 제목
    private String description; // 채널 설명
    private Date createDate;    // 채널 등록일
    private Date updateDate;    // 채널 수정일
    private Date deleteDate;    // 채널 삭제일
    private String deleteYn;    // 채널 삭제여부
    private String imageName;   // 채널 이미지이름
}
