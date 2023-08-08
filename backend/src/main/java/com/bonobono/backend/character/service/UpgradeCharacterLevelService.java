package com.bonobono.backend.character.service;

import com.bonobono.backend.character.domain.OurCharacter;
import com.bonobono.backend.character.domain.UserCharacter;
import com.bonobono.backend.character.enumClass.CharacterLevelEnum;
import com.bonobono.backend.character.repository.OurCharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpgradeCharacterLevelService {

    private final OurCharacterRepository ourCharacterRepository;

    //메인케릭터와 메인케릭터의 경험치를 가져옴
    public void upgradeCharacter(UserCharacter userCharacter, Integer experience) {
        if (experience>=100) {
            //경험치가 100이상이면 다음 레벨로 진화
            CharacterLevelEnum newLevel = getNextLevel(userCharacter.getOurCharacter().getLevel());
            //해당 레벨의 ourCharacter뽑기
            OurCharacter upgradedCharacter = ourCharacterRepository.findByCharOrdIdAndLevel(userCharacter.getOurCharacter().getCharOrdId(), newLevel);

            if (upgradedCharacter!=null) {
                //이름과 level이 같은 캐릭터가 있다면 usercharacter에 ourcharacter를 update한다
                userCharacter.upgradeCharacter(upgradedCharacter);
            }

        }
    }

    private CharacterLevelEnum getNextLevel(CharacterLevelEnum currentLevel) {
        switch (currentLevel) {
            case LEVEL_1:
                return CharacterLevelEnum.LEVEL_2;
            case LEVEL_2:
                return CharacterLevelEnum.LEVEL_3;
            default:
                return currentLevel;
        }
    }
}
