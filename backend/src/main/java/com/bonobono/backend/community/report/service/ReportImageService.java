package com.bonobono.backend.community.report.service;

import com.bonobono.backend.community.report.dto.req.ReportImageRequestDto;
import com.bonobono.backend.community.report.entity.Report;
import com.bonobono.backend.community.report.entity.ReportImage;
import com.bonobono.backend.community.report.repository.ReportImageRepository;
import com.bonobono.backend.global.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportImageService {

    private final ReportImageRepository reportImageRepository;

    private final AwsS3Service awsS3Service;

    public void saveImage(Report report, MultipartFile imageFile, String imageDirName){
        String imageUrl = awsS3Service.upload(imageFile, imageDirName).getPath();
        ReportImageRequestDto requestDto = ReportImageRequestDto.builder()
                .imageName(imageFile.getOriginalFilename())
                .imageUrl(imageUrl)
                .build();
        ReportImage reportImage = requestDto.toEntity(report);
        reportImageRepository.save(reportImage);
    }

    public void updateImage(Report report, List<MultipartFile> newImages, List<ReportImage> oldImages, String imageDirName){
        if (oldImages.isEmpty()){
            // 원래 게시글에 이미지가 없었다면
            for (MultipartFile newImage : newImages){
                saveImage(report, newImage, imageDirName);
            }
        } else {
            // 원래 게시글에 이미지가 있다면

            // 기존 게시글의 이미지 url을 받아오자
            List<String> imageUrls = oldImages.stream()
                    .map(ReportImage::getImageUrl)
                    .collect(Collectors.toList());
            System.out.println("imageUrls : " + imageUrls);
            // String s3BaseUrl = "https://bonobono.s3.ap-northeast-2.amazonaws.com";

            // 새롭게 받아온 이미지들을 하나씩 확인해보자
            String checkKey = imageDirName +"/";
            for (MultipartFile newImage : newImages){
                // 수정된 게시글의 이미지들이 기존의 이미지와 겹치는 지 확인
                String imageName = newImage.getOriginalFilename();
                if(imageName != null) {
                    boolean isChecked = imageName.contains(checkKey);
                    if (isChecked) {
                        // 이미 S3 버킷에 저장되어 있는 이미지이면
                        String newImageUrl = newImage.getOriginalFilename().split(checkKey)[1];

                        // S3 버킷에 해당 이미지가 있는지 확인
                        boolean isContained = imageUrls.contains(newImageUrl);
                        if (!isContained) {
                            // S3 버킷에도 없는 이미지 이면 저장
                            saveImage(report, newImage, imageDirName);
                        } else {

                            // S3 버킷에 있는 이미지이면 비교대상 기존 이미지 리스트중 삭제 (또 저장안해도 되니까)
                            imageUrls.remove(newImageUrl);
                        }
                    } else {

                        // S3 버킷에 없는 이미지는 저장
                        saveImage(report, newImage, imageDirName);
                    }
                }
            }

            // 기존 이미지 중에 새롭게 받아온 이미지들 중 없다면 다 삭제
            if(!imageUrls.isEmpty()){
                for(String imageUrl : imageUrls){
                    ReportImage reportImage = reportImageRepository.findByReportAndImageUrl(report, imageUrl);
                    System.out.println("imageUrl : " + imageUrl);
                    awsS3Service.delete(reportImage.getImageUrl(), imageDirName);
                    reportImageRepository.delete(reportImage);
                }
            }
        }
    }

    public void deleteImage(List<ReportImage> reportImages, String dirName){
        // S3 이미지 삭제 후 DB에서 이미지 삭제
        for (ReportImage reportImage : reportImages){
            awsS3Service.delete(reportImage.getImageUrl(), dirName);
            reportImageRepository.delete(reportImage);
        }
    }
}
