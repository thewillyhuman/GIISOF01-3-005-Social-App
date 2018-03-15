package com.uniovi.entities;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

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
	@JoinTable(name = "friends", joinColumns = @JoinColumn(name = "friend_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> friends = new HashSet<User>();

	// List of friend requests.
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "requests", joinColumns = @JoinColumn(name = "requester_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> requests = new HashSet<User>();

	@Transient
	private Page<User> f;

	public User(String name, String email) {
		setName(name);
		setEmail(email);
		setPassword(password);
	}

	public User() {

	}

	public long getId() {
		return this.id;
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

	public Page<User> getF(Pageable pageable) {
		return new PageImpl<User>(new LinkedList<User>(getRequests()));
	}

	public Set<User> getRequests() {
		if (requests == null)
			requests = new HashSet<User>();

		return requests;
	}

	public void setRequests(Set<User> friendRequests) {
		this.requests = friendRequests;
	}

	@Transactional
	public void acceptRequestFrom(User user) {
		if (this.getRequests().contains(user)) {
			// Add the relation to one side.
			this.friends.add(user);

			// Add the relation to another side.
			user.getFriends().add(this);

			// Remove the user request
			this.requests.remove(user);

		} else {
			System.err.println("There's no request from that user");
		}
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (!(other instanceof User))
			return false;
		User otherUser = (User) other;
		if (this.email == otherUser.getEmail())
			return true;
		else
			return false;
	}

}
