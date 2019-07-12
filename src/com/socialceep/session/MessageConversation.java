package com.socialceep.session;

public class MessageConversation {
	
	private String messageConversationThread;
	
	private int messageId;

	private String messageAuthor;

	private String messageAuthorId;
	
	private Long messageAuthorPhotoProfile;

	private String messageDate;

	private String messageBody;
	
	
	
	public MessageConversation() {
		
	}

	public MessageConversation(String messageConversationThread, int messageId, String messageAuthor,
			String messageAuthorId, String messageDate, String messageBody) {
		super();
		this.messageConversationThread = messageConversationThread;
		this.messageId = messageId;
		this.messageAuthor = messageAuthor;
		this.messageAuthorId = messageAuthorId;
		this.messageDate = messageDate;
		this.messageBody = messageBody;
	}

	public String getMessageConversationThread() {
		return messageConversationThread;
	}

	public void setMessageConversationThread(String messageConversationThread) {
		this.messageConversationThread = messageConversationThread;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getMessageAuthor() {
		return messageAuthor;
	}

	public void setMessageAuthor(String messageAuthor) {
		this.messageAuthor = messageAuthor;
	}

	public String getMessageAuthorId() {
		return messageAuthorId;
	}

	public void setMessageAuthorId(String messageAuthorId) {
		this.messageAuthorId = messageAuthorId;
	}

	public String getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	
	public Long getMessageAuthorPhotoProfile() {
		return messageAuthorPhotoProfile;
		
	}
	
	public void setMessageAuthorPhotoProfile(Long userPhotoProfile) {
		this.messageAuthorPhotoProfile = userPhotoProfile;
		
	}
	
	
	
	
	

}
