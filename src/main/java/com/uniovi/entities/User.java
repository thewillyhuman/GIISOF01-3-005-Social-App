package com.uniovi.entities;

import java.util.HashSet;
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

import org.springframework.transaction.annotation.Transactional;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
public class User {
	@Id
	@Getter
	@GeneratedValue
	private long id;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	@Column(unique = true)
	private String email;

	@Getter
	@Setter
	private String password;

	@Getter
	@Setter
	@Transient
	private String passwordConfirm;

	// List of friends.
	@Getter @Setter
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "friends", joinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	private Set<User> friends = new HashSet<User>();

	// List of friend requests.
	@Getter @Setter
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "requests", joinColumns = @JoinColumn(name = "requester_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	private Set<User> requests = new HashSet<User>();

	public User(String name, String email) {
		setName(name);
		setEmail(email);
		setPassword(password);
	}

	public User() {

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
			log.warn("[WARNING]: (" + this.getEmail() + ") Has no request from: "+ user.getEmail());
		}
	}

	public boolean hasRequest(User user) {
		return requests.contains(user);
	}
	
	public boolean isFriend(User user) {
		return friends.contains(user);
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