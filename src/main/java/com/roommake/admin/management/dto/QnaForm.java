package com.roommake.admin.management.dto;

import lombok.Data;

@Data
public class QnaForm {
    private int id;
    private int categoryId;
    private String title;
    private String content;
    private String secret;
}
