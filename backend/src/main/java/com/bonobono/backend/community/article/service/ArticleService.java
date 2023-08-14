package com.bonobono.backend.community.article.service;

import com.bonobono.backend.community.article.dto.req.ArticleSaveRequestDto;
import com.bonobono.backend.community.article.dto.req.ArticleUpdateRequestDto;
import com.bonobono.backend.community.article.dto.res.ArticleCommentResponseDto;
import com.bonobono.backend.community.article.dto.res.ArticleDetailResponseDto;
import com.bonobono.backend.community.article.dto.res.ArticleListResponseDto;
import com.bonobono.backend.community.article.dto.res.ArticleNoticeResponseDto;
import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.entity.ArticleImage;
import com.bonobono.backend.community.article.enumclass.ArticleType;
import com.bonobono.backend.community.article.repository.ArticleRepository;
import com.bonobono.backend.global.exception.UserNotAuthorizedException;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final MemberRepository memberRepository;

    private final ArticleCommentService articleCommentService;

    private final ArticleImageService articleImageService;

    private final String imageDirName = "article_images"; // S3 폴더이름

    // 게시글 글 저장
    @Transactional
    public void save(ArticleType type, Long memberId, ArticleSaveRequestDto requestDto, List<MultipartFile> imageFiles){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다. id =" + memberId));

        Article article  = articleRepository.save(requestDto.toEntity(type, member));
        if (imageFiles != null) {
            for (MultipartFile imageFile : imageFiles) {
                articleImageService.saveImage(article, imageFile, imageDirName);
            }
        }
    }

    // 게시글 전체글 내림차순
    @Transactional(readOnly = true) // readOnly를 사용하여 조회 기능만 남겨두어 조회속도가 개선
    public List<ArticleListResponseDto> findAllDesc(ArticleType type) {
        return articleRepository.findAllByTypeOrderByIdDesc(type).stream()
                .map(ArticleListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 게시글 글 검색
    @Transactional(readOnly = true) // readOnly를 사용하여 조회 기능만 남겨두어 조회속도가 개선
    public List<ArticleListResponseDto> search(ArticleType type, String keyword) {
        return articleRepository.findByTypeAndTitleContainingOrContentContaining(type, keyword, keyword).stream()
                .map(ArticleListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 게시글 특정글 조회
    @Transactional
    public ArticleDetailResponseDto findById(ArticleType type, Long memberId, Long articleId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다. id =" + memberId));

        Article article = articleRepository.findByIdAndType(articleId, type)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + articleId));
        articleRepository.updateView(articleId);
        List<ArticleCommentResponseDto> comments = articleCommentService.findByArticleId(member, articleId);
        return new ArticleDetailResponseDto(article, member, comments);
    }

    // 공지사항 조회
    @Transactional
    public ArticleNoticeResponseDto findNoticeById(ArticleType type, Long articleId){
        Article article = articleRepository.findByIdAndType(articleId, type)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + articleId));
        articleRepository.updateView(articleId);
        return new ArticleNoticeResponseDto(article);
    }

    // 게시글 특정 글 수정
    @Transactional
    public void update(Long memberId, Long articleId, ArticleUpdateRequestDto requestDto, List<MultipartFile> newImages){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + articleId));
        if(memberId.equals(article.getMember().getId())) {
            if (!newImages.isEmpty()) {
                List<ArticleImage> oldImages = article.getImages();
                articleImageService.updateImage(article, newImages, oldImages, imageDirName);
            }
            article.updateArticle(requestDto.getTitle(), requestDto.getContent(), requestDto.getUrlTitle(), requestDto.getUrl());
        } else {
            throw new UserNotAuthorizedException("해당 멤버는 게시글 작성자가 아닙니다.");
        }
    }

    // 함께 게시글 모집 완료
    @Transactional
    public void updateRecruitStatus(Long memberId, Long articleId){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + articleId));
        if(memberId.equals(article.getMember().getId())) {
            article.updateRecruitStatus();
        } else {
            throw new UserNotAuthorizedException("해당 멤버는 게시글 작성자가 아닙니다.");
        }
    }

    // 게시글 특정글 삭제
    @Transactional
    public void delete(Long memberId, Long articleId){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + articleId));
        if(memberId.equals(article.getMember().getId())) {
            if(article.getImages() != null) {
                articleImageService.deleteImage(article.getImages(), imageDirName);
            }
            articleRepository.delete(article);
        } else {
            throw new UserNotAuthorizedException("해당 멤버는 게시글 작성자가 아닙니다.");
        }
    }


}