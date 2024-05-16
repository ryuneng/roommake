package com.roommake.admin.management.mapper;

import com.roommake.admin.management.vo.Notice;
import com.roommake.dto.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    int getTotalRows(Criteria criteria);

    List<Notice> getNotices(Criteria criteria);

    void createNotice(Notice notice);

    List<Notice> getAllNotices();

    Notice getNoticeById(int id);

    void modifyNotice(Notice notice);
}
