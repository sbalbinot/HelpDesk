package com.stephano.helpdesk.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.stephano.helpdesk.api.entity.ChangeStatus;
import com.stephano.helpdesk.api.entity.Ticket;
import com.stephano.helpdesk.api.exception.ObjectNotFoundException;
import com.stephano.helpdesk.api.repository.ChangeStatusRepository;
import com.stephano.helpdesk.api.repository.TicketRepository;
import com.stephano.helpdesk.api.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private ChangeStatusRepository changeStatusRepository;
	
	@Override
	public Ticket createOrUpdate(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	@Override
	public Ticket findById(String id) {
		Optional<Ticket> obj = ticketRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Ticket.class.getName()));
	}

	@Override
	public void delete(String id) {
		ticketRepository.deleteById(id);
	}

	@Override
	public Iterable<Ticket> findAll() {
		return ticketRepository.findAll();
	}

	@Override
	public Page<Ticket> list(int page, int count) {
		Pageable pages = PageRequest.of(page, count);
		return ticketRepository.findAll(pages);
	}

	@Override
	public Page<Ticket> findByNumber(int page, int count, Integer number) {
		Pageable pages = PageRequest.of(page, count);
		return ticketRepository.findByNumber(number, pages);
	}

	@Override
	public Page<Ticket> findByCurrentUser(int page, int count, String userId) {
		Pageable pages = PageRequest.of(page, count);
		return ticketRepository.findByUserIdOrderByDateDesc(userId, pages);
	}

	@Override
	public Page<Ticket> findByParameters(int page, int count, String title, String status, String priority) {
		Pageable pages = PageRequest.of(page, count);
		return ticketRepository.findByTitleIgnoreCaseContainingAndStatusAndPriorityOrderByDateDesc(title, status, priority, pages);
	}

	@Override
	public Page<Ticket> findByParametersAndUser(int page, int count, String title, String status, String priority, String userId) {
		Pageable pages = PageRequest.of(page, count);
		return ticketRepository.findByTitleIgnoreCaseContainingAndStatusAndPriorityAndUserIdOrderByDateDesc(title, status, priority, userId, pages);
	}

	@Override
	public Page<Ticket> findByParametersAndAssignedUser(int page, int count, String title, String status, String priority, String assignedUserId) {
		Pageable pages = PageRequest.of(page, count);
		return ticketRepository.findByTitleIgnoreCaseContainingAndStatusAndPriorityAndAssignedUserIdOrderByDateDesc(title, status, priority, assignedUserId, pages);
	}

	@Override
	public ChangeStatus createChangeStatus(ChangeStatus changeStatus) {
		return changeStatusRepository.save(changeStatus);
	}
	
	@Override
	public Iterable<ChangeStatus> listChangeStatus(String ticketId) {
		return changeStatusRepository.findByTicketIdOrderByDateDesc(ticketId);
	}

}
