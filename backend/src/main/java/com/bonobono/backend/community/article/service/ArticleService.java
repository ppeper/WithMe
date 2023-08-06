package com.bonobono.backend.community.article.service;

import com.bonobono.backend.community.article.dto.req.ArticleSaveRequestDto;
import com.bonobono.backend.community.article.dto.req.ArticleUpdateRequestDto;
import com.bonobono.backend.community.article.dto.res.ArticleCommentResponseDto;
import com.bonobono.backend.community.article.dto.res.ArticleDetailResponseDto;
import com.bonobono.backend.community.article.dto.res.ArticleListResponseDto;
import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.enumclass.ArticleType;
import com.bonobono.backend.community.article.repository.ArticleImageRepository;
import com.bonobono.backend.community.article.repository.ArticleRepository;
import com.bonobono.backend.global.exception.UserNotAuthorizedException;
import com.bonobono.backend.global.service.AwsS3Service;
import com.bonobono.backend.member.domain.Member;
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

    private final ArticleImageRepository articleImageRepository;

    private final ArticleCommentService articleCommentService;

    private final ArticleImageService articleImageService;

    private final AwsS3Service awsS3Service;


    // 게시글 글 저장
    @Transactional
    public void save(ArticleType type, Member member, ArticleSaveRequestDto requestDto, List<MultipartFile> imageFiles){
        Article article  = articleRepository.save(requestDto.toEntity(type, member));
        if (imageFiles != null) {
            for (MultipartFile imageFile : imageFiles) {
                String imageUrl = awsS3Service.upload(imageFile, "article_images").getPath();
                articleImageService.saveImage(article, imageFile.getOriginalFilename(), imageUrl);
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
    public ArticleDetailResponseDto findById(ArticleType type, Member member, Long articleId){
        Article article = articleRepository.findByIdAndType(articleId, type)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + articleId));
        articleRepository.updateView(articleId);
        List<ArticleCommentResponseDto> comments = articleCommentService.findByArticleId(member, articleId);
        return new ArticleDetailResponseDto(article, member, comments);
    }

    // 게시글 특정 글 수정
    @Transactional
    public void update(Member member, Long articleId, ArticleUpdateRequestDto requestDto, List<MultipartFile> imageFiles){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + articleId));

        if(member.getId() == article.getMember().getId()) {
            article.updateArticle(requestDto.getTitle(), requestDto.getContent(), requestDto.getUrlTitle(), requestDto.getUrl());
            if (imageFiles != null) {
                for (MultipartFile imageFile : imageFiles) {
                    String imageUrl = awsS3Service.upload(imageFile, "article_images").getPath();
                    articleImageService.saveImage(article, imageFile.getOriginalFilename(), imageUrl);
                }
            }
        } else {
            throw new UserNotAuthorizedException("해당 멤버는 게시글 작성자가 아닙니다.");
        }
    }

    // 함께 게시글 모집 완료
    @Transactional
    public void updateRecruitStatus(Member member, Long articleId){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + articleId));
        if(member.getId() == article.getMember().getId()) {
            article.updateRecruitStatus();
        } else {
            throw new UserNotAuthorizedException("해당 멤버는 게시글 작성자가 아닙니다.");
        }
    }

    // 게시글 특정글 삭제
    @Transactional
    public void delete(Member member, Long articleId){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + articleId));
        if(member.getId() == article.getMember().getId()) {
            articleRepository.delete(article);
        } else {
            throw new UserNotAuthorizedException("해당 멤버는 게시글 작성자가 아닙니다.");
        }
    }


}