package com.bonobono.backend.community.report.service;

import com.bonobono.backend.community.report.dto.req.ReportCommentRequestDto;
import com.bonobono.backend.community.report.dto.res.ReportCommentResponseDto;
import com.bonobono.backend.community.report.entity.Report;
import com.bonobono.backend.community.report.entity.ReportComment;
import com.bonobono.backend.community.report.repository.ReportCommentRepository;
import com.bonobono.backend.community.report.repository.ReportRepository;
import com.bonobono.backend.fcm.dto.FCMReportNotificationRequestDto;
import com.bonobono.backend.fcm.service.FCMNotificationService;
import com.bonobono.backend.global.exception.UserNotAuthorizedException;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.enumtype.Role;
import com.bonobono.backend.member.repository.MemberRepository;
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

    private final MemberRepository memberRepository;

    private final FCMNotificationService fcmNotificationService;

    // 신고게시글 댓글, 대댓글 작성하기
    @Transactional
    public ReportCommentResponseDto save(Long memberId, Long reportId, ReportCommentRequestDto requestDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다. id =" + memberId));

        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + reportId));

        boolean isAuthor = member.getId().equals(report.getMember().getId());
        boolean isAdmin = member.getRole().stream().anyMatch(authority -> authority.getRole().equals(Role.ADMIN.toString()));

        if (!isAuthor && !isAdmin) {
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

        if (!memberId.equals(report.getMember().getId())) {
            FCMReportNotificationRequestDto fcmReportNotificationRequestDto = FCMReportNotificationRequestDto.builder()
                    .memberId(report.getMember().getId())
                    .title("게시물에 댓글이 작성되었습니다.")
                    .body(requestDto.getContent())
                    .reportId(reportId)
                    .build();

            fcmNotificationService.sendReportNotificationByToken(fcmReportNotificationRequestDto);
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
    public void update(Long memberId, Long reportId, Long commentId, ReportCommentRequestDto requestDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다. id =" + memberId));

        if (!reportRepository.existsById(reportId)) {
            throw new IllegalArgumentException("해당 게시글이 없습니다. id=" + reportId);
        }
        ReportComment reportComment = reportCommentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다. id=" + commentId));
        if(member.getId().equals(reportComment.getMember().getId())) {
            reportComment.updateComment(requestDto.getContent());
        } else {
            throw new UserNotAuthorizedException("해당 유저는 댓글 작성자가 아닙니다.");
        }
    }

    // 댓글 삭제하기
    @Transactional
    public void delete(Long memberId, Long reportId, Long commentId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다. id =" + memberId));

        if (!reportRepository.existsById(reportId)) {
            throw new IllegalArgumentException("해당 게시글이 없습니다. id=" + reportId);
        }
        ReportComment reportComment = reportCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + reportId));
        if(member.getId().equals(reportComment.getMember().getId())) {
            reportCommentRepository.delete(reportComment);
        } else {
            throw new UserNotAuthorizedException("해당 멤버는 댓글 작성자가 아닙니다.");
        }
    }
}
