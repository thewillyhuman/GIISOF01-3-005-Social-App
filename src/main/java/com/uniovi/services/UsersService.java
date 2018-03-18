package com.uniovi.services;

import java.util.LinkedList;

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
	private UsersRepository usersRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User getUser(Long id) {
		return usersRepository.findOne(id);
	}

	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}

	public void updateUser(User user) {
		usersRepository.save(user);
	}

	public void removeUser(User user) {
		usersRepository.delete(user);
	}

	public Page<User> getUsers(Pageable pageable) {
		return usersRepository.findAll(pageable);
	}

	public Iterable<User> getUsers() {
		return usersRepository.findAll();
	}

	public Page<User> searchUsersByEmailAndName(String searchText, Pageable pageable) {
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		String search = "%" + searchText + "%";
		users = usersRepository.searchUsersByEmailAndName(search, pageable);
		return users;
	}

	public void acceptRequest(User emisor, User receptor) {
		receptor.acceptRequest(emisor, receptor);
	}

}
