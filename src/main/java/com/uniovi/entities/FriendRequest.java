package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FriendRequest {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User from;

    public FriendRequest() {
    }

    public FriendRequest(User from) {
	this.from = from;
    }

    // Not sure if this method should be included here but this would be the way
    // to
    // accept a FriendRequest.
    public void accept(User actual) {
	actual.getFriends().add(this.from);
	actual.getFriendRequests().remove(this);
    }

}
