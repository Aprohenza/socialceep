package com.formbean.session;

import java.util.List;

public class UserConversation {

	private String conversationThread;
	
	private String conversationTitle;

	private List<MessageConversation> messageConversation;
	
	private List<ParticipantConversation> participantsConversation;
	
	private boolean multiple;
	

	public UserConversation() {
		
	} 

	public String getConversationThread() {
		return conversationThread;
	}

	public void setConversationThread(String conversationThread) {
		this.conversationThread = conversationThread;
	}
	
	
	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}
	
	public List<MessageConversation> getMessageConversation() {
		return messageConversation;
	}

	public void setMessageConversation(List<MessageConversation> messageConversation) {
		this.messageConversation = messageConversation;
	}
	
	public List<ParticipantConversation> getParticipantsConversation() {
		return participantsConversation;
	}

	public void setParticipantsConversation(List<ParticipantConversation> participantsConversation) {
		this.participantsConversation = participantsConversation;
	}
	
	public String getConversationTitle() {
		return conversationTitle;
	}

	public void setConversationTitle(String conversationTitle) {
		this.conversationTitle = conversationTitle;
	}

}
