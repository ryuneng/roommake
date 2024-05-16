package com.roommake.community.vo;

import com.roommake.user.vo.ScrapFolder;
import com.roommake.user.vo.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityScrap {

    private Community community;        // 커뮤니티번호
    private User user;                  // 유저번호
    private ScrapFolder scrapFolder;    // 스크랩폴더번호
    private Date createDate;            // 스크랩 최초생성일
    private Date updateDate;            // 스크랩 수정일
    private Date deleteDate;            // 스크랩 삭제일
    private String deleteYn;            // 스크랩 삭제여부
}
