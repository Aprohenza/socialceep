package com.formbean.controller;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.formbean.dao.ConversationDAO;
import com.formbean.dao.MessageDAO;
import com.formbean.dao.UserDAO;
import com.formbean.dto.ConversationModel;
import com.formbean.dto.MessageModel;
import com.formbean.entity.ConversationEntity;
import com.formbean.entity.ConversationsMessageEntity;
import com.formbean.entity.LoginEntity;
import com.formbean.entity.MessageEntity;
import com.formbean.entity.UserEntity;
import com.formbean.form.NewMessageFromMessaging;
import com.formbean.session.MessageConversation;
import com.formbean.session.ParticipantConversation;
import com.formbean.session.SessionManager;
import com.formbean.session.UserConversation;
import com.formbean.session.UserSession;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

@Controller
public class MessagingController {
	
	
	List<MessageModel> messageModel = new ArrayList<>();
	
	private UserSession uSession;

	@RequestMapping(value = "/messaging", method = RequestMethod.GET)
	public String gettingConversations(ModelMap model, HttpServletRequest request) {
		
		this.uSession = (UserSession) request.getSession().getAttribute("uSession");
		
		System.out.println("INIT MESSAGING. USER ID: " + this.uSession.getUserProfileId());
		
		if(this.uSession.getUserConversations() != null && this.uSession.getUserConversations().size() != 0) {
			
			System.out.println("ENTRANDO POR METODO YA CONVERSATIONS.");
			
			//MyWebScket.sendMessageToClient(uSession.getUserConversations(), "conversations", uSession.getUserProfileId());
			
			return "redirect:/messaging/thread/" + uSession.getUserConversations().get(0).getConversationThread();
			
		}else {
			
			//inicializar conversaciones de la session
			List<ConversationEntity> conversations = UserDAO.getConversationsOfUser(this.uSession.getUserProfileId());
			
			List<UserConversation> userConversationsList = new ArrayList<>();
			
			
			System.out.println("CONVERSATIONS: " + conversations);
			
			for(ConversationEntity cE: conversations) {
				UserConversation userConversation = new UserConversation();
				List<ParticipantConversation> participantConversationList = new ArrayList<>();
				List<MessageConversation> messageConversationList = new ArrayList<>();
				userConversation.setConversationThread(cE.getConversationThread()); // thread de la conversacion
				
				
				
				//participantes de la conversacion
				for(UserEntity uE: cE.getUsers()) {
					if(!uE.getUserId().equals(this.uSession.getUserProfileId())) {
						ParticipantConversation pC = new ParticipantConversation();
						pC.setParticipantConversationThread(cE.getConversationThread());
						pC.setParticipantId(uE.getUserId());
						pC.setParticipantLastName(uE.getUserLastname());
						pC.setParticipantName(uE.getUserName());
						pC.setParticipantNationality(uE.getUserNationality());
						pC.setParticipantPhotoProfile(Long.toString(uE.getUserPhotoProfile()));
						pC.setParticipantRole(uE.getUserRole().getRole().getRoleName());
						participantConversationList.add(pC); // add participant
					}
					
				}
			
				
				//messages en la conversacion
				for(ConversationsMessageEntity cMeE: cE.getConversationsMessages()) {
					MessageConversation messageConversation = new MessageConversation();
					messageConversation.setMessageConversationThread(cE.getConversationThread());
					messageConversation.setMessageAuthorId((cMeE.getMessage().getMessageAuthor().getUserId()));
					messageConversation.setMessageAuthor((cMeE.getMessage().getMessageAuthor().getUserName() + " " + cMeE.getMessage().getMessageAuthor().getUserLastname()));
					messageConversation.setMessageBody(cMeE.getMessage().getMessageBody());
					messageConversation.setMessageDate(cMeE.getMessage().getMessageDate());
					messageConversation.setMessageId(cMeE.getMessage().getMessageId());
					messageConversation.setMessageAuthorPhotoProfile(cMeE.getMessage().getMessageAuthor().getUserPhotoProfile());
					//messageConversation.set;
					
					
					messageConversationList.add(messageConversation);
				}
				
				
				userConversation.setParticipantsConversation(participantConversationList); // add lista de participantes en la conversacion
				userConversation.setMessageConversation(messageConversationList); // add lista de mensajes en la conversacion
				
				userConversationsList.add(userConversation);
			}
			
			this.uSession.setUserConversations(userConversationsList);
			
			//MyWebScket.sendMessageToClient(uSession.getUserConversations(), "conversations", uSession.getUserProfileId());
			
			
		}
		
		MyWebScket.sendMessageToClient(uSession.getUserConversations(), "conversations", uSession.getUserProfileId());
		return "redirect:/messaging/thread/" + uSession.getUserConversations().get(0).getConversationThread();
	}

