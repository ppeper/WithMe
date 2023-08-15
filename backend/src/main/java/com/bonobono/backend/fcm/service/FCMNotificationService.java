package com.bonobono.backend.fcm.service;

import com.bonobono.backend.fcm.dto.FCMArticleNotificationRequestDto;
import com.bonobono.backend.fcm.dto.FCMNotificationRequestDto;
import com.bonobono.backend.fcm.dto.FCMReportNotificationRequestDto;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.Token;
import com.bonobono.backend.member.repository.MemberRepository;
import com.bonobono.backend.member.repository.TokenRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FCMNotificationService {

    private final FirebaseMessaging firebaseMessaging;
    private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;

    public String sendNotificationByToken(FCMNotificationRequestDto requestDto){

        Optional<Member> member = memberRepository.findById(requestDto.getMemberId());
        Optional<Token> token = tokenRepository.findByKey(Long.toString(requestDto.getMemberId()));

        if(member.isPresent()) {
            if (token.isPresent() && token.get().getFcmtoken() != null) {
                Notification notification = Notification.builder()
                        .setTitle(requestDto.getTitle())
                        .setBody(requestDto.getBody())
                        .build();

                Message message = Message.builder()
                        .setToken(token.get().getFcmtoken())
                        .setNotification(notification)
                        .build();

                try {
                    firebaseMessaging.send(message);
                    return "알림을 성공적으로 전송했습니다. memberId=" + requestDto.getMemberId();
                } catch (FirebaseMessagingException e) {
                    e.printStackTrace();
                    return "알림을 보내기를 실패하였습니다. memberId=" + requestDto.getMemberId();
                }
            } else {
                return "서버에 저장된 해당 유저의 FirebaseToken이 존재하지 않습니다. targetUserId=" +
                        requestDto.getMemberId();
            }
        } else {
            return "해당 유저가 존재하지 않습니다. memberId=" + requestDto.getMemberId();
        }
    }

    public String sendArticleNotificationByToken(FCMArticleNotificationRequestDto requestDto){

        Optional<Member> member = memberRepository.findById(requestDto.getMemberId());

        Optional<Token> token = tokenRepository.findByKey(Long.toString(requestDto.getMemberId()));

        if(member.isPresent()) {
            if (token.isPresent() && token.get().getFcmtoken() != null) {
                Notification notification = Notification.builder()
                        .setTitle(requestDto.getTitle())
                        .setBody(requestDto.getBody())
                        .build();

                Message message = Message.builder()
                        .setToken(token.get().getFcmtoken())
                        .setNotification(notification)
                        .putData("articleId", String.valueOf(requestDto.getArticleId()))
                        .putData("type", String.valueOf(requestDto.getType()))
                        .build();

                try {
                    firebaseMessaging.send(message);
                    return "알림을 성공적으로 전송했습니다. memberId=" + requestDto.getMemberId();
                } catch (FirebaseMessagingException e) {
                    e.printStackTrace();
                    return "알림을 보내기를 실패하였습니다. memberId=" + requestDto.getMemberId();
                }
            } else {
                return "서버에 저장된 해당 유저의 FirebaseToken이 존재하지 않습니다. targetUserId=" +
                        requestDto.getMemberId();
            }
        } else {
            return "해당 유저가 존재하지 않습니다. memberId=" + requestDto.getMemberId();
        }
    }

    public String sendReportNotificationByToken(FCMReportNotificationRequestDto requestDto){

        Optional<Member> member = memberRepository.findById(requestDto.getMemberId());

        Optional<Token> token = tokenRepository.findByKey(Long.toString(requestDto.getMemberId()));

        if(member.isPresent()) {
            if (token.isPresent() && token.get().getFcmtoken() != null) {
                Notification notification = Notification.builder()
                        .setTitle(requestDto.getTitle())
                        .setBody(requestDto.getBody())
                        .build();

                Message message = Message.builder()
                        .setToken(token.get().getFcmtoken())
                        .setNotification(notification)
                        .putData("reportId", String.valueOf(requestDto.getReportId()))
                        .build();

                try {
                    firebaseMessaging.send(message);
                    return "알림을 성공적으로 전송했습니다. memberId=" + requestDto.getMemberId();
                } catch (FirebaseMessagingException e) {
                    e.printStackTrace();
                    return "알림을 보내기를 실패하였습니다. memberId=" + requestDto.getMemberId();
                }
            } else {
                return "서버에 저장된 해당 유저의 FirebaseToken이 존재하지 않습니다. targetUserId=" +
                        requestDto.getMemberId();
            }
        } else {
            return "해당 유저가 존재하지 않습니다. memberId=" + requestDto.getMemberId();
        }
    }
}
