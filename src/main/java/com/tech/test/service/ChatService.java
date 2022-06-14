package com.tech.test.service;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.tech.test.Interface.ChatInterface;
import com.tech.test.exception.BadRequestException;
import com.tech.test.model.Chat;
import com.tech.test.repository.ChatRepository;

@Service
@Transactional
public class ChatService implements ChatInterface {
	
	@Autowired
	private ChatRepository chatRepository;

	@Override
	public Chat createChat(String idChat, int send_number, int receive_number,MultipartFile audio) throws IOException{
        byte[] theaudio = null;
        if(!(send_number > 0)) throw new BadRequestException("A sendNumber form data field is required");
        if(!(receive_number > 0)) throw new BadRequestException("A receiveNumber form data field is required");
        if(send_number < 600000000 || send_number > 700000000) throw new BadRequestException("The userNumber is not a valid phone number");
        if(receive_number < 600000000 || receive_number > 700000000) throw new BadRequestException("The userNumber is not a valid phone number");
        //if(!userRepository.findById(userNumber).isPresent()) throw new NotFoundException("The user with that Number is not registered to this platform");
        //LocalDateTime theDisappearTime = null;

        if(audio == null){
            throw new BadRequestException("You status has to contain a text, audio, image or video");
        }

        LocalDateTime send_date = LocalDateTime.now();

        if(!(audio == null)){
            String audioFileName = StringUtils.cleanPath(audio.getOriginalFilename());
            String audioFileExtension = StringUtils.getFilenameExtension(audioFileName);
            if(!audioFileExtension.equalsIgnoreCase("mp3") ) throw new BadRequestException("You need to provide an audio here");
            if (audioFileName.contains("..")) {
                throw new BadRequestException("Filename contains invalid path sequence "
                        + audioFileName);
            }
            theaudio = audio.getBytes();
        }
        Chat chat = new Chat(idChat,send_number, receive_number,send_date,theaudio);
        Chat returnedchat = chatRepository.save(chat);
        System.out.println("........okkkkk");
        System.out.println(returnedchat.toString());
        return returnedchat;
	}

	@Override
	public Chat getChatById(String id){
		Chat chatData=chatRepository.findByIdChat(id);
		if (chatData !=null) {
		    return chatData;
		  } else {
		    return null;
		  }
	}

	@Override
	public List<Chat> getAllChatSendNumber(int send_number) {
		List<Chat> listChat=chatRepository.findAllBySendNumber(send_number);
		if(listChat.isEmpty()) {
			return null;
		}else {
			return listChat;
		}
	}

	@Override
	public List<Chat> getAllChat() {
		List<Chat> listChat=chatRepository.findAll();
		if(listChat.isEmpty()) {
			return null;
		}else {
			return listChat;
		}
	}

	@Override
	public List<Chat> getAllChatReceiveNumber(int receive_number) {
		List<Chat> listChat=chatRepository.findAllBySendNumber(receive_number);
		if(listChat.isEmpty()) {
			return null;
		}else {
			return listChat;
		}
	}

	@Override
	public Chat getSendAndReceiveNumber(int send_number, int receive_number) {
		Optional<Chat> chatData=chatRepository.findBySendNumberAndReceiveNumber(send_number, receive_number);
		if (chatData.isPresent()) {
		    return chatData.get();
		  } else {
		    return null;
		  }
	}

}

