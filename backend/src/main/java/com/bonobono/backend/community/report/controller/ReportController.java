package com.bonobono.backend.community.report.controller;

import com.bonobono.backend.community.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/community/report")
public class ReportController {

    private final ReportService reportService;


}
