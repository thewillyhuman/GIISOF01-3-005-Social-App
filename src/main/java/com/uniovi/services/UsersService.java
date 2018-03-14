package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

	public void removeUser(User user) {
		repository.delete(user);
	}

	public Page<User> getUsers(Pageable pageable) {
		Page<User> users = repository.findAll(pageable);
		return users;
	}

	public List<User> searchUsersByEmailAndName(String searchText) {
		List<User> marks = new ArrayList<User>();
		String search = "%" + searchText + "%";
		marks = repository.searchUsersByEmailAndName(search);
		return marks;
	}

}
