package com.bonobono.backend.character.dto.catchCharacter;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NowPositionRequestDto {
    //현재 위치 정보 name(해수욕장이름)으로 받기? 아님 로컬에 해수욕장 id를 넣고, id를 받기??
    private String name;

    @Builder
    public NowPositionRequestDto(String name) {
        this.name=name;
    }
}
