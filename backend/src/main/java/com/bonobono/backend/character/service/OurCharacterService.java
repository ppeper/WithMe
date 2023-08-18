package com.bonobono.backend.character.service;

import com.bonobono.backend.character.domain.LocationOurCharacter;
import com.bonobono.backend.character.domain.OurCharacter;
import com.bonobono.backend.character.dto.catchCharacter.NowPositionRequestDto;
import com.bonobono.backend.character.dto.catchCharacter.OurChacracterWithSeaResponseDto;
import com.bonobono.backend.character.repository.LocationOurCharacterRepository;
import com.bonobono.backend.character.repository.OurCharacterRepository;
import com.bonobono.backend.dailymission.repository.OXQuizProblemRepository;
import com.bonobono.backend.global.exception.LocationOurChracterNotFoundException;
import com.bonobono.backend.global.util.SecurityUtil;
import com.bonobono.backend.location.entity.Location;
import com.bonobono.backend.location.repository.LocationRepository;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@RequiredArgsConstructor
@Service
public class OurCharacterService {

    private final LocationOurCharacterRepository locationOurCharacterRepository;
    private final LocationRepository locationRepository;
    private final MemberRepository memberRepository;
    private final OurCharacterRepository ourCharacterRepository;

    @Transactional(readOnly = true)
    public List<OurChacracterWithSeaResponseDto> SeaOurFindList(NowPositionRequestDto nowPositionRequestDto) {
        //위경도로 해변위치 찾아온다
        Member member = memberRepository
                .findById(SecurityUtil.getLoginMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));


        Location location = locationRepository.findByName(nowPositionRequestDto.getName())
                .orElseThrow(()->new IllegalArgumentException("해당 해변이 존재하지 않습니다 +location"+ nowPositionRequestDto.getName()));

        //해변위치를 바탕으로 해변의 캐릭터들을 리스트로 받아옴
        List<LocationOurCharacter> locationOurCharacterList = locationOurCharacterRepository.findByLocation_id(location.getId());

        List<OurChacracterWithSeaResponseDto> bound = new ArrayList<>();
        if (!locationOurCharacterList.isEmpty()) {

            if (location.getId()==5) {
                Long[] idx = new Long[]{11L, 14L, 17L};
                double[] latitude = new double[] {36.1069552, 36.1069552, 36.1069552};
                double[] longitude = new double[] {128.416656, 128.4171194, 128.4171194};

                for (int i=0; i<3; i++) {
                    int finalI = i;
                    OurCharacter ourCharacter = ourCharacterRepository.findById(idx[i])
                            .orElseThrow(()-> new IllegalArgumentException("해당 캐릭터가 존재하지 않습니다 +character id" + idx[finalI]));
                    LocationOurCharacter locationOurCharacter = locationOurCharacterRepository.findByLocationAndOurCharacter(location, ourCharacter);
                    bound.add(new OurChacracterWithSeaResponseDto(latitude[i], longitude[i], locationOurCharacter));
                }
            }
            else {
                //리스트의 캐릭터의 하나하나의 위치를, 그 해변의 위경도를 중심으로 랜덤으로 지정해서  OurChacracterWithSeaResponseDto에 넣는다
                double incrementLatitude = 0.000000000001;
                double incrementLogitude = 0.0000000001;

                double leftLongtitude = location.getLeftLongitude();
                double rightLongtitude = location.getRightLongitude();
                double upperLatitude = location.getUpperLatitude();
                double lowerLatitude = location.getLowerLatitude();


                Random random = new Random();

                //캐릭터 id, 위도, 경도를 dto에 저장
                for (LocationOurCharacter locationOurCharacter : locationOurCharacterList) {
                    double randomLatitude = lowerLatitude + (upperLatitude - lowerLatitude - incrementLatitude) * random.nextDouble() + incrementLatitude * random.nextDouble();
                    double randomLongitude = leftLongtitude + (rightLongtitude - leftLongtitude - incrementLogitude) * random.nextDouble() + incrementLogitude * random.nextDouble();

                    bound.add(new OurChacracterWithSeaResponseDto(randomLatitude, randomLongitude, locationOurCharacter));
                }
            }
        }
        else {
            throw new LocationOurChracterNotFoundException("해당 해변에 캐릭터리스트가 없습니다 + 해변ID:" + location.getId());
        }

        return bound;
    }






}
