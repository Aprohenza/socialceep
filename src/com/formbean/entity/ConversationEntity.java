package com.formbean.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;


/**
 * The persistent class for the CONVERSATIONS database table.
 * 
 */
@Entity
@Table(name="CONVERSATIONS")
@NamedQuery(name="ConversationEntity.findAll", query="SELECT c FROM ConversationEntity c")
public class ConversationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="conversation_thread")
	private String conversationThread;

	@Column(name="conversation_created")
	private String conversationCreated;

	//bi-directional many-to-many association to UserEntity
	@ManyToMany(mappedBy="conversations")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<UserEntity> users;

	//bi-directional many-to-one association to ConversationsMessageEntity
	@OneToMany(mappedBy="conversation")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ConversationsMessageEntity> conversationsMessages;

	public ConversationEntity() {
	}

	public String getConversationThread() {
		return this.conversationThread;
	}

	public void setConversationThread(String conversationThread) {
		this.conversationThread = conversationThread;
	}

	public String getConversationCreated() {
		return this.conversationCreated;
	}

	public void setConversationCreated(String conversationCreated) {
		this.conversationCreated = conversationCreated;
	}

	public List<UserEntity> getUsers() {
		return this.users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public List<ConversationsMessageEntity> getConversationsMessages() {
		return this.conversationsMessages;
	}

	public void setConversationsMessages(List<ConversationsMessageEntity> conversationsMessages) {
		this.conversationsMessages = conversationsMessages;
	}

	public ConversationsMessageEntity addConversationsMessage(ConversationsMessageEntity conversationsMessage) {
		getConversationsMessages().add(conversationsMessage);
		conversationsMessage.setConversation(this);

		return conversationsMessage;
	}

	public ConversationsMessageEntity removeConversationsMessage(ConversationsMessageEntity conversationsMessage) {
		getConversationsMessages().remove(conversationsMessage);
		conversationsMessage.setConversation(null);

		return conversationsMessage;
	}

}