	@RequestMapping(value = "/messaging/thread/{messageThread}", method = RequestMethod.GET)
	public String messaging(Model model, @PathVariable("messageThread") String messageThread) {
		
		
		
		model.addAttribute("message", new NewMessageFromMessaging());
		
		
		model.addAttribute("conversations", uSession.getUserConversations());
		model.addAttribute("userSession", uSession);
		
		for(UserConversation uC: uSession.getUserConversations()) {
			if(uC.getConversationThread().equals(messageThread)) {
				System.out.println("DENTRO DE THREAD: " + messageThread);
				model.addAttribute("messages", uC.getMessageConversation());
			}
		}
		
		/*
		 * model.addAttribute("messageThread", messageThread);
		 * model.addAttribute("messages", messageModel);
		 */
		model.addAttribute("messageThread", messageThread);

		return "messagingProfie";
	}
	
	//metodo que obtines los mensajes de una conversacion
	@RequestMapping(value = "/messaging/thread/messages/{conversationThread}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MessageModel>> getMessagesConversation(@PathVariable("conversationThread") String conversationThread) {
		
				 
		 ConversationDAO conversationDao = new ConversationDAO();		
		 MessageDAO messageDao = new MessageDAO();
		 List<ConversationsMessageEntity> messagesWhitThread = new ArrayList<>();
		  
		 try {
			messagesWhitThread =  messageDao.getMessagesOfConversation( conversationDao.getCoversationByThread(conversationThread));
			
			
			
		  
		 	for(ConversationsMessageEntity cm: messagesWhitThread) {
		 		messageModel.add(new MessageModel(cm));
		 	}
		 }catch (Exception e) {
			 //model.addAttribute("error", "There is no conversation");
		 }
		 
		 
		return new ResponseEntity<List<MessageModel>>(messageModel, HttpStatus.OK);
	}
	
	

	@RequestMapping(value = "/messaging/insert", method = RequestMethod.POST)
	public ResponseEntity<MessageConversation> insertMessaging(@ModelAttribute NewMessageFromMessaging message, HttpServletRequest request) {
		
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		UserSession uSession = (UserSession) request.getSession().getAttribute("uSession");
		
		message.setMessageAuthor(uSession.getUserProfileId());
		
		MessageConversation mC = new MessageConversation();
		
		try {
			
			
			for(UserConversation uC: uSession.getUserConversations()) {
				if(uC.getConversationThread().equals(message.getMessageThread())) {
					
					mC.setMessageAuthor(uSession.getUserProfileName() + " " + uSession.getUserProfileLastName());
					mC.setMessageAuthorPhotoProfile(Long.parseLong(uSession.getUserProfilePhoto()));
					mC.setMessageBody(message.getMessageBody());
					mC.setMessageDate(DATE_FORMAT.format(new Date()));
					
					uC.getMessageConversation().add(mC);
				}
			}
		 	
		 }catch (Exception e) {
			e.printStackTrace();
		 }
		
		Runnable createMessage = new MessageDAO(uSession, MessageDAO.ACTION_CREATE_NEW_MESSAGE, message, mC);
		
		new Thread(createMessage).start();
		
	    //esto es lo q enviamos al message box del view
		/*
		 * MessageModel mm = new MessageModel();
		 * 
		 * mm.setBodyMessage(message.getMessageBody());
		 * mm.setAuthorNameMessage(uSession.getUserProfileName() + " " +
		 * uSession.getUserProfileLastName()); mm.setDateMessage(DATE_FORMAT.format(new
		 * Date()));
		 * 
		 * messageModel.add(mm);
		 */
		
		//model.addAttribute("messages", uC.getMessageConversation());

		return new ResponseEntity<MessageConversation>(mC, HttpStatus.CREATED);
	}
}
