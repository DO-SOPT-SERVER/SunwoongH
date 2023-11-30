package org.sopt.infra.s3;

import lombok.RequiredArgsConstructor;
import org.sopt.common.error.ErrorStatus;
import org.sopt.common.error.InvalidValueException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class S3Handler {
    @Value("${aws-property.s3-bucket-name}")
    private String bucketName;
    private final S3Client s3Client;

    public String uploadImage(MultipartFile image) {
        String key = generateImageFileName();
        PutObjectRequest request = generateRequest(image, key);
        RequestBody requestBody = generateRequestBody(image);
        s3Client.putObject(request, requestBody);
        return key;
    }

    public void deleteImage(String key) {
        s3Client.deleteObject((DeleteObjectRequest.Builder builder) ->
                builder.bucket(bucketName)
                        .key(key)
                        .build()
        );
    }

    private PutObjectRequest generateRequest(MultipartFile image, String key) {
        return PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(image.getContentType())
                .contentDisposition("inline")
                .build();
    }

    private RequestBody generateRequestBody(MultipartFile image) {
        try {
            return RequestBody.fromBytes(image.getBytes());
        } catch (IOException e) {
            throw new InvalidValueException(ErrorStatus.BAD_IMAGE_FILE);
        }
    }

    private String generateImageFileName() {
        return "images/" + UUID.randomUUID() + ".jpg";
    }
}