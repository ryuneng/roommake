package com.roommake.utils;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    public static String upload(MultipartFile uploadFile, String saveDirectory) {
        // 1. 첨부파일이 비어있으면 파일저장과정 없이 default.png를 반환한다.
        if (uploadFile.isEmpty()) {
            return "default.jpg";
        }
        // 2. 첨부파일이 비어있지 않으면 파일을 저장하고, 해당 파일명을 반환한다.
        String filename = System.currentTimeMillis() + uploadFile.getOriginalFilename();
        // 3. File 객체 생성하고 저장
        File file = new File(saveDirectory, filename);
        try {
            FileCopyUtils.copy(uploadFile.getBytes(), file);
        } catch (IOException ex) {
            throw new RuntimeException("첨부파일을 저장할 수 없습니다.", ex);
        }
        return filename;
    }
}
