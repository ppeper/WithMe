package com.bonobono.backend.character.service;

import com.bonobono.backend.character.domain.OurCharacter;
import com.bonobono.backend.character.domain.UserCharacter;
import com.bonobono.backend.character.enumClass.CharacterLevelEnum;
import com.bonobono.backend.character.repository.UserCharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpgradeCharacterLevelService {

    private final UserCharacterRepository userCharacterRepository;

    public void upgradeCharacter(UserCharacter userCharacter, Integer experience) {
        if (experience>=100) {
            //경험치가 100이상이면 다음 레벨로 넘어가기
            CharacterLevelEnum newLevel = getNextLevel(userCharacter.getOurCharacter().getLevel());
            //해당 레벨의 ourCharacter뽑기
            OurCharacter upgradedCharacter = OurCharacter.getCharacterByLevel(newLevel);
            if (upgradedCharacter!=null) {
                //이름과 level이 같은 걸 조회해서 바꾼다(update한다)
                userCharacterRepository.upgradeCharacter(userCharacter.getId(), upgradedCharacter.getName(), upgradedCharacter.getLevel());
//                userCharacter.
            }
//            this.experience-=100;
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
