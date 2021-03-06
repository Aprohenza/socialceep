package com.socialceep.controller;

import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.socialceep.dao.MessageDao;
import com.socialceep.dao.UserConversationDao;
import com.socialceep.dao.UserDao;
import com.socialceep.entity.ConversationEntity;
import com.socialceep.entity.ConversationsMessageEntity;
import com.socialceep.entity.MessageEntity;
import com.socialceep.entity.UserConversationEntity;
import com.socialceep.entity.UserEntity;
import com.socialceep.form.NewMessageFromMessaging;
import com.socialceep.session.MessageConversation;
import com.socialceep.session.ParticipantConversation;
import com.socialceep.session.SessionManager;
import com.socialceep.session.UserConversation;
import com.socialceep.session.UserSession;

@Controller
public class MessagingController {

	private UserSession uSession;

	@RequestMapping(value = "/messaging", method = RequestMethod.GET)
	public String gettingConversations(ModelMap model, HttpServletRequest request) {

		this.uSession = (UserSession) request.getSession().getAttribute("uSession");

		System.out.println("INIT MESSAGING. USER ID: " + this.uSession.getUserProfileId());

		if (this.uSession.getUserConversations() != null && this.uSession.getUserConversations().size() != 0) {

			System.out.println("CONVERSAVIONES YA CARGADAS.");

			// MyWebScket.sendMessageToClient(uSession.getUserConversations(),
			// "conversations", uSession.getUserProfileId());

			return "redirect:/messaging/thread/" + uSession.getUserConversations().get(0).getConversationThread();

		} else {

			System.out.println("INICIALIZANDO CONVERSACIONES DE LA SESSION.");

			// inicializar conversaciones de la session
			List<ConversationEntity> conversations = UserDao.getConversationsOfUser(this.uSession.getUserProfileId());

			if (conversations == null || conversations.size() == 0)
				return "redirect:/messaging/compose";

			List<UserConversation> userConversationsList = new ArrayList<>();

			for (ConversationEntity cE : conversations) {
				UserConversation userConversation = new UserConversation();
				List<ParticipantConversation> participantConversationList = new ArrayList<>();
				List<MessageConversation> messageConversationList = new ArrayList<>();
				userConversation.setConversationThread(cE.getConversationThread()); // thread de la conversacion

				// participantes de la conversacion
				for (UserEntity uE : cE.getUsers()) {
					if (!uE.getUserId().equals(this.uSession.getUserProfileId())) {
						ParticipantConversation pC = new ParticipantConversation();
						pC.setParticipantConversationThread(cE.getConversationThread());
						pC.setUserProfileId(uE.getUserId());
						pC.setUserProfileLastName(uE.getUserLastname());
						pC.setUserProfileName(uE.getUserName());
						pC.setUserProfileNationality(uE.getUserNationality());
						pC.setUserProfilePhotoProfile(Long.toString(uE.getUserPhotoProfile()));
						pC.setUserProfileRole(uE.getUserRole().getRole().getRoleName());
						participantConversationList.add(pC); // add participant
					}

				}

				// messages en la conversacion
				for (ConversationsMessageEntity cMeE : cE.getConversationsMessages()) {
					MessageConversation messageConversation = new MessageConversation();
					messageConversation.setMessageConversationThread(cE.getConversationThread());
					messageConversation.setMessageAuthorId((cMeE.getMessage().getMessageAuthor().getUserId()));
					messageConversation.setMessageAuthor((cMeE.getMessage().getMessageAuthor().getUserName() + " "
							+ cMeE.getMessage().getMessageAuthor().getUserLastname()));
					messageConversation.setMessageBody(cMeE.getMessage().getMessageBody());
					messageConversation.setMessageDate(cMeE.getMessage().getMessageDate());
					messageConversation.setMessageId(cMeE.getMessage().getMessageId());
					messageConversation
							.setMessageAuthorPhotoProfile(cMeE.getMessage().getMessageAuthor().getUserPhotoProfile());
					// messageConversation.set;

					messageConversationList.add(messageConversation);
				}

				userConversation.setParticipantsConversation(participantConversationList); // add lista de participantes
																							// en la conversacion
				userConversation.setMessageConversation(messageConversationList); // add lista de mensajes en la
																					// conversacion

				userConversationsList.add(userConversation);
			}

			this.uSession.setUserConversations(userConversationsList);

			// MyWebScket.sendMessageToClient(uSession.getUserConversations(),
			// "conversations", uSession.getUserProfileId());

		}

		MyWebScket.sendMessageToClient(uSession.getUserConversations(), "conversations", uSession.getUserProfileId());
		return "redirect:/messaging/thread/" + uSession.getUserConversations().get(0).getConversationThread();
	}

	@RequestMapping(value = "/messaging/thread/{messageThread}", method = RequestMethod.GET)
	public String messaging(Model model, @PathVariable("messageThread") String messageThread) {

		model.addAttribute("message", new NewMessageFromMessaging());

		model.addAttribute("conversations", uSession.getUserConversations());
		model.addAttribute("userSession", uSession);

		for (UserConversation uC : uSession.getUserConversations()) {
			if (uC.getConversationThread().equals(messageThread)) {
				model.addAttribute("messages", uC.getMessageConversation());
			}
		}

		model.addAttribute("hasConversations", true);
		model.addAttribute("messageThread", messageThread);

		return "messagingProfie";
	}

