package com.roommake.channel.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class ChannelForm {

    @NotEmpty(message = "채널 제목은 필수 입력항목입니다.")
    @Size(max = 210, message = "제목 글자수는 최대 10글자까지 가능합니다.")
    private String title;
    @NotEmpty(message = "채널 소개는 필수 입력항목입니다.")
    private String description;
    private MultipartFile imageFile;
}
