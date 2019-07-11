package com.formbean.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.formbean.entity.ConversationEntity;

public class ConversationDAO {

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
	EntityManager entitymanager = emfactory.createEntityManager();

	public ConversationEntity getCoversationByThread(String conversationThread){
		ConversationEntity conversation = entitymanager.find(ConversationEntity.class, conversationThread);
		
		return conversation;
	}

}
