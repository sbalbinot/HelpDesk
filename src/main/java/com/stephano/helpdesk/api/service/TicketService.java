package com.stephano.helpdesk.api.service;

import org.springframework.data.domain.Page;

import com.stephano.helpdesk.api.entity.ChangeStatus;
import com.stephano.helpdesk.api.entity.Ticket;

public interface TicketService {

	Ticket createOrUpdate(Ticket ticket);
	
	Ticket findById(String id);
	
	void delete(String id);
	
	Iterable<Ticket> findAll();
	
	Page<Ticket> list(int page, int count);
	
	Page<Ticket> findByNumber(int page, int count, Integer number);
	
	Page<Ticket> findByCurrentUser(int page, int count, String userId);
	
	Page<Ticket> findByParameters(int page, int count, String title, String status, String priority);
	
	Page<Ticket> findByParametersAndUser(int page, int count, String title, String status, String priority, String userId);
	
	Page<Ticket> findByParametersAndAssignedUser(int page, int count, String title, String status, String priority, String assignedUserId);
	
	ChangeStatus createChangeStatus(ChangeStatus changeStatus);
	
	Iterable<ChangeStatus> listChangeStatus(String ticketId);

}
