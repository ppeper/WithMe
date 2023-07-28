package com.bonobono.backend.chatting.config;

import org.json.JSONObject;

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
    String chatName;
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
                            this.chatName = jsonObject.getString("data");
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
    //메소드: JSON 보내기(outputstream을 써서 내보냄)
    public void send(String json) {
        try {
            dos.writeUTF(json);
            // json을 DB에 넣기(필드는 맞춰야할듯)
            // 지금 들어가는 필드는 clientIp, chatname, message
            // 보낸시간, 확인여부, 사진은... 어떻게 하지..?
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