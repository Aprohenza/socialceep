package com.socialceep.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.socialceep.entity.CycleEntity;

public class CycleDao {
	
	private static EntityManagerFactory emfactory;
	private static EntityManager entitymanager;
	
	public static List<CycleEntity> getAllCycleEntity() {
		
		emfactory = Persistence.createEntityManagerFactory("socialceep");
		entitymanager = emfactory.createEntityManager();
		
		TypedQuery<CycleEntity> q= entitymanager.createNamedQuery("CycleEntity.findAll", CycleEntity.class);
		
		List<CycleEntity> cycleEntityList = q.getResultList();
		
		entitymanager.close();
		emfactory.close();
		
		return cycleEntityList;
	}

}
