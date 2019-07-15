package com.socialceep.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.socialceep.entity.UserConversationEntity;


public class UserConversationDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static List<UserConversationEntity> getUserConversationByConversation(Long conversationThread) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("socialceep");
		EntityManager entitymanager = emfactory.createEntityManager();

		TypedQuery<UserConversationEntity> query = entitymanager.createNamedQuery("UserConversationEntity.findByConversationThread",
				UserConversationEntity.class);
		
		query.setParameter("conversationThread", conversationThread);
		
		List<UserConversationEntity> userConversationList = query.getResultList();

		entitymanager.close();
		emfactory.close();
		return userConversationList;
		
	}

}
