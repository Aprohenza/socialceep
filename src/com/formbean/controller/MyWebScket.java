package com.formbean.controller;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.json.Json;
import javax.json.JsonValue;
import javax.json.stream.JsonGenerator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.formbean.dto.AttachmentDto;
import com.formbean.dto.PostModel;
import com.formbean.entity.PostEntity;
import com.formbean.entity.UserEntity;
import com.formbean.session.MessageConversation;
import com.formbean.session.UserFriendsSession;
import com.formbean.session.UserOwnPost;
import com.formbean.session.UserOwnPostSession;
import com.formbean.session.UserSession;
import com.google.gson.Gson;

@ServerEndpoint(value = "/endpoint/{username}")
public class MyWebScket {

	private Session session;
	private static Set<MyWebScket> endpoints = new CopyOnWriteArraySet<>();
	private static HashMap<String, String> users = new HashMap<>();

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
	EntityManager entitymanager = emfactory.createEntityManager();

	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) {
		System.out.println("onOpen:" + session.getId());

		this.session = session;
		endpoints.add(this);
		users.put(username, session.getId());

		try {
			this.session.getBasicRemote().sendText("{\"contentType\": \"sessionStatus\", \"content\": " + 1 + "}");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@OnClose
	public void onClose(Session session) {
		System.out.println("onClose: " + session.getId());

		endpoints.remove(this);

		try {
			this.session.getBasicRemote().sendText("{\"contentType\": \"sessionStatus\", \"content\": " + 0 + "}");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@OnMessage
	public void onMessage(String message, Session session) {

		System.out.println("onMessage: From=" + session.getId() + " Message=" + message);

	}

	@OnError
	public void onError(Throwable t) {
		System.out.println("onError:" + t.getMessage());
	}

	public static void sendMessageToClient(Object object, String type, String userSessionId) {

		String contentType = type;

		Gson gson = new Gson();

		String sessionScketId = users.get(userSessionId);

		endpoints.forEach(endpoint -> {
			synchronized (endpoint) {
				if (endpoint.session.getId().equals(sessionScketId)) {
					try {
						String json = gson.toJson(object);
						json = "{\"contentType\":\"" + contentType + "\", \"content\":" + json + "}";
						System.out.println("CONTENIDO ENVIADO AL CLIENTE: " + json);

						endpoint.session.getBasicRemote().sendText(json);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	public static void sendNotificationToSessionFriends(Object object, String type, String userSessionId,
			UserSession uSession) {

		String contentType = type;

		Gson gson = new Gson();
		
		//mandarmelo a mi mismo
		String sessionScketId = users.get(userSessionId);
		endpoints.forEach(endpoint -> {
			synchronized (endpoint) {
				if (endpoint.session.getId().equals(sessionScketId)) {
					try {
						String json = gson.toJson(object);
						json = "{\"contentType\":\"" + contentType + "\", \"content\":" + json + "}";
						System.out.println("CONTENIDO ENVIADO A MI MISMO: " + json);

						endpoint.session.getBasicRemote().sendText(json);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		for (UserFriendsSession userF : uSession.getUserFriendsSession()) {
			
			System.out.println("AMIGO DE LA SESSION: " + userF.getUserId());
			
			if (MyWebScket.users.containsKey(userF.getUserId())) {

				endpoints.forEach(endpoint -> {
					synchronized (endpoint) {
						if (endpoint.session.getId().equals(MyWebScket.users.get(userF.getUserId()))) {
							try {
								String json = gson.toJson(object);
								json = "{\"contentType\":\"" + contentType + "\", \"content\":" + json + "}";
								System.out.println("CONTENIDO ENVIADO AL CLIENTE: " + json);

								endpoint.session.getBasicRemote().sendText(json);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				});

			}else {
				System.out.println("NO HAY AMIGOS CONECTADOS!!!");
			}

		}

	}

	public static void sendNewMessageChat(MessageConversation mC, String type, List<String> participantDestinatarios) {
		

		String contentType = type;

		Gson gson = new Gson();
		
		System.out.println("PARTICPANTS A ENVIAR EL MENSAJE DE CHAT: " + participantDestinatarios.get(0));
		for(String participante: participantDestinatarios) {
			
			endpoints.forEach(endpoint -> {
				synchronized (endpoint) {
					System.out.println(endpoint.session.getId() + " = " + MyWebScket.users.get(participante));
					if (endpoint.session.getId().equals(MyWebScket.users.get(participante))) {
						try {
							String json = gson.toJson(mC);
							json = "{\"contentType\":\"" + contentType + "\", \"content\":" + json + "}";
							System.out.println("NUEVO MENSAJE ENVIADO A MIGOS CONECTADOS: " + json);

							endpoint.session.getBasicRemote().sendText(json);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			});
			
		}
		
		
		
		
		
	}

}
