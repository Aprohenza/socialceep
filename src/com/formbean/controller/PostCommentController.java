package com.formbean.controller;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.formbean.entity.PostCommentEntity;
import com.formbean.session.UserSession;

@Controller
public class PostCommentController {
	
	
	private UserSession uSession;
	
	@RequestMapping(value = "/comment/new", method = RequestMethod.POST)
	public ResponseEntity<Void> createNewCommentOnPost(@RequestParam("postId") Long postId, @RequestParam("comment") String comment, ModelMap model, HttpServletRequest request) {
		
		//Long time = System.currentTimeMillis();
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
		EntityManager entitymanager = emfactory.createEntityManager();
		
		PostCommentEntity postCommentEntity = new PostCommentEntity();
		
		uSession = (UserSession) request.getSession().getAttribute("uSession");
		
		
		postCommentEntity.setPostcommentPostId(postId);
		postCommentEntity.setPostcommentPostAuthor(uSession.getUserProfileId());
		postCommentEntity.setPostcommentBody(comment);
		postCommentEntity.setPostcommentPostDate(System.currentTimeMillis());
		
		
		
		entitymanager.getTransaction().begin();

		entitymanager.persist(postCommentEntity);

		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
		
		 
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
