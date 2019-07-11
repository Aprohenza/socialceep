package com.formbean.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "USERS_CONVERSATIONS")
@NamedQueries({
	@NamedQuery(name = "UserConversationEntity.findAll", query = "SELECT uC FROM UserConversationEntity uC")
})
public class UserConversationEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "conversation_thread")
	private Long conversationThread;
	
	@Column(name = "user_id")
	private String user;
	
	public UserConversationEntity() {
		
	}
	
	public UserConversationEntity(Long conversationThread, String user) {
		this.conversationThread = conversationThread;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getConversationThread() {
		return conversationThread;
	}

	public void setConversationThread(Long conversationThread) {
		this.conversationThread = conversationThread;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
