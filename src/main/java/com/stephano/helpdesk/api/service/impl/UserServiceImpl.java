package com.stephano.helpdesk.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.stephano.helpdesk.api.entity.User;
import com.stephano.helpdesk.api.exception.ObjectNotFoundException;
import com.stephano.helpdesk.api.repository.UserRepository;
import com.stephano.helpdesk.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;
	
	@Override
	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public User createOrUpdate(User user) {
		return repository.save(user);
	}

	@Override
	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
	}

	@Override
	public void delete(String id) {
		repository.deleteById(id);
	}

	@Override
	public Page<User> findAll(int page, int count) {
		Pageable pages = PageRequest.of(page, count);
		return repository.findAll(pages);
	}

}
