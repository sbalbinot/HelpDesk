package com.stephano.helpdesk.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stephano.helpdesk.api.entity.Status;

public interface StatusRepository extends MongoRepository<Status, String> {
	
	Iterable<Status> findByTicketIdOrderByDateDesc(String ticketId);
	
}
