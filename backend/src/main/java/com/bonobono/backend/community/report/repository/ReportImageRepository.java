package com.bonobono.backend.community.report.repository;

import com.bonobono.backend.community.report.entity.Report;
import com.bonobono.backend.community.report.entity.ReportImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportImageRepository extends JpaRepository<ReportImage, Long> {

    ReportImage findByReportAndImageUrl(Report report, String ImageUrl);
}
