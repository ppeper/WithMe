package com.bonobono.backend.community.article.service;

import com.bonobono.backend.community.article.dto.res.ArticleTogetherResponseDto;
import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleTogetherService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true) // readOnly를 사용하여 조회 기능만 남겨두어 조회속도가 개선
    public List<ArticleTogetherResponseDto> findAllDesc() {
        return articleRepository.findAllDesc().stream()
                .map(ArticleTogetherResponseDto::new)
                .collect(Collectors.toList());
    }

    public ArticleTogetherResponseDto findById(Long id){
        Article entity = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

        return new ArticleTogetherResponseDto(entity);
    }
}
