package com.bonobono.backend.community.report.repository;

import com.bonobono.backend.community.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    // 신고 게시글
    List<Report> findAllByOrderByIdDesc();

    List<Report> findAllByTitleContainingOrContentContaining(String titleKeyword, String contentKeyword);

    // 게시글 조회수 1 증가 기능
    @Modifying
    @Query("update Report r set r.views = r.views + 1 where r.id = :reportId ")
    void updateView(@Param("reportId") Long reportId);
}
