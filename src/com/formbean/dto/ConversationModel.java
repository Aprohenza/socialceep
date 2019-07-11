package com.formbean.dto;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.formbean.entity.ConversationEntity;
import com.formbean.entity.ConversationsMessageEntity;
import com.formbean.entity.UserEntity;

public class ConversationModel {
	
	private ConversationEntity conversationEntity;	
	
	private String conversationThread;
	
	private String nameOtherUser;
	
	private String previewLastMessageOtherUser;
	
	private String dateLastMessageOtherUser;

	public ConversationModel() {
		
	}

	public ConversationModel(ConversationEntity conversationEntity) {		
		this.conversationEntity = conversationEntity;
		setConversationThread(this.conversationEntity.getConversationThread());
		setNameOtherUser(this.conversationEntity.getConversationsMessages());		
	}

	public ConversationEntity getConversationEntity() {
		return conversationEntity;
	}

	public void setConversationEntity(ConversationEntity conversationEntity) {
		this.conversationEntity = conversationEntity;
	}

	public String getConversationThread() {
		return conversationThread;
	}

	public void setConversationThread(String conversationThread) {
		this.conversationThread = conversationThread;
	}

	public String getNameOtherUser() {
		return nameOtherUser;
	}

	public void setNameOtherUser(List<ConversationsMessageEntity> messages) {
		String nameOtherUser = null;
		System.out.println("cantidad: " + messages.size());
		for(ConversationsMessageEntity message : messages) {
			System.out.println("message: " + message.getCoversationMessageId());
			System.out.println("autor: " + message.getMessage().getMessageAuthor());
			System.out.println("body: " + message.getMessage().getMessageBody());
			if(!message.getMessage().getMessageAuthor().equals("aldo.prohenza.hernandez")) {
				System.out.println("esta aki: " + message.getMessage().getMessageAuthor());
				System.out.println("xxxxxxxxxxxx");
				EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
				EntityManager entitymanager = emfactory.createEntityManager();
				UserEntity ue = entitymanager.find(UserEntity.class, message.getMessage().getMessageAuthor());
				nameOtherUser = ue.getUserName() + " " + ue.getUserLastname();
				System.out.println("name other user: " + nameOtherUser);
				setDateLastMessageOtherUser(message.getMessage().getMessageDate());
				setPreviewLastMessageOtherUser(message.getMessage().getMessageBody());
			}
		}
		this.nameOtherUser = nameOtherUser;
	}

	public String getPreviewLastMessageOtherUser() {
		return previewLastMessageOtherUser;
	}

	public void setPreviewLastMessageOtherUser(String previewLastMessageOtherUser) {
		this.previewLastMessageOtherUser = previewLastMessageOtherUser;
	}

	/**
	 * @return the dateLastMessageOtherUser
	 */
	public String getDateLastMessageOtherUser() {
		return dateLastMessageOtherUser;
	}

	/**
	 * @param dateLastMessageOtherUser the dateLastMessageOtherUser to set
	 */
	public void setDateLastMessageOtherUser(String dateLastMessageOtherUser) {
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd 'de' MMM");
		
		Date myDate = Date.valueOf(dateLastMessageOtherUser.split(" ")[0]);
		
		this.dateLastMessageOtherUser = DATE_FORMAT.format(myDate);
	}
	
	
	
	
	
	
	

}
