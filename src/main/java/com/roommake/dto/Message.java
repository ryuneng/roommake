package com.roommake.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {
    String message = "";

    public Message(String message) {
        this.message = message;
    }
}
