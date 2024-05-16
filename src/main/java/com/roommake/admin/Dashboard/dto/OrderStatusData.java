package com.roommake.admin.Dashboard.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class OrderStatusData {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private int statusId;
    private String statusName;
    private int statusCnt;
}
