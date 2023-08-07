package com.bonobono.backend.community.report.repository;

import com.bonobono.backend.community.report.entity.ReportComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportCommentRepository extends JpaRepository<ReportComment, Long> {

    List<ReportComment> findAllByReportIdAndParentCommentIsNull(Long reportId);

}
