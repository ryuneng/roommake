package com.roommake.user.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;                      // 유저 번호
    private String email;                // 유저 이메일(아이디)
    @JsonIgnore
    private String password;             // 유저 비밀번호
    private String nickname;             // 유저 닉네임
    private String profilePhoto;         // 유저 프로필사진
    private String tel;                  // 유저 연락처
    private Date birthDate;              // 유저 생일
    private String introduction;         // 유저 소개
    private String sns;                  // 유저 SNS
    private Date createDate;             // 유저 가입일
    private Date updateDate;             // 유저 수정일
    private Date deleteDate;             // 유저 탈퇴일
    private String uniqueRecommendCode;  // 유저 추천코드 (가입시 자동부여)
    private String socialYn;             // 유저 소셜 로그인 여부
    private String status;               // 유저 상태
    private UserGrade userGrade;         // 등급번호
    private int point;                   // 유저 보유포인트
    private String address;              // 유저 주소
    private int complaintCount;          // 유저 신고 누적수
    private int followingCount;          // 유저 팔로잉 수
    private int followerCount;           // 유저 팔로워 수
    private String optionRecommendCode;  // 유저 추천코드 (가입시 선택입력)

    public User(int id) {
        this.id = id;
    }
}
