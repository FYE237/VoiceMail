package com.tech.test.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.tech.test.model.Chat;


@Repository
public interface ChatRepository extends CassandraRepository<Chat,String> {
	Optional<Chat> findBySendNumberAndReceiveNumber(int sendNumber,int receiveNumber);
    List<Chat> findAllBySendNumber(int sendNumber);
    List<Chat> findAllByReceiveNumber(int receiveNumber);
    Chat findByIdChat(String id);
}
