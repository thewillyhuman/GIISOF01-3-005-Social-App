package com.uniovi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

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

	@OneToMany(mappedBy = "emisor", cascade = CascadeType.ALL)
	private Set<Request> requestsEnviadas = new HashSet<Request>();

	@OneToMany(mappedBy = "receptor", cascade = CascadeType.ALL)
	private Set<Request> requestsRecibidas = new HashSet<Request>();

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

	public Set<Request> getRequestsEnviadas() {
		return requestsEnviadas;
	}

	public void setRequestsEnviadas(Set<Request> requestsEnviadas) {
		this.requestsEnviadas = requestsEnviadas;
	}

	public Set<Request> getRequestsRecibidas() {
		return requestsRecibidas;
	}

	public void setRequestsRecibidas(Set<Request> requestsRecibidas) {
		this.requestsRecibidas = requestsRecibidas;
	}

	public void addRequest(Request request, User emisor, User receptor) {
		emisor.getRequestsEnviadas().add(request);
		receptor.getRequestsRecibidas().add(request);
	}

	public void removeRequest(Request request, User sender, User reciever) {
		sender.getRequestsEnviadas().remove(request);
		reciever.getRequestsRecibidas().remove(request);
		request.setEmisor(null);
		request.setReceptor(null);
	}

	public void acceptRequest(User emisor, User receptor) {
		// emisor.getFriends().add(receptor);
		// receptor.getFriends().add(emisor);
	}



}
