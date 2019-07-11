package com.formbean.session;

import java.util.List;

public class UserConversation {

	private String conversationThread;
	
	private List<MessageConversation> messageConversation;
	
	private List<ParticipantConversation> participantsConversation;

	

	/*
	 * private String nameOtherUser;
	 * 
	 * private String previewLastMessageOtherUser;
	 * 
	 * private String dateLastMessageOtherUser;
	 */
	
	private boolean multiple;
	
	

	public UserConversation() {
		
	}

	/*
	 * public UserConversation(String conversationThread, String nameOtherUser,
	 * String previewLastMessageOtherUser, String dateLastMessageOtherUser) {
	 * super(); this.conversationThread = conversationThread; //this.nameOtherUser =
	 * nameOtherUser; //this.previewLastMessageOtherUser =
	 * previewLastMessageOtherUser; //this.dateLastMessageOtherUser =
	 * dateLastMessageOtherUser; }
	 */

	public String getConversationThread() {
		return conversationThread;
	}

	public void setConversationThread(String conversationThread) {
		this.conversationThread = conversationThread;
	}

	/*
	 * public String getNameOtherUser() { return nameOtherUser; }
	 * 
	 * public void setNameOtherUser(String nameOtherUser) { this.nameOtherUser =
	 * nameOtherUser; }
	 * 
	 * public String getPreviewLastMessageOtherUser() { return
	 * previewLastMessageOtherUser; }
	 * 
	 * public void setPreviewLastMessageOtherUser(String
	 * previewLastMessageOtherUser) { this.previewLastMessageOtherUser =
	 * previewLastMessageOtherUser; }
	 * 
	 * public String getDateLastMessageOtherUser() { return
	 * dateLastMessageOtherUser; }
	 * 
	 * public void setDateLastMessageOtherUser(String dateLastMessageOtherUser) {
	 * this.dateLastMessageOtherUser = dateLastMessageOtherUser; }
	 */
	
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
	
	

}
