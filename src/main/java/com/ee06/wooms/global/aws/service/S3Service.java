package com.ee06.wooms.global.aws.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ee06.wooms.global.aws.exception.FileUploadFailedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.url}")
    private String root;

    public void save(File file, String dir) {
        String keyName = dir + "/" + file.getName();
        log.info(keyName);

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, keyName, file);
        try {
            s3Client.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new FileUploadFailedException();
        }
    }

    public String getFilePath(String dir, String fileName) {
        log.info("{}{}/{}", root, dir, fileName);
        return root + dir + "/" + fileName + ".mp3";
    }
}
