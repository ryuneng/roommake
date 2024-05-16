package com.roommake.admin.Dashboard.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesData {

    private int id;
    @JsonFormat(pattern = "MM-dd", timezone = "Asia/Seoul")
    private Date date;
    private int salesCnt;
    private long salesAmount;
}
