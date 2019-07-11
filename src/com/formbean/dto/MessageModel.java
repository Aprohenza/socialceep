package com.formbean.dto;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.formbean.entity.ConversationsMessageEntity;
import com.formbean.entity.UserEntity;

public class MessageModel {

	// private ConversationsMessageEntity messagesPerConversatios;

	private String threadConversationMessage;

	private String authorMessage;

	private String authorIdMessage;

	private String dateMessage;

	private String bodyMessage;

	public MessageModel() {

	}

	public MessageModel(ConversationsMessageEntity messagesPerConversation) {
		// this.messagesPerConversatios = messagesPerConversatios;

		setAuthorIdMessage(messagesPerConversation.getMessage().getMessageAuthor());
		setThreadConversationMessage(messagesPerConversation.getConversation().getConversationThread());
		setAuthorMessage(messagesPerConversation.getMessage().getMessageAuthor());
		setDateMessage(messagesPerConversation.getMessage().getMessageDate());
		setBodyMessage(messagesPerConversation.getMessage().getMessageBody());
	}

	/**
	 * @param authorMessage
	 * @param dateMessage
	 * @param bodyMessage
	 */
	public MessageModel(String authorMessage, String dateMessage, String bodyMessage,
			ConversationsMessageEntity messagesPerConversatios) {
		this.authorMessage = authorMessage;
		this.dateMessage = dateMessage;
		this.bodyMessage = bodyMessage;
		//this.messagesPerConversatios = messagesPerConversatios;
	}

	/**
	 * @return the authorMessage
	 */
	public String getAuthorMessage() {
		return authorMessage;
	}

	/**
	 * @param authorMessage the authorMessage to set
	 */
	public void setAuthorMessage(String authorMessage) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
		EntityManager entitymanager = emfactory.createEntityManager();
		UserEntity ue = entitymanager.find(UserEntity.class, authorMessage);
		// this.setAuthorIdMessage(authorMessage);
		this.authorMessage = ue.getUserName() + " " + ue.getUserLastname();
	}
	
	public void setAuthorNameMessage(String authorMessage) {
		this.authorMessage = authorMessage;
	}

	/**
	 * @return the dateMessage
	 */
	public String getDateMessage() {
		return dateMessage;
	}

	/**
	 * @param dateMessage the dateMessage to set
	 */
	public void setDateMessage(String dateMessage) {
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd 'de' MMM");
		Date myDate = Date.valueOf(dateMessage.split(" ")[0]);
		this.dateMessage = DATE_FORMAT.format(myDate);
	}

	/**
	 * @return the bodyMessage
	 */
	public String getBodyMessage() {
		return bodyMessage;
	}

	/**
	 * @param bodyMessage the bodyMessage to set
	 */
	public void setBodyMessage(String bodyMessage) {
		this.bodyMessage = bodyMessage;
	}

	/*	*//**
			 * @return the messagesPerConversatios
			 */
	/*
	 * public ConversationsMessageEntity getMessagesPerConversatios() { return
	 * messagesPerConversatios; }
	 * 
	 *//**
		 * @param messagesPerConversatios the messagesPerConversatios to set
		 *//*
			 * public void setMessagesPerConversatios(ConversationsMessageEntity
			 * messagesPerConversatios) { this.messagesPerConversatios =
			 * messagesPerConversatios; }
			 */

	/**
	 * @return the threadConversationMessage
	 */
	public String getThreadConversationMessage() {
		return threadConversationMessage;
	}

	/**
	 * @param threadConversationMessage the threadConversationMessage to set
	 */
	public void setThreadConversationMessage(String threadConversationMessage) {
		this.threadConversationMessage = threadConversationMessage;
	}

	/**
	 * @return the authorIdMessage
	 */
	public String getAuthorIdMessage() {
		return authorIdMessage;
	}

	/**
	 * @param authorIdMessage the authorIdMessage to set
	 */
	public void setAuthorIdMessage(String authorIdMessage) {
		this.authorIdMessage = authorIdMessage;
	}

}
