package com.stephano.helpdesk.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stephano.helpdesk.api.entity.ChangeStatus;

public interface ChangeStatusRepository extends MongoRepository<ChangeStatus, String> {
	
	Iterable<ChangeStatus> findByTicketIdOrderByDateDesc(String ticketId);
	
}
