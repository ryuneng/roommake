package com.roommake.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class AllScrap {
    private String type;                // 스크랩 유형
    private int itemId;                 // 스크랩 ID
    private String imageName;           // 스크랩 이미지명
    private String description;
    private Date createDate;            // 생성일
    private Date updateDate;            // 수정일
    private String folderName;          // 폴더명
    private int folderId;               // 폴더 ID
}
