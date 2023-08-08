package com.bonobono.backend.community.report.service;

import com.bonobono.backend.community.report.dto.req.ReportSaveRequestDto;
import com.bonobono.backend.community.report.dto.req.ReportUpdateRequestDto;
import com.bonobono.backend.community.report.dto.res.ReportCommentResponseDto;
import com.bonobono.backend.community.report.dto.res.ReportDetailResponseDto;
import com.bonobono.backend.community.report.dto.res.ReportListResponseDto;
import com.bonobono.backend.community.report.entity.Report;
import com.bonobono.backend.community.report.entity.ReportImage;
import com.bonobono.backend.community.report.repository.ReportRepository;
import com.bonobono.backend.global.exception.UserNotAuthorizedException;
import com.bonobono.backend.global.service.AwsS3Service;
import com.bonobono.backend.member.domain.Authority;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.enumtype.Role;
import java.util.Collection;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final ReportImageService reportImageService;
    private final ReportCommentService reportCommentService;
    private final AwsS3Service awsS3Service;

    private final String imageDirName = "report_images"; // S3 폴더이름

    // 신고 게시글 저장
    @Transactional
    public void save(Member member, ReportSaveRequestDto requestDto, List<MultipartFile> imageFiles){
        Report report  = reportRepository.save(requestDto.toEntity(member));
        if (imageFiles != null) {
            for (MultipartFile imageFile : imageFiles) {
                reportImageService.saveImage(report, imageFile, imageDirName);
            }
        }
    }

    // 신고 게시글 전체글 내림차순
    @Transactional(readOnly = true)
    public List<ReportListResponseDto> findAllDesc() {
        return reportRepository.findAllByOrderByIdDesc().stream()
                .map(ReportListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 신고 게시글 글 검색
    @Transactional(readOnly = true)
    public List<ReportListResponseDto> search(String keyword) {
        return reportRepository.findAllByTitleContainingOrContentContaining(keyword, keyword).stream()
                .map(ReportListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 신고 게시글 특정글 조회
    @Transactional
    public ReportDetailResponseDto findById(Member member, Long reportId){
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + reportId));
        reportRepository.updateView(reportId);
        List<ReportCommentResponseDto> comments = reportCommentService.findByReportId(member, reportId);
        return new ReportDetailResponseDto(report, member, comments);
    }

    // 신고 게시글 특정 글 수정
    @Transactional
    public void update(Member member, Long reportId, ReportUpdateRequestDto requestDto, List<MultipartFile> imageFiles){
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + reportId));
        if(member.getId() == report.getMember().getId()) {
            report.updateReport(requestDto.getTitle(), requestDto.getContent(), requestDto.getLatitude(), requestDto.getLongitude());
            if (imageFiles != null) {
                for (MultipartFile imageFile : imageFiles) {
                    reportImageService.saveImage(report, imageFile, imageDirName);
                }
            }
        } else {
            throw new UserNotAuthorizedException("해당 멤버는 게시글 작성자가 아닙니다.");
        }
    }

    // (관리자) 신고 게시글 처리완료
    @Transactional
    public void updateRecruitStatus(Member member, Long reportId){
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + reportId));


        if(member.getRole().stream().anyMatch(authority -> authority.getRole().equals(Role.ADMIN))) {
            report.updateAdminConfirmStatus();
        } else {
            throw new UserNotAuthorizedException("해당 멤버는 관리자가 아닙니다.");
        }
    }

    // 신고 게시글 특정글 삭제
    @Transactional
    public void delete(Member member, Long reportId){
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + reportId));
        if(member.getId() == report.getMember().getId()) {
            List<ReportImage> reportImages = report.getImages();
            for (ReportImage reportImage : reportImages) {
                reportImageService.deleteImage(reportImage, reportImage.getImageUrl(), imageDirName);
            }
            reportRepository.delete(report);
        } else {
            throw new UserNotAuthorizedException("해당 멤버는 게시글 작성자가 아닙니다.");
        }
    }

}
