package com.uniovi.entities.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.uniovi.entities.FriendRequestInterface;
import com.uniovi.entities.UserInterface;

@Entity
// @Table(name = "USER")
public class User implements UserInterface {

	@Id @GeneratedValue private long id;

	private String name;

	@Column(unique = true) private String email;

	private String password;

	@Transient private String passwordConfirm;

	// List of friends.
	@ManyToMany(cascade = {CascadeType.ALL })
	@JoinTable(name = "friends", joinColumns = @JoinColumn(name = "friend_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<UserInterface> friends = new HashSet<UserInterface>();

	// List of petitions.
	private Set<FriendRequestInterface> friendRequests = new HashSet<FriendRequestInterface>();

	public User( String name, String email ) {
		setName( name );
		setEmail( email );
		setPassword( password );
	}

	public User() {

	}

	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	@Override
	public void setName( String name ) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	@Override
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	@Override
	public void setEmail( String email ) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	@Override
	public void setPassword( String password ) {
		this.password = password;
	}

	/**
	 * @return the passwordConfirm
	 */
	@Override
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	/**
	 * @param passwordConfirm the passwordConfirm to set
	 */
	@Override
	public void setPasswordConfirm( String passwordConfirm ) {
		this.passwordConfirm = passwordConfirm;
	}
	
	@Override
	public Set<UserInterface> getFriends() {
		return friends;
	}
	
	@Override
	public void setFriends( Set<UserInterface> friends ) {
		this.friends = friends;
	}
	
	@Override
	public Set<FriendRequestInterface> getFriendRequests() {
		return friendRequests;
	}
	
	@Override
	public void setFriendRequests( Set<FriendRequestInterface> friendRequests ) {
		this.friendRequests = friendRequests;
	}
}
