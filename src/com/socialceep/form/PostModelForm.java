package com.socialceep.form;

import java.io.File;

public class PostModelForm {
	
	private String postBody;
	private File attachment;

	/**
	 * @return the attachment
	 */
	public File getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	/**
	 * 
	 */
	public PostModelForm() {
	}

	/**
	 * @param postBody
	 */
	public PostModelForm(String postBody) {
		this.postBody = postBody;
	}

	/**
	 * @return the postBody
	 */
	public String getPostBody() {
		return postBody;
	}

	/**
	 * @param postBody the postBody to set
	 */
	public void setPostBody(String postBody) {
		this.postBody = postBody;
	}
	
	
}
