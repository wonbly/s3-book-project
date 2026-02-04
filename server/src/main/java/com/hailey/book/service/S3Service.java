package com.hailey.book.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadFile(MultipartFile file) throws IOException {
        // 파일 이름이 겹치지 않게 무작위 ID를 붙여줘요 (UUID)
        String fileName = "book/" + UUID.randomUUID() + "_" + file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        // S3로 파일 전송!
        amazonS3.putObject(bucket, fileName, file.getInputStream(), metadata);
        
        // 업로드된 파일의 URL 주소를 반환합니다
        return amazonS3.getUrl(bucket, fileName).toString();
    }
}