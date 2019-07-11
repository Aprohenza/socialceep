package com.formbean.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.formbean.entity.CycleCurseSessionEntity;

public class CycleCurseSessionDao {
	
	public static List<CycleCurseSessionEntity> getAllCycleCurseSession() {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
		EntityManager entitymanager = emfactory.createEntityManager();

		TypedQuery<CycleCurseSessionEntity> q = entitymanager.createNamedQuery("CycleCurseSessionEntity.findAll", CycleCurseSessionEntity.class);
		List<CycleCurseSessionEntity> cycleCurseSessionList = q.getResultList();

		entitymanager.close();
		emfactory.close();
		
		return cycleCurseSessionList;
		
	}
	
	public static CycleCurseSessionEntity getCycleCurseSessionToUser(Long cycleId, Integer cycleCurseId, Integer cycleSessionId) {
		System.out.println("ESTOY EN CYCLE-CURSE-SESSION");
		System.out.println("CYCLE ID LLEGADO: " + cycleId);
		System.out.println("CYCLE CURSE ID LLEGADO: " + cycleCurseId);
		System.out.println("CYCLE SESSION ID LLEGADO: " + cycleSessionId);
	
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
		EntityManager entitymanager = emfactory.createEntityManager();

		TypedQuery<CycleCurseSessionEntity> q = entitymanager.createNamedQuery("CycleCurseSessionEntity.findClassToUser", CycleCurseSessionEntity.class);
		q.setParameter("cycleId", cycleId); //long
		q.setParameter("cycleCurseId", cycleCurseId); //int
		q.setParameter("cycleSessionId", cycleSessionId); //int 
		CycleCurseSessionEntity cycleCurseSession = q.getSingleResult();

		entitymanager.close();
		emfactory.close();
		
		return cycleCurseSession;
		
	}
	
	

}
