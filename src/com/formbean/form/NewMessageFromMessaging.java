package com.formbean.form;

import java.io.Serializable;

public class NewMessageFromMessaging implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String messageBody;
	private String messageThread;
	private String messageAuthor;

	/**
	 * 
	 */
	public NewMessageFromMessaging() {
	}

	/**
	 * @param messageBody
	 */
	public NewMessageFromMessaging(String messageBody) {
		this.messageBody = messageBody;
	}

	/**
	 * @return the messageBody
	 */
	public String getMessageBody() {
		return messageBody;
	}

	/**
	 * @param messageBody the messageBody to set
	 */
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	

	/**
	 * @return the messageThread
	 */
	public String getMessageThread() {
		return messageThread;
	}

	/**
	 * @param messageThread the messageThread to set
	 */
	public void setMessageThread(String messageThread) {
		this.messageThread = messageThread;
	}

	/**
	 * @return the messageAuthor
	 */
	public String getMessageAuthor() {
		return messageAuthor;
	}

	/**
	 * @param messageAuthor the messageAuthor to set
	 */
	public void setMessageAuthor(String messageAuthor) {
		this.messageAuthor = messageAuthor;
	}

	


	
	

}
