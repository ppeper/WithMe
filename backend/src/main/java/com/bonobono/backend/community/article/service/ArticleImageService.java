package com.bonobono.backend.community.article.service;

import com.bonobono.backend.community.article.dto.req.ArticleImageRequestDto;
import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.entity.ArticleImage;
import com.bonobono.backend.community.article.repository.ArticleImageRepository;
import com.bonobono.backend.global.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleImageService {

    private final ArticleImageRepository articleImageRepository;

    private final AwsS3Service awsS3Service;

    // 게시글 이미지 저장
    public void saveImage(Article article, MultipartFile imageFile, String imageDirName){
        String imageUrl = awsS3Service.upload(imageFile, imageDirName).getPath();
        ArticleImageRequestDto requestDto = ArticleImageRequestDto.builder()
                .imageName(imageFile.getOriginalFilename())
                .imageUrl(imageUrl)
                .build();
        ArticleImage articleImage = requestDto.toEntity(article);
        articleImageRepository.save(articleImage);
    }

    // 게시글 이미지 수정
    public void updateImage(Article article, List<MultipartFile> newImages, List<ArticleImage> oldImages, String imageDirName){
        // 해당 게시글 articleImage 리스트
        // 비교할 때 "https://bonobono.s3.ap-northeast-2.amazonaws.com" 포함되면 비교하기
        // imageUrl로 비교해서 있는 값이면 통과 없는 값이면 저장
        // 남은 값들은 삭제
        List<String> imageUrls = oldImages.stream()
                .map(ArticleImage::getImageUrl)
                .collect(Collectors.toList());

        String s3BaseUrl = "https://bonobono.s3.ap-northeast-2.amazonaws.com";
        for (MultipartFile newImage : newImages){
            boolean isChecked = newImage.getOriginalFilename().contains(s3BaseUrl);
            if(isChecked){
                String newImageUrl = newImage.getOriginalFilename().split(imageDirName + "/" )[1];
                boolean isContained =  imageUrls.contains(newImageUrl);
                if(!isContained){
                    saveImage(article, newImage, imageDirName);
                } else {
                    imageUrls.remove(newImageUrl);
                }
            } else {
                saveImage(article, newImage, imageDirName);
            }
        }

        for (String imageUrl :imageUrls) {
            ArticleImage articleImage = articleImageRepository.findByArticleAndImageUrl(article, imageUrl);
            deleteImage(articleImage, imageUrl, imageDirName);
        }

        // 남은 imageUrls 삭제
    }

    // 게시글 이미지 삭제
    public void deleteImage(ArticleImage articleImage, String imageUrl, String dirName){
        // S3 이미지 삭제 후 DB에서 이미지 삭제
        awsS3Service.delete(imageUrl, dirName);
        articleImageRepository.delete(articleImage);
    }


}
