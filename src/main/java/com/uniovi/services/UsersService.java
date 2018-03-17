package com.uniovi.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

/**
 * Instance of UsersService.java
 * 
 * @author
 * @version
 */
@Service
public class UsersService {

	@Autowired
	private UsersRepository repository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User getUser(Long id) {
		return repository.findOne(id);
	}

	public User getUserByEmail(String email) {
		return repository.findByEmail(email);
	}

	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		repository.save(user);
	}

	public void updateUser(User user) {
		repository.save(user);
	}

	public void removeUser(User user) {
		repository.delete(user);
	}

	public Page<User> getUsers(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Iterable<User> getUsers() {
		return repository.findAll();
	}

	public Page<User> searchUsersByEmailAndName(String searchText, Pageable pageable) {
		Page<User> marks = new PageImpl<User>(new LinkedList<User>());
		String search = "%" + searchText + "%";
		marks = repository.searchUsersByEmailAndName(search, pageable);
		return marks;
	}

	public Page<User> getRequestsByUser(Long id, Pageable pageable) {
		return repository.findRequestByUser(id, pageable);

	}

	public Page<User> getFriendsByUser(Long id, Pageable pageable) {
		return repository.findFriendsByUser(id, pageable);

	}

}
