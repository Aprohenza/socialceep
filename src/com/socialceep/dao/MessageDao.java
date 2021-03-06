package com.socialceep.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.socialceep.controller.MyWebScket;
import com.socialceep.entity.ConversationEntity;
import com.socialceep.entity.ConversationsMessageEntity;
import com.socialceep.entity.MessageEntity;
import com.socialceep.entity.UserEntity;
import com.socialceep.form.NewMessageFromMessaging;
import com.socialceep.session.MessageConversation;
import com.socialceep.session.SessionManager;
import com.socialceep.session.UserSession;

public class MessageDao implements Runnable {
	
	public static final String ACTION_CREATE_NEW_MESSAGE = "create";
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	private String action;
	private MessageConversation mC;

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("socialceep");
	EntityManager entitymanager = emfactory.createEntityManager();
	
	private UserSession uSession;
	
	public MessageDao() {
	}
	
	public MessageDao(UserSession uSession, String action, MessageConversation mC) {
		this.action = action;
		this.mC = mC;
		this.uSession = uSession;
	}
	
	public List<ConversationsMessageEntity> getMessagesOfConversation(ConversationEntity conversation) {
		List<ConversationsMessageEntity> messagesOfConversation = conversation.getConversationsMessages();
		return messagesOfConversation;
	}

	public void ceateNewMessage(UserSession uSession, MessageConversation mC) {
		
		System.out.println("MESSAGE THREAD RECOGIDO EN MESSAGE DAO: " + mC.getMessageConversationThread());
		
		//se obtiene el conversation entity
		ConversationEntity conversation = entitymanager.find(ConversationEntity.class, mC.getMessageConversationThread());
		System.out.println("CONVERSACIONN OBTENIDA PARA GUARDAR EL MENSAJE: " + conversation.getConversationThread());
		//se obtiene user entity del author (session)
		System.out.println("AUTHOR ID MENSAJE: " + mC.getMessageAuthorId());
		UserEntity userEntity = entitymanager.find(UserEntity.class, mC.getMessageAuthorId());
		
		System.out.println("AUTHOR DEL MENSAJE: " + userEntity.getUserId());
		
		ConversationsMessageEntity conversationsMessage = new ConversationsMessageEntity();
		//seteamos el conversation entity en conversation message
		conversationsMessage.setConversation(conversation);
		
		MessageEntity messageEntity = new MessageEntity();
		
		//creamos el message
		messageEntity.setMessageAuthor(userEntity);
		messageEntity.setConversationsMessage(conversationsMessage);
		messageEntity.setMessageBody(mC.getMessageBody());
		messageEntity.setMessageDate(DATE_FORMAT.format(new Date()));
		messageEntity.setMessageStatus("NOTSEEN");
		
		conversationsMessage.setMessage(messageEntity);
		
		entitymanager.getTransaction( ).begin( );
		
		entitymanager.persist( messageEntity ); 
			
		entitymanager.getTransaction().commit();
	
		entitymanager.close( );
		emfactory.close( );
		
		List<UserEntity> participants = conversation.getUsers();
		List<String> participantDestinatarios = new ArrayList<>();
		
		for(UserEntity uE: participants) {
			if(!uE.getUserId().equals(uSession.getUserProfileId())) {
				String participantId = uE.getUserId();
				participantDestinatarios.add(participantId);
			}
		}
		
		//crear funcion q manda el mensaje por el socket
		MyWebScket.sendNewMessageChat(mC, "newMessageChat", participantDestinatarios);
		
		
	}

	@Override
	public void run() {
		if(this.action.equals(ACTION_CREATE_NEW_MESSAGE)) {
			System.out.println("Ejecutando hilo crear nuevo mensaje.");
			ceateNewMessage(this.uSession, this.mC);
		}
		
	}
}
