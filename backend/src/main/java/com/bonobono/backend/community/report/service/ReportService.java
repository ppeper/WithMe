package com.bonobono.backend.community.report.service;

import com.bonobono.backend.community.report.dto.ReportRequestdto;
import com.bonobono.backend.community.report.dto.ReportResponseDto;
import com.bonobono.backend.community.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public List<ReportResponseDto> findAllDesc(){
        return reportRepository.findAllDesc().stream()
                .map(ReportResponseDto::new)
                .collect(Collectors.toList());
    }

    public Long save(ReportRequestdto requestDto){
        return reportRepository.save(requestDto.toEntity()).getId();
    }
}
