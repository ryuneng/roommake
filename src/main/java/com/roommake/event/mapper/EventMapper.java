package com.roommake.event.mapper;

import com.roommake.event.vo.Attendance;
import com.roommake.user.vo.PlusPointHistory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventMapper {
    void addDailyCheckPoint(PlusPointHistory plusPointHistory);

    void addDailyCheckPointToUser(PlusPointHistory plusPointHistory);

    Attendance getAttendance(int userId);

    void createAttendance(int userId);
}
