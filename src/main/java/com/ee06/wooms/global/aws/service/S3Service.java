package com.ee06.wooms.global.aws.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ee06.wooms.global.aws.exception.FileUploadFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.url}")
    private String ROOT_URL;

    public void save(File file, String dir) {
        String keyName = dir + "/" + file.getName();

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, keyName, file);
        try {
            s3Client.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new FileUploadFailedException();
        }
    }

    public String getFilePath(String dir, String fileName, String extension) {
        return ROOT_URL + dir + "/" + fileName + "." + extension;
    }
}
