package com.bonobono.backend.community.report.service;

import com.bonobono.backend.community.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;

}
