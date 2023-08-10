package com.bonobono.backend.community.report.service;

import com.bonobono.backend.community.report.entity.Report;
import com.bonobono.backend.community.report.entity.ReportLike;
import com.bonobono.backend.community.report.repository.ReportLikeRepository;
import com.bonobono.backend.community.report.repository.ReportRepository;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReportLikeService {

    private final ReportRepository reportRepository;

    private final ReportLikeRepository reportLikeRepository;

    private final MemberRepository memberRepository;

    // 신고 게시글 좋아요
    @Transactional
    public void like(Long memberId, Long reportId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다. id =" + memberId));

        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID 값을 가진 게시글이 없습니다 + id = " + reportId));

        ReportLike reportLike = reportLikeRepository.findByReportAndMember(report, member);
        if(reportLike != null){
            // 이미 좋아요한 상태이면 취소
            reportLikeRepository.delete(reportLike);
        } else {
            ReportLike newReportLike = ReportLike.builder()
                    .report(report)
                    .member(member)
                    .build();
            reportLikeRepository.save(newReportLike);
        }
    }
}
