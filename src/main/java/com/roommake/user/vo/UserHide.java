package com.roommake.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserHide {

    private User id;         // 유저 번호
    private User hideUserId; // 숨겨진 유저 번호
    private Date createDate; // 사용자 숨김 생성일
    private Date updateDate; // 사용자 숨김 수정일
    private String hide_yn;  // 사용자 숨김 여부
}
