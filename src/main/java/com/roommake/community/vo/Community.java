package com.roommake.community.vo;

import com.roommake.user.vo.User;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class Community {

    private int id;                         // 커뮤니티번호
    private CommunityCategory category;     // 커뮤니티 카테고리번호
    private User user;                      // 유저번호
    private String title;                   // 커뮤니티 제목
    private String content;                 // 커뮤니티 내용
    private int viewCount;                  // 커뮤니티 조회수
    private Date createDate;                // 커뮤니티 등록일
    private Date updateDate;                // 커뮤니티 수정일
    private Date deleteDate;                // 커뮤니티 삭제일
    private String status;                  // 커뮤니티 상태
    private String deleteYn;                // 커뮤니티 삭제여부
    private int likeCount;                  // 커뮤니티 좋아요수
    private int scrapCount;                 // 커뮤니티 스크랩수
    private int complaintCount;             // 커뮤니티 신고수
    private String imageName;

    public Community(int id) {
        this.id = id;
    }
}