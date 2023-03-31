package com.example.messenger_spring.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import com.example.messenger_spring.model.ChatMessage;

@Controller
public class ChatController {
  private final SimpMessageSendingOperations messagingTemplate;

  public ChatController(SimpMessageSendingOperations messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }

  
  /**
   * クライアントからprefixつけて、/pub/chat/messageに発行リクエストをするとControllerが該当メッセージを受け取り処理する
   * @param message
   */
  @MessageMapping("/chat/message")
  public void message(ChatMessage message) {
    if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
      message.setMessage(message.getSender() + "さんが参加しました。");
    }
    // クライアントでは該当アドレス /sub/chat/room/{roomId} を登録していてメッセージが渡されたら画面に表示する
    // /sub/chat/room/{roomId} はチャットルームを区分する値なので、pub/sub でTopicの役割を行う
    messagingTemplate.convertAndSend("/sub/chat/room" + message.getRoomId(), message);
  }
}
