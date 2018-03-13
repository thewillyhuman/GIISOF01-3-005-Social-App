package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
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

	public Iterable<User> getUsers() {
		return repository.findAll();
	}

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

	public void removeUser(User user) {
		repository.delete(user);
	}
}
