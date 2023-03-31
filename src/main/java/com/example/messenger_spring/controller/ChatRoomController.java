package com.example.messenger_spring.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.messenger_spring.model.ChatRoom;
import com.example.messenger_spring.repository.ChatRoomRepository;

@Controller
@RequestMapping("/chat")
public class ChatRoomController {
  private final ChatRoomRepository chatRoomRepository;

  public ChatRoomController(ChatRoomRepository chatRoomRepository) {
    this.chatRoomRepository = chatRoomRepository;
  }

  /**
   * チャットリストの画面
   * @param model
   * @return
   */
  @GetMapping("/room")
  public String rooms(Model model) {
    return "chat/room";
  }

  /**
   * チャットルームの一覧リストを返す
   * @return チャットルームの一覧リスト
   */
  @GetMapping("/rooms")
  @ResponseBody
  public List<ChatRoom> room() {
    return chatRoomRepository.findAllRoom();
  }

  /**
   * チャットルームの作成
   * @param name チャットルームの名前
   * @return
   */
  @PostMapping("/room")
  @ResponseBody
  public ChatRoom createRoom(@RequestParam String name) {
    return chatRoomRepository.createChatRoom(name);
  }

  /**
   * チャット参加画面
   * @param model
   * @param roomId チャットルームのID
   * @return
   */
  @GetMapping("/room/enter/{roomId}")
  public String roomDetail(Model model, @PathVariable(name = "roomId") String roomId) {
    model.addAttribute("roomId", roomId);
    return "chat/roomDetail";
  }

  /**
   * 特定のチャットルームを検索
   * @param roomId チャットルームのID
   * @return
   */
  @GetMapping("/room/{roomId}")
  @ResponseBody
  public ChatRoom roomInfo(@PathVariable(name = "roomId") String roomId) {
    return chatRoomRepository.findRoomById(roomId);
  }
}
