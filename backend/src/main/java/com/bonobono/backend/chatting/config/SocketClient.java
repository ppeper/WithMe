package com.bonobono.backend.chatting.config;

import com.bonobono.backend.chatting.domain.ChatMessage;
import com.bonobono.backend.chatting.domain.ChatRoom;
import com.bonobono.backend.chatting.dto.ChatMessageRequestDto;
import com.bonobono.backend.chatting.repository.ChatMessageRepository;
import com.bonobono.backend.chatting.service.ChatMessageService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient {
    //필드
    MyServer myServer;
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
    String clientIp;
    Long chatName;
    //생성자
    public SocketClient(MyServer myServer, Socket socket) {
        try {
            this.myServer = myServer;
            this.socket = socket;
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
            InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
            this.clientIp = isa.getHostName();
            receive(); // inputstream json받아서 모든 사용자에 보여주고, 사용자도 보여줌
        } catch(IOException e) {
        }
    }
    //메소드: JSON 받기
    public void receive() {
        myServer.threadPool.execute(() -> {
            try {
                while(true) {
                    String receiveJson = dis.readUTF();

                    JSONObject jsonObject = new JSONObject(receiveJson);
                    String command = jsonObject.getString("command");

                    switch(command) {
                        case "incoming": //사용자가 들어온 것이면 socketclient추가
                            //채팅메시지 id(long)
                            this.chatName = jsonObject.getLong("data");
                            myServer.sendToAll(this, "들어오셨습니다.");
                            myServer.addSocketClient(this);
                            break;
                        case "message": //메시지면, 모든 사람에게 메시지 보여줌
                            String message = jsonObject.getString("data");
                            myServer.sendToAll(this, message);
                            break;
                    }
                }
            } catch(IOException e) {
                myServer.sendToAll(this, "나가셨습니다.");
                myServer.removeSocketClient(this);
            }
        });
    }

    @Autowired
    ChatMessageRepository chatMessageRepository;

    //메소드: JSON 보내기(outputstream을 써서 내보냄)
    public void send(String json) {
        try {
            dos.writeUTF(json);

            //보내기 전 db에 저장
            //json을 dto로 바꾸고, repository저장
            JSONObject jsonObject = new JSONObject(json);

            // JSONObject에서 필요한 데이터 추출
            String clientIp = jsonObject.getString("clientIp");
            String message = jsonObject.getString("message");

            // 추출한 데이터를 사용하여 ChatMessageRequestDto 객체 생성
            ChatMessageRequestDto dto = ChatMessageRequestDto.builder()
                    .sender(clientIp)
                    .message(message)
                    .build();

            // ChatMessageRequestDto 객체를 ChatMessage 객체로 변환
            ChatMessage chatMessage = dto.toEntity();

            // ChatMessage 객체를 저장
            chatMessageRepository.save(chatMessage);

            dos.flush();
        } catch(IOException e) {
        }
    }
    //메소드: 연결 종료
    public void close() {
        try {
            socket.close();
        } catch(Exception e) {}
    }
}