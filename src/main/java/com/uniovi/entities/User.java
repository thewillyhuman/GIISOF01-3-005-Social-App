package com.uniovi.entities;

import java.util.ArrayList;
import java.util.List;

public class User {
    
    private String name;
    private String email;
    private String password;
    private String passwordConfirm;
    
    // List of friends.
    private List<User> friends = new ArrayList<User>();
    
    // List of petitions.
    private List<FriendRequest> firendRequests = new ArrayList<FriendRequest>();
    
    public User(String name, String email) {
	setName(name);
	setEmail(email);
	setPassword(password);
    }
    
    public User() {
	
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the passwordConfirm
     */
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     * @param passwordConfirm the passwordConfirm to set
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public List<User> getFriends() {
	return friends;
    }

    public void setFriends(List<User> friends) {
	this.friends = friends;
    }

}
