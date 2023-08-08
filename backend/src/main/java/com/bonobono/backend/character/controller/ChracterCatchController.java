package com.bonobono.backend.character.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catch")
public class ChracterCatchController {
    //캐릭터 잡기용 ox퀴즈 넘겨주기

    //정답 체크하고 맞으면 도감에 넣기(save)

}
