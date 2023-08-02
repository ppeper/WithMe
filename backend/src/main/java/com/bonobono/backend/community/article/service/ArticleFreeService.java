package com.bonobono.backend.community.article.service;

import com.bonobono.backend.community.article.dto.req.ArticleFreeSaveRequestDto;
import com.bonobono.backend.community.article.dto.req.ArticleFreeUpdateRequestDto;
import com.bonobono.backend.community.article.dto.req.ArticleImageRequestDto;
import com.bonobono.backend.community.article.dto.res.ArticleFreeDetailResponseDto;
import com.bonobono.backend.community.article.dto.res.ArticleFreeListResponseDto;
import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.entity.ArticleImage;
import com.bonobono.backend.community.article.repository.ArticleImageRepository;
import com.bonobono.backend.community.article.repository.ArticleLikeRepository;
import com.bonobono.backend.community.article.repository.ArticleRepository;
import com.bonobono.backend.member.entity.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import com.bonobono.backend.global.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleFreeService {

    private final ArticleRepository articleRepository;

    private final MemberRepository memberRepository;

    private final ArticleImageRepository articleImageRepository;

    private final ArticleLikeRepository articleLikeRepository;

    private final AwsS3Service awsS3Service;


    // 자유게시판 글 저장
    @Transactional
    public Long save(ArticleFreeSaveRequestDto requestDto, List<MultipartFile> imageFiles){
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new NoSuchElementException("해당 ID 값을 가진 Member가 없습니다 + id = " + requestDto.getMemberId()));

        Article article  = articleRepository.save(requestDto.toEntity(member));
        if (imageFiles != null) {
            for (MultipartFile imageFile : imageFiles) {
                String imageUrl = awsS3Service.upload(imageFile, "article_images").getPath();
                ArticleImage articleImage = saveImage(imageFile.getOriginalFilename(), imageUrl).toEntity(article);
                articleImageRepository.save(articleImage);
            }

        }

        return article.getId();
    }

    private ArticleImageRequestDto saveImage(String imageName, String imageUrl){
        return ArticleImageRequestDto.builder()
                .imageName(imageName)
                .imageUrl(imageUrl)
                .build();
    }

    // 자유게시판 전체글 내림차순
    @Transactional(readOnly = true) // readOnly를 사용하여 조회 기능만 남겨두어 조회속도가 개선
    public List<ArticleFreeListResponseDto> findAllDesc() {
        return articleRepository.findAllDesc().stream()
                .map(ArticleFreeListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 자유게시판 글 검색
    @Transactional(readOnly = true) // readOnly를 사용하여 조회 기능만 남겨두어 조회속도가 개선
    public List<ArticleFreeListResponseDto> search(String keyword) {
        return articleRepository.findByTitleContainingOrContentContaining(keyword, keyword).stream()
                .map(ArticleFreeListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 자유게시판 특정글 조회
    @Transactional
    public ArticleFreeDetailResponseDto findById(Long articleId){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + articleId));
        articleRepository.updateView(articleId);
        return new ArticleFreeDetailResponseDto(article);
    }

    // 자유게시판 특정 글 수정
    @Transactional
    public ArticleFreeDetailResponseDto update(Long articleId, ArticleFreeUpdateRequestDto requestDto){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + articleId));
        article.updateFree(requestDto.getTitle(), requestDto.getContent());
        return new ArticleFreeDetailResponseDto(article);
    }

    // 자유게시판 특정글 삭제
    @Transactional
    public void delete(Long articleId){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + articleId));
        articleRepository.delete(article);
    }

    /*
    // 자유게시판 좋아요
    @Transactional
    public boolean addLike(Long articleId, Member member){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + articleId));
        if(isNotAlreadyLike(member, article)){
            articleLikeRepository.save(new ArticleLike(member, article));
            return true;
        }
        articleLikeRepository.deleteArticleLikeByMemberAndArticle(member, article);
        return false;
    }
    // 해당 Member가 좋아요 눌렀는지 판단
    private boolean isNotAlreadyLike(Member member, Article article){
        return articleLikeRepository.findByMemberAndArticle(member, article).isEmpty();
    }
    */

}