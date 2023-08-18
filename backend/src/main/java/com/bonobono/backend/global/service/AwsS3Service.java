package com.bonobono.backend.global.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class AwsS3Service {


    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    /**
     * AWS S3에 이미지 파일 업로드
     * @param multipartFile : 파일
     * @param dirName : s3 버킷에서 만들어준 폴더 이름
     * @return : Url
     */
    public URL upload(MultipartFile multipartFile, String dirName){

        String fileName = createFileName(multipartFile.getOriginalFilename(), dirName);

        // s3에 이미지 저장
        try(InputStream inputStream = multipartFile.getInputStream()){
            ObjectMetadata metadata = new ObjectMetadata();
            byte[] bytes = IOUtils.toByteArray(inputStream);
            metadata.setContentLength(bytes.length);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, byteArrayInputStream, metadata));
        } catch (IOException e){
            // 예외처리
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
        }

        // s3에 저장된 파일 url 얻어옴.
        return amazonS3.getUrl(bucket, fileName);
    }

    // 파일 이름이 같으면 저장이 안 된다. 따라서 파일이름 앞에 UUID를 붙인다.
    private String createFileName(String fileName, String dirName){
        return dirName + "/" + UUID.randomUUID() + fileName;
    }

    public void delete(String imageUrl, String dirName) {
        try {
            String keyName = URLDecoder.decode(dirName + "/" + imageUrl.split("/")[2], StandardCharsets.UTF_8);
            System.out.println(keyName);
            boolean isFileExist = amazonS3.doesObjectExist(bucket, keyName);
            System.out.println(isFileExist);
            if (isFileExist) {
                amazonS3.deleteObject(bucket, keyName);
            } else {
                throw new IllegalArgumentException("해당 이미지 파일이 없습니다.");
            }
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 파일 삭제를 실패하였습니다.");
        }

    }
}
