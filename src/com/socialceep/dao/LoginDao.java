package com.socialceep.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.socialceep.entity.LoginEntity;

public class LoginDao {

	public static String createLoginId(String userName, String userLastName) {

		String loginId = userName.trim().toLowerCase();
		
		userLastName = userLastName.trim().toLowerCase();
		String parts[] = userLastName.split(" ");
		
		for(String s: parts) {
			loginId = loginId + "." + s.trim();
		}
		return loginId;

	}

	public static void createNewLogin(String loginId, String loginEmail, String loginPassword) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("socialceep");
		EntityManager entitymanager = emfactory.createEntityManager();

		LoginEntity lE = new LoginEntity();
		lE.setLoginId(loginId);
		lE.setLoginEmail(loginEmail);
		lE.setLoginPassword(loginPassword);

		entitymanager.getTransaction().begin();

		entitymanager.persist(lE);

		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();

	}

}
