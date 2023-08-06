package com.bonobono.backend.community.report.repository;

import com.bonobono.backend.community.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {


}
