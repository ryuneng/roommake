package com.roommake.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FolderEdit {

    @NotBlank(message = "필수 입력값입니다.")
    private String folderName;              // 폴더명
    private String folderDescription;       // 폴더 이름
}
