package com.formbean.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.formbean.controller.MyWebScket;
import com.formbean.entity.ConversationEntity;
import com.formbean.entity.ConversationsMessageEntity;
import com.formbean.entity.MessageEntity;
import com.formbean.entity.UserEntity;
import com.formbean.form.NewMessageFromMessaging;
import com.formbean.session.MessageConversation;
import com.formbean.session.SessionManager;
import com.formbean.session.UserSession;

public class MessageDAO implements Runnable {
	
	public static final String ACTION_CREATE_NEW_MESSAGE = "create";
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	private String action;
	private Object obj;
	private MessageConversation mC;

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
	EntityManager entitymanager = emfactory.createEntityManager();
	
	private UserSession uSession;
	
	public MessageDAO() {
	}
	
	public MessageDAO(UserSession uSession, String action, Object obj, MessageConversation mC) {
		this.action = action;
		this.obj = obj;
		this.mC = mC;
		this.uSession = uSession;
	}
	
	public List<ConversationsMessageEntity> getMessagesOfConversation(ConversationEntity conversation) {
		List<ConversationsMessageEntity> messagesOfConversation = conversation.getConversationsMessages();
		return messagesOfConversation;
	}

	public void ceateNewMessage(UserSession uSession, NewMessageFromMessaging message, MessageConversation mC) {
		
		
		//se obtiene el conversation entity
		ConversationEntity conversation = entitymanager.find(ConversationEntity.class, message.getMessageThread());
		//se obtiene user entity del author (session)
		UserEntity userEntity = entitymanager.find(UserEntity.class, message.getMessageAuthor());
		
		ConversationsMessageEntity conversationsMessage = new ConversationsMessageEntity();
		//seteamos el conversation entity en conversation message
		conversationsMessage.setConversation(conversation);
		
		MessageEntity messageEntity = new MessageEntity();
		
		//creamos el message
		messageEntity.setMessageAuthor(userEntity);
		messageEntity.setConversationsMessage(conversationsMessage);
		messageEntity.setMessageBody(message.getMessageBody());
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
			ceateNewMessage(this.uSession, (NewMessageFromMessaging) this.obj, this.mC);
		}
		
	}
}
