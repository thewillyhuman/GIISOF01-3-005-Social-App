package com.uniovi.entities.impl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.uniovi.entities.FriendRequestInterface;
import com.uniovi.entities.UserInterface;

@Entity public class FriendRequest implements FriendRequestInterface {

	@Id @GeneratedValue private Long id;

	@ManyToOne @JoinColumn(name = "user_id") private UserInterface from;

	public FriendRequest() {}

	public FriendRequest( UserInterface from ) {
		this.from = from;
	}

	// Not sure if this method should be included here but this would be the way
	// to
	// accept a FriendRequest.
	@Override
	public void accept( UserInterface actual ) {
		actual.getFriends().add( this.from );
		actual.getFriendRequests().remove( this );
	}

}
