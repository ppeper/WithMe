package com.bonobono.backend.character.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class UserChracterRequestDto {
    private String custom_name;
    private boolean is_main;
    private Long chracterId; //도감의 모든 캐릭터들의 id를 가져옴
    private Long memberId;// 현재 밈버 id

    @Builder
    public UserChracterRequestDto(String custom_name, boolean is_main, Long chracterId, Long memberId){
        this.chracterId=chracterId;
        this.custom_name=custom_name;
        this.is_main=is_main;
        this.memberId=memberId;
    }


}
