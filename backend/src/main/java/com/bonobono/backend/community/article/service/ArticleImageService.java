package com.bonobono.backend.community.article.service;

import com.bonobono.backend.community.article.dto.req.ArticleImageRequestDto;
import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.entity.ArticleImage;
import com.bonobono.backend.community.article.repository.ArticleImageRepository;
import com.bonobono.backend.global.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class ArticleImageService {

    private final ArticleImageRepository articleImageRepository;

    private final AwsS3Service awsS3Service;

    public void saveImage(Article article, MultipartFile imageFile, String imageDirName){
        String imageUrl = awsS3Service.upload(imageFile, imageDirName).getPath();
        ArticleImageRequestDto requestDto = ArticleImageRequestDto.builder()
                .imageName(imageFile.getOriginalFilename())
                .imageUrl(imageUrl)
                .build();
        ArticleImage articleImage = requestDto.toEntity(article);
        articleImageRepository.save(articleImage);
    }

    public void deleteImage(ArticleImage articleImage, String imageUrl, String dirName){
        // S3 이미지 삭제 후 DB에서 이미지 삭제
        awsS3Service.delete(imageUrl, dirName);
        articleImageRepository.delete(articleImage);
    }


}
