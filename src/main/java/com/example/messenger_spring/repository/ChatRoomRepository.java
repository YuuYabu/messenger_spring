package com.example.messenger_spring.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.example.messenger_spring.model.ChatRoom;
import jakarta.annotation.PostConstruct;

@Repository
public class ChatRoomRepository {

  // チャットルーム情報を保持するためのMap
  private Map<String, ChatRoom> chatRoomMap;

  @PostConstruct
  private void init() {
    chatRoomMap = new LinkedHashMap<>();
  }

  /**
   * @return 生成の逆順に並べたチャットルームのリスト
   */
  public List<ChatRoom> findAllRoom() {
    List<ChatRoom> chatRooms = new ArrayList<>(chatRoomMap.values());
    Collections.reverse(chatRooms);
    return chatRooms;
  }

  /**
   * id でチャットルームを検索
   * @param id チャットルームid
   * @return chatRoom
   */
  public ChatRoom findRoomById(String id) {
    return chatRoomMap.get(id);
  }

  /**
   * チャットルームを生成するメソッド
   * 生成後は chatRoomMap に追加
   * @param name チャットルームの名前
   * @return chatRoom
   */
  public ChatRoom createChatRoom(String name) {
    ChatRoom chatRoom = ChatRoom.create(name);
    chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
    return chatRoom;
  }
}
