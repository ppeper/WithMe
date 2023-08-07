package com.bonobono.backend.community.report.service;

import com.bonobono.backend.community.report.dto.req.ReportImageRequestDto;
import com.bonobono.backend.community.report.entity.Report;
import com.bonobono.backend.community.report.entity.ReportImage;
import com.bonobono.backend.community.report.repository.ReportImageRepository;
import com.bonobono.backend.global.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public void deleteImage(ReportImage reportImage, String imageUrl, String dirName){
        awsS3Service.delete(imageUrl, dirName);
        reportImageRepository.delete(reportImage);
    }
}
