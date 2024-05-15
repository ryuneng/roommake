package com.roommake.user.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserGradeMapper {

    // 유저 누적 결제금액 변경
    void modifyUserActualPrice();

    // 유저 등급 변경
    void modifyUserGrade();
}
