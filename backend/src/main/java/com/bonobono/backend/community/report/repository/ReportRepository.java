package com.bonobono.backend.community.report.repository;

import com.bonobono.backend.community.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("SELECT r FROM Report r ORDER BY r.id DESC")
    List<Report> findAllDesc();
}
