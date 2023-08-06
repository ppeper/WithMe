package com.bonobono.backend.auth.oauth;

import com.bonobono.backend.member.domain.Authority;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.enumtype.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@ToString
public class OAuthAttributes {

    private final Member member;

    private Map<String, Object> attributes;
    private String nameAttributesKey;
    private String name;
    private String nickname;
    private String email;
    private String profileImg;
    private String phoneNumber;

    public static OAuthAttributes of(String provider, String nameAttributesKey, Map<String, Object> attributes) {
        switch (provider) {
            case "google":
                return ofGoogle(nameAttributesKey, attributes);
            case "kakao":
                return ofKakao("email", attributes);
            case "naver":
                return ofNaver("id", attributes);
            default:
                throw new RuntimeException();
        }
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name(String.valueOf(attributes.get("name")))
                .email(String.valueOf(attributes.get("email")))
                .profileImg(String.valueOf(attributes.get("picture")))
                .attributes(attributes)
                .nameAttributesKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

        return OAuthAttributes.builder()
                .name(String.valueOf(kakaoAccount.get("name")))
                .email(String.valueOf(kakaoAccount.get("email")))
                .phoneNumber(String.valueOf(kakaoAccount.get("phone_number")))
                .nickname(String.valueOf(kakaoProfile.get("nickname")))
                .profileImg(String.valueOf(kakaoProfile.get("profile_image")))
                .attributes(attributes)
                .nameAttributesKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name(String.valueOf(response.get("name")))
                .nickname(String.valueOf(response.get("nickname")))
                .email(String.valueOf(response.get("email")))
                .profileImg(String.valueOf(response.get("profile_image")))
                .phoneNumber(String.valueOf(response.get("mobile")))
                .attributes(attributes)
                .nameAttributesKey(userNameAttributeName)
                .build();
    }

    Map<String, Object> converToMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", nameAttributesKey);
        map.put("key", nameAttributesKey);
        map.put("name", name);
        map.put("nickname", nickname);
        map.put("username", email);
        map.put("profileImg", profileImg);
        map.put("phoneNumber", phoneNumber);

        return map;
    }

    public Member toEntity() {

        Set<Authority> role = member.getRole();

        return Member.builder()
                .name(name)
                .nickname(nickname)
                .username(email)
                .phoneNumber(phoneNumber)
                .role(role)
                .build();

    }
}
