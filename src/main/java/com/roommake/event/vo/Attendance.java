package com.roommake.event.vo;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
    private int userId;
    private Date attendenceDate;
}
