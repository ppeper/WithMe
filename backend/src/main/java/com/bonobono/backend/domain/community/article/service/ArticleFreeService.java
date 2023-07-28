package com.bonobono.backend.domain.community.article.service;

import com.bonobono.backend.domain.community.article.dto.req.ArticleFreeSaveRequestDto;
import com.bonobono.backend.domain.community.article.dto.res.ArticleFreeDetailResponseDto;
import com.bonobono.backend.domain.community.article.dto.res.ArticleFreeListResponseDto;
import com.bonobono.backend.domain.community.article.entity.Article;
import com.bonobono.backend.domain.community.article.repository.ArticleRepository;
import com.bonobono.backend.domain.member.entity.Member;
import com.bonobono.backend.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleFreeService {

    private final ArticleRepository articleRepository;

    private final MemberRepository memberRepository;

    // 자유게시판 전체글 내림차순
    @Transactional(readOnly = true) // readOnly를 사용하여 조회 기능만 남겨두어 조회속도가 개선
    public List<ArticleFreeListResponseDto> findAllDesc() {
        return articleRepository.findAllDesc().stream()
                .map(ArticleFreeListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 자유게시판 글 저장
    @Transactional
    public Long save(ArticleFreeSaveRequestDto requestDto){
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new NoSuchElementException("해당 ID 값을 가진 Member가 없습니다 + id = " + requestDto.getMemberId()));
        return articleRepository.save(requestDto.toEntity(member)).getId();
    }


    // 자유게시판 특정글 조회
    public ArticleFreeDetailResponseDto findById(Long id){
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));
        article.increaseViews(article.getViews());
        return new ArticleFreeDetailResponseDto(article);
    }

    // 자유게시판 특정글 삭제
    @Transactional
    public void delete(Long id){
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        articleRepository.delete(article);
    }

    // 자유게시판 특정 글 수정
    @Transactional
    public Long update(Long id, ArticleFreeSaveRequestDto requestDto){
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        article.updateFree(requestDto.getTitle(), requestDto.getContent(), requestDto.getImage());
        return id;
    }

    // 자유게시판 글 검색
    @Transactional(readOnly = true) // readOnly를 사용하여 조회 기능만 남겨두어 조회속도가 개선
    public List<ArticleFreeListResponseDto> search(String keyword) {
        return articleRepository.findByTitleContainingOrContentContaining(keyword, keyword).stream()
                .map(ArticleFreeListResponseDto::new)
                .collect(Collectors.toList());
    }

}