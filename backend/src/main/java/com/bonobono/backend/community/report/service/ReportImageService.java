package com.bonobono.backend.community.report.service;

import com.bonobono.backend.community.report.dto.req.ReportImageRequestDto;
import com.bonobono.backend.community.report.entity.Report;
import com.bonobono.backend.community.report.entity.ReportImage;
import com.bonobono.backend.community.report.repository.ReportImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReportImageService {

    private final ReportImageRepository reportImageRepository;

    public void saveImage(Report report, String imageName, String imageUrl){
        ReportImageRequestDto requestDto = ReportImageRequestDto.builder()
                .imageName(imageName)
                .imageUrl(imageUrl)
                .build();
        ReportImage reportImage = requestDto.toEntity(report);
        reportImageRepository.save(reportImage);
    }
}
