package com.bonobono.backend.chatting.config;

import com.bonobono.backend.chatting.domain.ChatRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class MyServer {
    //server와 클라이언트 통신하는 설정 파일
    //json파일에 사용자 정보, 채팅내용 등 넣어서 보내주면(receive), 처리 후 다시 send
    // (controller)여기서 추가될 로직은 만약에 url에 담긴 채팅방정보가 db에 있는지 확인하고 있으면 화면단에 보여주고
    //아니라면, thread를 만든 후, 그대로 로직


    ServerSocket serverSocket;
    ExecutorService threadPool = Executors.newFixedThreadPool(100);
    //채팅(key, socketClient)몇명이 접속했는지, key는 사용자 정보
    Map<String, SocketClient> chatinfo = Collections.synchronizedMap(new HashMap<>());
    //json 파일을 저장할 그 ... 자료구조 선언 후 넣고 클라이언트 연결종료시, db에 저장
    //jsonarray를 생성해서 넣어서 db에 저장


    //서버 시작하고, 스레드 시작
    public void start(Long id) throws IOException {
        serverSocket = new ServerSocket(8888);
        System.out.println( "[서버] 시작됨");

        Thread thread = new Thread(() ->
        { try{
            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println("접속: "+socket);
                SocketClient sc = new SocketClient(this, socket, id);
                // DB에 담기 전, JSON을 쌓아놓을 배열(수정 필요)
//                JSONArray jsonArray = new JSONArray();
            }
        } catch(IOException e) {
        }});
        thread.start();
    }

    // 채팅방이름+clientip로 채팅방 map에 저장(
    public void addSocketClient(SocketClient socketClient) {
        String key = socketClient.chatName + "@" + socketClient.clientIp;
        chatinfo.put(key, socketClient);
        
        System.out.println("입장: " + key);
        System.out.println("현재 채팅자 수: " + chatinfo.size() + "\n");
    }

    //메소드: 클라이언트 연결 종료시 SocketClient 제거
    public void removeSocketClient(SocketClient socketClient) {
        String key = socketClient.chatName + "@" + socketClient.clientIp;
        chatinfo.remove(key);
        System.out.println("나감: " + key);
        System.out.println("현재 채팅자 수: " + chatinfo.size() + "\n");
    }
    //메소드: 모든 클라이언트에게 메시지 보냄
    public void sendToAll(SocketClient sender, String message, Long roomId) {
        try {
            JSONObject root = new JSONObject();
            //클라이언트에게 json파일 내 clientIp, message, 채팅방 id를 받기
            root.put("clientIp", sender.clientIp);
            root.put("message", message);
//            root.put("roomId", sender.RoomId);
            String json = root.toString();

            Collection<SocketClient> socketClients = chatinfo.values();
            for (SocketClient sc : socketClients) {
                if (sc == sender) continue;
                sc.send(json, roomId);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //메소드: 서버 종료
    public void stop() {
        try {
            serverSocket.close();
            threadPool.shutdownNow(); //스레드 종료
            chatinfo.values().stream().forEach(sc -> {sc.close();});
            System.out.println( "[서버] 종료됨 ");
        } catch (IOException e1) {}
    }
}
//    public MyServer() {
//        try {
//            MyServer chatServer = new MyServer();
//            chatServer.start();
//
//            Scanner scanner = new Scanner(System.in); //input값
//            while(true) {
//                String key = scanner.nextLine();
//                if(key.equals("q")) 	break;
//            }
//            scanner.close();
//            chatServer.stop();
//        } catch(IOException e) {
//            System.out.println("[서버] " + e.getMessage());
//        }

//        try{
//        ServerSocket serverSocket = new ServerSocket(8888); //클라이언트 포트 번호..
//        ExecutorService threadPool = Executors.newFixedThreadPool(100);
//        Map<String, SocketClient> chatRoom = Collections.synchronizedMap(new HashMap<>());
//
//        while(true) {
//            Socket socket = serverSocket.accept();
//            System.out.println("접속: "+socket);
//            ChatThread chatThread = new ChatThread(socket);
//            chatThread.start();
//        }
//    }
//    catch(IOException e) {
//        log.error("웹소켓 통신에 실패했습니다",e);
//    }
//    }
//}
