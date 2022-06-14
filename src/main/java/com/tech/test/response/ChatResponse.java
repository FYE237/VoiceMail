package com.tech.test.response;

import java.util.UUID;


public class ChatResponse {
	 private String idChat;
	 private String send_number;
	 private String receive_number;
	 private String ChatAudioUrl;
	 private String send_date;
	 
	public ChatResponse(String idChat, String send_number, String receive_number, String chatAudioUrl, String send_date) {
		super();
		this.idChat = idChat;
		this.send_number = send_number;
		this.receive_number = receive_number;					
		this.ChatAudioUrl = chatAudioUrl;
		this.send_date = send_date;
	}

	@Override
	public String toString() {
		return "ChatResponse [idChat=" + idChat + ", send_number=" + send_number + ", receive_number=" + receive_number
				+ ", ChatAudioUrl=" + ChatAudioUrl + ", send_date=" + send_date + "]";
	}
	 
	 
}
