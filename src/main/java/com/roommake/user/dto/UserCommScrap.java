package com.roommake.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserCommScrap {
    private int communityId;
    private String communityImage;
    private String communityTitle;
    private int authorId;
    private String authorName;
    private Date createDate;
    private int viewCount;
    private int likeCount;
    private String categoryName;
}
