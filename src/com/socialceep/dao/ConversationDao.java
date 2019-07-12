package com.socialceep.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.socialceep.entity.ConversationEntity;

public class ConversationDao {

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("socialceep");
	EntityManager entitymanager = emfactory.createEntityManager();

	public ConversationEntity getCoversationByThread(String conversationThread){
		ConversationEntity conversation = entitymanager.find(ConversationEntity.class, conversationThread);
		
		return conversation;
	}

}
