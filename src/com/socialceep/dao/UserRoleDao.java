package com.socialceep.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.socialceep.entity.RoleEntity;
import com.socialceep.entity.UserRoleEntity;

public class UserRoleDao {
	
	public static void createNewUserRole(String userRoleUserid, String roleId) {
		
		System.out.println("ROLE LLEGADO A AUSER ROLE DAO: " + roleId);
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("socialceep");
		EntityManager entitymanager = emfactory.createEntityManager();
		
		UserRoleEntity uRoleEntity = new UserRoleEntity();
		
		uRoleEntity.setUserRoleUserid(userRoleUserid);
		
		List<RoleEntity> roleList = RoleDao.getAllRoles();
	
		for(RoleEntity rE: roleList){
			if(rE.getRoleName().toUpperCase().equals(roleId.toUpperCase())){
				uRoleEntity.setRole(rE);
			}			
		}
		
		
		entitymanager.getTransaction().begin();

		entitymanager.persist(uRoleEntity);
		
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
		
	}

}
