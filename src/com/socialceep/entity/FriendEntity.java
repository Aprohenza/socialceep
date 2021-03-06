package com.socialceep.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the FRIENDS database table.
 * 
 */
@Entity
@Table(name = "FRIENDS")
@NamedQueries({
	@NamedQuery(name = "FriendEntity.findAll", query = "SELECT f FROM FriendEntity f"),
	@NamedQuery(name = "FriendEntity.findAllFriendsRequest", query = "SELECT f FROM FriendEntity f WHERE f.friendUserInvited=:friendUserInvited AND f.friendRequestStatus='PENDING'"),
	@NamedQuery(name = "FriendEntity.findAllFriends", query = "SELECT f FROM FriendEntity f WHERE f.friendUserInvited=:uSessionId AND f.friendRequestStatus='ACCEPTED' OR f.friendUserRequest=:uSessionId AND f.friendRequestStatus='ACCEPTED'")
	
})
public class FriendEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// ni caso
	@Id
	@Column(name = "friend_id")
	private Long friendId;

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "friend_request_date")
	private Date friendRequestDate;
	
	// PENDING, ACCEPTED, REJECTED
	@Column(name = "friend_request_status")
	private String friendRequestStatus;

	//USUARIO INVITADO A CONECTAR
	@Column(name = "friend_user_invited")
	private String friendUserInvited;

	
	//USUARIO QUE INVITA A CONECTAR
	@Column(name = "friend_user_request")
	private String friendUserRequest;

	public FriendEntity() {
	}

	public Long getFriendId() {
		return this.friendId;
	}

	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}

	public Date getFriendRequestDate() {
		return this.friendRequestDate;
	}

	public void setFriendRequestDate(Date friendRequestDate) {
		this.friendRequestDate = friendRequestDate;
	}

	public String getFriendRequestStatus() {
		return this.friendRequestStatus;
	}

	public void setFriendRequestStatus(String friendRequestStatus) {
		this.friendRequestStatus = friendRequestStatus;
	}

	
	 public String getFriendUserInvited() {
		 return this.friendUserInvited;
	}
	 
	 public void setFriendUserInvited(String friendUserInvited) {
 		this.friendUserInvited = friendUserInvited;
 	}
	 

	public String getFriendUserRequest() {
		return this.friendUserRequest;
	}

	public void setFriendUserRequest(String friendUserRequest) {
		this.friendUserRequest = friendUserRequest;
	}

}