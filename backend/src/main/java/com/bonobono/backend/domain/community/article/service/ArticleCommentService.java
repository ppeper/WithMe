package com.bonobono.backend.domain.community.article.service;

import com.bonobono.backend.domain.community.article.dto.req.ArticleCommentRequestDto;
import com.bonobono.backend.domain.community.article.entity.Article;
import com.bonobono.backend.domain.community.article.entity.ArticleComment;
import com.bonobono.backend.domain.community.article.repository.ArticleCommentRepository;
import com.bonobono.backend.domain.community.article.repository.ArticleRepository;
import com.bonobono.backend.domain.member.entity.Member;
import com.bonobono.backend.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ArticleCommentService {

    private final ArticleRepository articleRepository;

    private final ArticleCommentRepository articleCommentRepository;

    private final MemberRepository memberRepository;

    // 댓글 작성하기
    @Transactional
    public Long save(ArticleCommentRequestDto requestDto) {
        Article article = articleRepository.findById(requestDto.getArticleId())
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id=" + requestDto.getArticleId()));
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(()-> new IllegalArgumentException("해당 멤버가 없습니다. id=" + requestDto.getMemberId()));
        ArticleComment articleComment = requestDto.toEntity(article, member);
                return articleCommentRepository.save(articleComment).getId();
    }

    // 댓글 삭제하기
    @Transactional
    public void delete(Long id){
        ArticleComment articleComment = articleCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + id));
        articleCommentRepository.delete(articleComment);
    }

}
