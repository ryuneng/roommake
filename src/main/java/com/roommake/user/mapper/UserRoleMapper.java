package com.roommake.user.mapper;

import com.roommake.user.vo.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper {

    // 유저 권한 등록
    void createUserRole(UserRole userRole);

    // 유저 권한 삭제
    void deleteUserRole(UserRole userRole);
    
    // 유저 번호로 권한 조회
    List<UserRole> getUserRolesByUserId(int userId);
}
