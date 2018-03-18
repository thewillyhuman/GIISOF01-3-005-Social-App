package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Request;
import com.uniovi.entities.User;
import com.uniovi.repositories.RequestRepository;

/**
 * Instance of UsersService.java
 * 
 * @author
 * @version
 */
@Service
public class RequestsService {

	@Autowired
	private RequestRepository requestsRepository;

	public void sendRequest(User emisor, User receptor) {
		Request request = new Request(emisor, receptor);
		requestsRepository.save(request);
		receptor.addRequest(request, emisor, receptor);
	}

	public Page<Request> getRequestsForUser(User user, Pageable pageable) {
		return requestsRepository.findAllByUser(pageable, user);
	}

	public void deleteRequest(User emisor, User receptor, Long id) {
		Request request = requestsRepository.findOne(id);
		receptor.removeRequest(request, emisor, receptor);
		requestsRepository.delete(id);
	}

}
