package com.roommake.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LikeDto {
    private int id;
    private String imageName;
    private String title;
    private String type;
    private Date likeDate;
}
