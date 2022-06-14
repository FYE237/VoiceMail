package com.tech.test.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.tech.test.model.Chat;
import com.tech.test.response.ChatResponse;
import com.tech.test.service.ChatService;
import static com.datastax.oss.driver.shaded.guava.common.net.HttpHeaders.CONTENT_LENGTH;
import static com.datastax.oss.driver.shaded.guava.common.net.HttpHeaders.CONTENT_TYPE;


@RestController
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@PostMapping(path="/chat")
	public ResponseEntity<ChatResponse> postchat(
			@RequestParam(required = true) int send_number,
            @RequestParam(required = true) int receive_number,
            @RequestParam(required = true) MultipartFile audio
			)throws IOException{
		UUID idChat=Uuids.timeBased();
		Chat chat=chatService.createChat(idChat.toString(), send_number, receive_number, audio);
		System.out.println(chat.toString());
		ChatResponse chatResponse = null;
		if (audio != null) {
            String downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("chat/audio/"+idChat)
                    //.path(Integer.toString(c.getUserNumber()))
                    .toUriString(); //+ "?postTime=" + status.getPostTime().toString();
            chatResponse = new ChatResponse(idChat.toString(),send_number+"",receive_number+"" ,downloadURl,chat.getSend_date().toString());
            System.out.println(chatResponse.toString());
        }
		return new ResponseEntity<>(chatResponse, HttpStatus.CREATED);
	}
	
	@GetMapping("/chat/audio/{id}")
    public ResponseEntity<Resource> viewAudio(@PathVariable String id) {
		
        Chat chat = chatService.getChatById(id);
        System.out.println(chat.toString());
        int fileSize = chat.getAudioMail().length;
        return ResponseEntity.ok()
                .header(CONTENT_TYPE, "audio/mp3")
                .header(CONTENT_LENGTH, String.valueOf(fileSize))
                .body(new ByteArrayResource(chat.getAudioMail()));
    }
	
	
}
