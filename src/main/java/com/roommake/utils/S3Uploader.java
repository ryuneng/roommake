package com.roommake.utils;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.roommake.exception.FileException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Uploader {

    private final AmazonS3 amazonS3;
    private Set<String> uploadedFileNames = new HashSet<>();

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // 단일 파일 저장
    public String saveFile(MultipartFile file) {
        if (file.isEmpty()) {
            return "default";
        }
        String randomFilename = generateRandomFilename(file);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            amazonS3.putObject(bucket, randomFilename, file.getInputStream(), metadata);
        } catch (AmazonS3Exception e) {
            log.error("Amazon S3 error while uploading file: " + e.getMessage(), e);
            throw new FileException(e.getMessage(), e);
        } catch (SdkClientException e) {
            log.error("AWS SDK client error while uploading file: " + e.getMessage(), e);
            throw new FileException(e.getMessage(), e);
        } catch (IOException e) {
            log.error("IO error while uploading file: " + e.getMessage(), e);
            throw new FileException(e.getMessage(), e);
        }

        return amazonS3.getUrl(bucket, randomFilename).toString();
    }

    // 파일 삭제
    public void deleteFile(String fileUrl) {
        String[] urlParts = fileUrl.split("/");
        String fileBucket = urlParts[2].split("\\.")[0];

        if (!fileBucket.equals(bucket)) {
            throw new FileException("이미지가 존재하지 않습니다.");
        }

        String objectKey = String.join("/", Arrays.copyOfRange(urlParts, 3, urlParts.length));

        if (!amazonS3.doesObjectExist(bucket, objectKey)) {
            throw new FileException("이미지가 존재하지 않습니다.");
        }

        try {
            amazonS3.deleteObject(bucket, objectKey);
        } catch (AmazonS3Exception e) {
            log.error("File delete fail : " + e.getMessage());
            throw new FileException(e.getMessage(), e);
        } catch (SdkClientException e) {
            log.error("AWS SDK client error : " + e.getMessage());
            throw new FileException(e.getMessage(), e);
        }

        log.info("File delete complete: " + objectKey);
    }

    // 랜덤파일명 생성 (파일명 중복 방지)
    private String generateRandomFilename(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        String fileExtension = validateFileExtension(originalFilename);
        String randomFilename = UUID.randomUUID() + "." + fileExtension;
        return randomFilename;
    }

    // 파일 확장자 체크
    private String validateFileExtension(String originalFilename) {
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        List<String> allowedExtensions = Arrays.asList("jpg", "png", "gif", "jpeg");

        if (!allowedExtensions.contains(fileExtension)) {
            throw new FileException("이미지 파일만 가능합니다.");
        }
        return fileExtension;
    }
}
