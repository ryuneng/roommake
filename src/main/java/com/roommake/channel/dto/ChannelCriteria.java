package com.roommake.channel.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChannelCriteria {

    private int page;         // 요청한 페이지 번호
    private int rows;         // 한번에 표시할 데이터 개수
    private String sort;      // 정렬방식
    private String opt;       // 검색옵션
    private String keyword;   // 검색어
    private int begin;        // 검색시작 범위
    private int end;          // 검색종료 범위
    private String filt;      // 필터링 옵션
    private int channelId;    // 채널 아이디
    private int userId;       // 유저 아이디
    private int postId;       // 채널글 아이디
}
