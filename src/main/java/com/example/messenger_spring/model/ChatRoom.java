package com.example.messenger_spring.model;

import java.util.UUID;

public class ChatRoom {
  private String roomId;
  private String name;

  public static ChatRoom create(String name) {
    ChatRoom chatRoom = new ChatRoom();
    chatRoom.roomId = UUID.randomUUID().toString();
    chatRoom.name = name;
    return chatRoom;
  }

  public String getRoomId() {
    return roomId;
  }

  public void setRoomId(String roomId) {
    this.roomId = roomId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
