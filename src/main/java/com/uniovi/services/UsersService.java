package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

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
    
    public Page<User> getFriends(User user, Pageable pageable) {
	return getFriends(user.getId(), pageable);
    }

    /**
     * Gets the list of friends as a gageable for a given user.
     * 
     * @param id
     * @param pageable
     * @return
     */
    public Page<User> getFriends(long id, Pageable pageable) {
	User user = repository.findOne(id);
	List<User> listOfFriends = new ArrayList<User>();
	listOfFriends.addAll(user.getFriends());
	return new PageImpl<User>(listOfFriends, pageable, listOfFriends.size());
    }
    
    public Page<User> getRequests(User user, Pageable pageable) {
	return getRequests(user.getId(), pageable);
    }
    
    /**
     * Gets the list of requests as a gageable for a given user.
     * 
     * @param id
     * @param pageable
     * @return
     */
    public Page<User> getRequests(long id, Pageable pageable) {
	User user = repository.findOne(id);
	List<User> listOfRequests = new ArrayList<User>();
	listOfRequests.addAll(user.getRequests());
	return new PageImpl<User>(listOfRequests, pageable, listOfRequests.size());
    }

    public Iterable<User> getUsers() {
	return repository.findAll();
    }

    public List<User> searchUsersByEmailAndName(String searchText) {
	List<User> marks = new ArrayList<User>();
	String search = "%" + searchText + "%";
	marks = repository.searchUsersByEmailAndName(search);
	return marks;
    }

}
