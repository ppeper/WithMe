package com.bonobono.backend.community.report.dto.res;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportLikeResponseDto {

    int likes;
    boolean isLiked;

    public ReportLikeResponseDto(int likes, boolean isLiked) {
        this.likes = likes;
        this.isLiked = isLiked;
    }
}