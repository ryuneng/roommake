package com.roommake.admin.management.mapper;

import com.roommake.admin.management.dto.ComplaintDto;
import com.roommake.admin.management.vo.Complaint;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ComplaintMapper {
    // 전체 게시글 신고 조회
    List<ComplaintDto> getBoardComplaints(String filt);

    // 전체 댓글 신고 조회
    List<ComplaintDto> getReplyComplaint(String filt);

    // 신고 조회
    Complaint getCommComplaintById(int id);

    Complaint getCommReplyComplaintById(int id);

    Complaint getPostReplyComplaintById(int id);

    Complaint getPostComplaintById(int id);

    // 신고 확정, 취소
    void modifyPostComplaint(Complaint complaint);

    void modifyCommComplaint(Complaint complaint);

    void modifyPostReplyComplaint(Complaint complaint);

    void modifyCommReplyComplaint(Complaint complaint);

    // 회원 작성 콘텐츠 신고 누적 조회

    // 회원 신고 누적 업데이트
}
