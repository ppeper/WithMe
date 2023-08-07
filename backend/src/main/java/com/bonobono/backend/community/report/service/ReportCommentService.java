package com.bonobono.backend.community.report.service;

import com.bonobono.backend.community.report.dto.req.ReportCommentRequestDto;
import com.bonobono.backend.community.report.dto.res.ReportCommentResponseDto;
import com.bonobono.backend.community.report.entity.Report;
import com.bonobono.backend.community.report.entity.ReportComment;
import com.bonobono.backend.community.report.repository.ReportCommentRepository;
import com.bonobono.backend.community.report.repository.ReportRepository;
import com.bonobono.backend.global.exception.UserNotAuthorizedException;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.enumtype.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportCommentService {

    private final ReportRepository reportRepository;

    private final ReportCommentRepository reportCommentRepository;

    // 신고게시글 댓글, 대댓글 작성하기
    @Transactional
    public ReportCommentResponseDto save(Member member, Long reportId, ReportCommentRequestDto requestDto) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + reportId));

        if (member.getId() != report.getMember().getId() || member.getRole() != Role.ADMIN) {
            throw new UserNotAuthorizedException("해당 멤버는 게시글 작성자 또는 관리자가 아니기에 댓글을 작성할 수 없습니다.");
        }

        ReportComment parentComment = null;
        if (requestDto.getParentCommentId() != null) {
            parentComment = reportCommentRepository.findById(requestDto.getParentCommentId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + requestDto.getParentCommentId()));
        }
        ReportComment reportComment = reportCommentRepository.save(requestDto.toEntity(report, member, parentComment));
        if (parentComment != null) {
            parentComment.addChildComment(reportComment);
        }
        return new ReportCommentResponseDto(reportComment, member);
    }


    // 신고게시글 댓글 조회하기
    @Transactional
    public List<ReportCommentResponseDto> findByReportId(Member member, Long reportId){
        return reportCommentRepository.findAllByReportIdAndParentCommentIsNull(reportId).stream()
                .map(reportComment -> new ReportCommentResponseDto(reportComment, member))
                .collect(Collectors.toList());
    }

    // 댓글 수정하기
    @Transactional
    public void update(Member member, Long reportId, Long commentId, ReportCommentRequestDto requestDto) {
        if (!reportRepository.existsById(reportId)) {
            throw new IllegalArgumentException("해당 게시글이 없습니다. id=" + reportId);
        }
        ReportComment reportComment = reportCommentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다. id=" + commentId));
        if(member.getId() == reportComment.getMember().getId()) {
            reportComment.updateComment(requestDto.getContent());
        } else {
            throw new UserNotAuthorizedException("해당 유저는 댓글 작성자가 아닙니다.");
        }
    }

    // 댓글 삭제하기
    @Transactional
    public void delete(Member member, Long reportId, Long commentId){
        if (!reportRepository.existsById(reportId)) {
            throw new IllegalArgumentException("해당 게시글이 없습니다. id=" + reportId);
        }
        ReportComment reportComment = reportCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + reportId));
        if(member.getId() == reportComment.getMember().getId()) {
            reportCommentRepository.delete(reportComment);
        } else {
            throw new UserNotAuthorizedException("해당 멤버는 댓글 작성자가 아닙니다.");
        }
    }
}
