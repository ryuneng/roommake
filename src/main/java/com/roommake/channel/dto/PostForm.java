package com.roommake.channel.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostForm {

    @NotEmpty(message = "채널글 제목은 필수 입력항목입니다.")
    private String title;
    @NotEmpty(message = "채널글 내용은 필수 입력항목입니다.")
    private String content;
    private MultipartFile imageFile;
}
