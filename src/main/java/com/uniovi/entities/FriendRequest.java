package com.uniovi.entities;

public class FriendRequest {
    
    private User from;
    private boolean accepted = false;
    
    public FriendRequest() { }
    
    public FriendRequest(User from) {
	this.from = from;
	this.accepted = false;
    }

}
