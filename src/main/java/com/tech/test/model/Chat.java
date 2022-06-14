package com.tech.test.model;

import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import java.util.Arrays;
import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
/*import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;*/

/**
 * @author fye
 *
 */
@Table(value = "Chat")
public class Chat {
	
	@PrimaryKey
	private String idChat;
	/*@Positive(message="")
	@Min(value=600000000,message="")
	@Max(value=700000000,message="")*/
	
	private int sendNumber;
	
	private int receiveNumber;
	
	private LocalDateTime send_date;
	
	/*@CassandraType(type = CassandraType.Name.BLOB)
    private byte[] videoMail;*/
	
	@CassandraType(type = CassandraType.Name.BLOB)
	private byte[] audioMail;
	
	

	/*public Chat(String idChat, int sendNumber, int receiveNumber, LocalDateTime send_date, byte[] videoMail) {
		super();
		this.idChat = idChat;
		this.sendNumber = sendNumber;
		this.receiveNumber = receiveNumber;
		this.send_date = send_date;
		this.videoMail = videoMail;
	}*/
	
	public Chat(String idChat, int sendNumber, int receiveNumber, LocalDateTime send_date,/* byte[] videoMail,*/ byte[] audioMail) {
		super();
		this.idChat = idChat;
		this.sendNumber = sendNumber;
		this.receiveNumber = receiveNumber;
		this.send_date = send_date;
		//this.videoMail = videoMail;
		this.audioMail=audioMail;
	}

	public String getIdChat() {
		return idChat;
	}

	public void setIdChat(String idChat) {
		this.idChat = idChat;
	}

	public int getSendNumber() {
		return sendNumber;
	}

	public void setSendNumber(int sendNumber) {
		this.sendNumber = sendNumber;
	}

	public int getReceive_number() {
		return receiveNumber;
	}

	public void setReceive_number(int receiveNumber) {
		this.receiveNumber = receiveNumber;
	}

	public LocalDateTime getSend_date() {
		return send_date;
	}

	public void setSend_date(LocalDateTime send_date) {
		this.send_date = send_date;
	}

//	public byte[] getVideoMail() {
//		return videoMail;
//	}
//
//	public void setVideoMail(byte[] videoMail) {
//		videoMail = videoMail;
//	}

	
	
	public byte[] getAudioMail() {
		return audioMail;
	}

	public void setAudioMail(byte[] audioMail) {
		this.audioMail = audioMail;
	}

	@Override
	public String toString() {
		return "Chat [idChat=" + idChat + ", sendNumber=" + sendNumber + ", receiveNumber=" + receiveNumber
				+ ", send_date=" + send_date + ", audioMail=" + Arrays.toString(audioMail) + "]";
	}
	
	
}