	@RequestMapping(value = "/messaging/compose", method = RequestMethod.GET)
	public String composeMessaging(Model model) {

		boolean hasConversations = false;

		model.addAttribute("hasConversations", hasConversations);
		model.addAttribute("message", new NewMessageFromMessaging());

		model.addAttribute("userSession", uSession);

		return "messagingProfie";
	}

	@RequestMapping(value = "/messaging/insert", method = RequestMethod.POST)
	public ResponseEntity<MessageConversation> insertMessaging(HttpServletRequest request) {

		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		UserSession uSession = (UserSession) request.getSession().getAttribute("uSession");

		MessageConversation mC = new MessageConversation();

		String messageThread = request.getParameter("messageThread");
		String messageBody = request.getParameter("messageBody");
		// message.setMessageAuthor(uSession.getUserProfileId()); // setear author del
		// mensaje

		System.out.println("MESSAGE TRHEAD: " + messageThread);

		if (messageThread == null || messageThread.equals("")) {
			System.out.println("CREANDO NUEVA CONVERSACION");
			// no hay conversacion. Hay que crearla

			ConversationEntity cE = new ConversationEntity();

			String newMessageThread = Long.toString(System.currentTimeMillis()); // generate new conversation
			String conversationCreated = DATE_FORMAT.format(new Date());

			// UserEntity userConversationRecipient =
			// UserDao.getUserById(request.getParameter("messageRecipient"));
			// UserEntity userConversationAuthor =
			// UserDao.getUserById(uSession.getUserProfileId());

			System.out.println("CREANDO CONVERSACION CON USUARIO: " + request.getParameter("messageRecipient"));

			/*
			 * List<UserEntity> usersInConversation = new ArrayList<UserEntity>();
			 * usersInConversation.add(userConversationAuthor);
			 * usersInConversation.add(userConversationRecipient);
			 */

			// CONVERSACION
			cE.setConversationCreated(conversationCreated); // fecha creacion de la conversacion
			cE.setConversationThread(newMessageThread); // conversation thread
			// cE.setUsers(usersInConversation); // usuarios en la conversacion

			List<String> usersInConversation = new ArrayList<String>();

			usersInConversation.add(uSession.getUserProfileId());
			usersInConversation.add(request.getParameter("messageRecipient"));
			// uCe.setUser(usersInConversation);

			System.out.println("CANTIDD DE USERS EN LA CONVERSACION: " + usersInConversation.size());

			// cE.setConversationsMessages(conversationsMessages);

			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("socialceep");
			EntityManager entitymanager = emfactory.createEntityManager();

			entitymanager.getTransaction().begin();

			entitymanager.persist(cE);

			// GRABACION DE USER CONVERSATIONS
			for (String s : usersInConversation) {
				UserConversationEntity uCe = new UserConversationEntity(Long.parseLong(newMessageThread), s);
				entitymanager.persist(uCe);
			}

			entitymanager.getTransaction().commit();

			entitymanager.close();
			emfactory.close();

			mC.setMessageAuthorId(uSession.getUserProfileId());
			System.out.println("MESSAGE THREAD SETEADO EN CONTROLLER: " + newMessageThread);
			mC.setMessageConversationThread(newMessageThread);
			mC.setMessageAuthor(uSession.getUserProfileName() + " " + uSession.getUserProfileLastName());
			mC.setMessageAuthorPhotoProfile(Long.parseLong(uSession.getUserProfilePhotoProfile()));
			mC.setMessageBody(messageBody);
			mC.setMessageDate(DATE_FORMAT.format(new Date()));

		} else {
			// ya hay comversacion
			try {

				HashMap<String, UserSession> sessions = SessionManager.getSessions();

				List<UserConversationEntity> usersInConversation = UserConversationDao
						.getUserConversationByConversation(Long.parseLong(messageThread));

				for (UserConversationEntity uCE : usersInConversation) {
					if (sessions.containsKey(uCE.getUser())) {
						UserSession uS = sessions.get(uCE.getUser());
						for (UserConversation uc : uS.getUserConversations()) {
							if (uc.getConversationThread().equals(messageThread)) {
								mC.setMessageAuthorId(uSession.getUserProfileId());
								mC.setMessageConversationThread(messageThread);
								mC.setMessageAuthor(uSession.getUserProfileName() + " " + uSession.getUserProfileLastName());
								mC.setMessageAuthorPhotoProfile(Long.parseLong(uSession.getUserProfilePhotoProfile()));
								mC.setMessageBody(messageBody);
								mC.setMessageDate(DATE_FORMAT.format(new Date()));

								uc.getMessageConversation().add(mC);
							}

						}

					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		Runnable createMessage = new MessageDao(uSession, MessageDao.ACTION_CREATE_NEW_MESSAGE, mC);
		new Thread(createMessage).start();

		return new ResponseEntity<MessageConversation>(mC, HttpStatus.CREATED);
	}
}
