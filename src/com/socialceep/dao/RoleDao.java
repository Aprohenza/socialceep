package com.socialceep.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.socialceep.entity.RoleEntity;

public class RoleDao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static List<RoleEntity> getAllRoles(){
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("socialceep");
		EntityManager entitymanager = emfactory.createEntityManager();		
		
		TypedQuery<RoleEntity> query = entitymanager.createNamedQuery("RoleEntity.findAll", RoleEntity.class);
		
		List<RoleEntity> roleEntityList = query.getResultList();		
		
		entitymanager.close();
		emfactory.close();
		
		return roleEntityList;
	}

}
