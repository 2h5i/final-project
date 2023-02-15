package com.sparta.finalproject.common.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@Slf4j
public class S3Upload {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.dir}")
    private String dir;

    private final AmazonS3Client amazonS3;

    public String upload(MultipartFile multipartFile) throws IOException {
        String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(multipartFile.getInputStream().available());

        amazonS3.putObject(bucket + dir, s3FileName, multipartFile.getInputStream(), objMeta);

        return amazonS3.getUrl(bucket + dir, s3FileName).toString();
    }

    public void deleteFile(String fileUrl) throws IOException {
        try {
//            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            String[] file = fileUrl.split("/");
            String fileName = dir + "/" + file[file.length - 1];

            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, fileName);

            log.info(
                "DELETE URL : " + deleteObjectRequest.getBucketName()
                    + deleteObjectRequest.getKey());

            amazonS3.deleteObject(deleteObjectRequest);
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }

    }

}
