package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Request {
	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private User receptor;

	@ManyToOne
	private User emisor;

	Request() {
	}

	public Request(User emisor, User reciever) {
		super();
		this.emisor = emisor;
		this.receptor = reciever;
	}

	public long getId() {
		return id;
	}

	public User getReceptor() {
		return receptor;
	}

	public void setReceptor(User receptor) {
		this.receptor = receptor;
	}

	public User getEmisor() {
		return emisor;
	}

	public void setEmisor(User emisor) {
		this.emisor = emisor;
	}

	@Override
	public String toString() {
		return "FriendRequest [sender=" + emisor + ", reciever=" + receptor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((receptor == null) ? 0 : receptor.hashCode());
		result = prime * result + ((emisor == null) ? 0 : emisor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (receptor == null) {
			if (other.receptor != null)
				return false;
		} else if (!receptor.equals(other.receptor))
			return false;
		if (emisor == null) {
			if (other.emisor != null)
				return false;
		} else if (!emisor.equals(other.emisor))
			return false;
		return true;
	}

}
