/*
 * This source file is part of the SocialApp open source project.
 *
 * Copyright (c) 2018 willy and the SocialApp project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package com.uniovi.entities;

import java.util.Set;

/**
 * User interface for every user of this model to implement.
 * 
 * @author Guillermo Facundo Colunga.
 * @version 201803121513
 */
public interface UserInterface {

	/**
	 * Gets the name of the user as an String.
	 * 
	 * @return the name of the user as an String.
	 */
	public String getName();

	/**
	 * Sets the name given in the parameter as the new name of the user.
	 * 
	 * @param name to set as new name of the user.
	 */
	public void setName( String name );

	/**
	 * Gets the email of the user as a String.
	 * 
	 * @return the email of the user as a String.
	 */
	public String getEmail();

	/**
	 * Sets the email of the user from the email given as a parameter.
	 * 
	 * @param email to be set to the user.
	 */
	public void setEmail( String email );

	/**
	 * Gets the password of the user as a String.
	 * 
	 * @return the password of the user as a String.
	 */
	public String getPassword();

	/**
	 * Sets the password of the user from the password String given as a
	 * parameter.
	 * 
	 * @param password to be set to the user.
	 */
	public void setPassword( String password );

	/**
	 * Gets the password confirmation of the user as a String.
	 * 
	 * @return the password confirmation of the user as a String.
	 */
	public String getPasswordConfirm();

	/**
	 * Sets the password confirmation of the user from the password String given
	 * as a parameter.
	 * 
	 * @param passwordConfirm
	 */
	public void setPasswordConfirm( String passwordConfirm );

	/**
	 * Gets the set of users that are friends of the current user.
	 * 
	 * @return the set of users that are friends of the current user.
	 */
	public Set<UserInterface> getFriends();

	/**
	 * Sets the set of friends of the current user.
	 * 
	 * @param friends to be set as friends of the current user.
	 */
	public void setFriends( Set<UserInterface> friends );

	/**
	 * Gets the friend requests set for the current user.
	 * 
	 * @return the friend requests set for the current user.
	 */
	public Set<FriendRequestInterface> getFriendRequests();

	/**
	 * Sets the set of friend requests of the current user.
	 * 
	 * @param friendRequests is the new set of friend requests.
	 */
	public void setFriendRequests( Set<FriendRequestInterface> friendRequests );
}
