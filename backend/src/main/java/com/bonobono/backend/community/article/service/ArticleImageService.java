package com.bonobono.backend.community.article.service;

import com.bonobono.backend.community.article.dto.req.ArticleImageRequestDto;
import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.entity.ArticleImage;
import com.bonobono.backend.community.article.repository.ArticleImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArticleImageService {

    private final ArticleImageRepository articleImageRepository;

    public void saveImage(Article article, String imageName, String imageUrl){
        ArticleImageRequestDto requestDto = ArticleImageRequestDto.builder()
                .imageName(imageName)
                .imageUrl(imageUrl)
                .build();
        ArticleImage articleImage = requestDto.toEntity(article);
        articleImageRepository.save(articleImage);
    }
}
