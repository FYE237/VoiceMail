package com.tech.test.Interface;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.tech.test.model.Chat;

public interface ChatInterface {
	public Chat createChat(String idChat,int send_number,int receive_number,MultipartFile video) throws IOException;
	public Chat getChatById(String id);
	public List<Chat> getAllChatSendNumber(int send_number);
	public List<Chat> getAllChatReceiveNumber(int receive_number);
	public List<Chat> getAllChat();
	public Chat getSendAndReceiveNumber(int send_number,int receive_number);
}
