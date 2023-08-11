package com.bonobono.backend.community.report.service;

import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.entity.ArticleImage;
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

    private final AwsS3Service awsS3Service;
    private final ReportImageRepository reportImageRepository;

    public void saveImage(Report report, MultipartFile imageFile, String imageDirName){
        String imageUrl = awsS3Service.upload(imageFile, imageDirName).getPath();
        ReportImageRequestDto requestDto = ReportImageRequestDto.builder()
                .imageName(imageFile.getOriginalFilename())
                .imageUrl(imageUrl)
                .build();
        ReportImage reportImage = requestDto.toEntity(report);
        reportImageRepository.save(reportImage);
    }

    public void updateImage(Report report, List<MultipartFile> newImages, List<ArticleImage> oldImages, String imageDirName){
        List<String> imageUrls = oldImages.stream()
                .map(ReportImage::getImageUrl)
                .collect(Collectors.toList());

        String s3BaseUrl = "https://bonobono.s3.ap-northeast-2.amazonaws.com";
        for (MultipartFile newImage : newImages){
            boolean isChecked = newImage.getOriginalFilename().contains(s3BaseUrl);
            if(isChecked){
                String newImageUrl = newImage.getOriginalFilename().split(imageDirName + "/" )[1];
                boolean isContained =  imageUrls.contains(newImageUrl);
                if(!isContained){
                    saveImage(report, newImage, imageDirName);
                } else {
                    imageUrls.remove(newImageUrl);
                }
            } else {
                saveImage(report, newImage, imageDirName);
            }
        }

        for (String imageUrl :imageUrls) {
            ReportImage reportImage = reportImageRepository.findByReportAndImageUrl(report, imageUrl);
            deleteImage(reportImage, imageUrl, imageDirName);
        }

        // 남은 imageUrls 삭제
    }

    public void deleteImage(ReportImage reportImage, String imageUrl, String dirName){
        awsS3Service.delete(imageUrl, dirName);
        reportImageRepository.delete(reportImage);
    }
}
