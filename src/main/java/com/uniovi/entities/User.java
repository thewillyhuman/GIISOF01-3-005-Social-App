package com.uniovi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Transient
    private String passwordConfirm;

    // List of friends.
    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(name = "friends",
    	joinColumns = @JoinColumn(name = "friend_id"),
    	inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> friends = new HashSet<User>();

    // List of friend requests.
    @OneToMany(mappedBy = "id",
	    cascade = { CascadeType.ALL })
    private Set<FriendRequest> friendRequests = new HashSet<FriendRequest>();

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
     * @param name
     *            the name to set
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
     * @param email
     *            the email to set
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
     * @param password
     *            the password to set
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
     * @param passwordConfirm
     *            the passwordConfirm to set
     */
    public void setPasswordConfirm(String passwordConfirm) {
	this.passwordConfirm = passwordConfirm;
    }

    public Set<User> getFriends() {
	return friends;
    }

    public void setFriends(Set<User> friends) {
	this.friends = friends;
    }

    public Set<FriendRequest> getFriendRequests() {
	return friendRequests;
    }

    public void setFriendRequests(Set<FriendRequest> friendRequests) {
	this.friendRequests = friendRequests;
    }
}
