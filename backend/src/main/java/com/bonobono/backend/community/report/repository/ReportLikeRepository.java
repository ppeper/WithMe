package com.bonobono.backend.community.report.repository;

import com.bonobono.backend.community.report.entity.Report;
import com.bonobono.backend.community.report.entity.ReportLike;
import com.bonobono.backend.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportLikeRepository extends JpaRepository<ReportLike, Long> {

    ReportLike findByReportAndMember(Report report, Member member);
}
