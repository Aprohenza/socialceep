package com.socialceep.dto;

public class AttachmentDto {
	
	private String attachmentCode;
	
	private String attachmentName;	

	private String attachmentPath;
	
	private String attachmentContentType;

	/**
	 * 
	 */
	public AttachmentDto() {
	}

	/**
	 * @param attachmentCode
	 * @param attachmentPath
	 * @param attachmentContentType
	 */
	public AttachmentDto(String attachmentCode, String attachmentName, String attachmentPath, String attachmentContentType) {
		this.attachmentName = attachmentName;
		this.attachmentCode = attachmentCode;
		this.attachmentPath = attachmentPath;
		this.attachmentContentType = attachmentContentType;
	}

	/**
	 * @return the attachmentCode
	 */
	public String getAttachmentCode() {
		return attachmentCode;
	}

	/**
	 * @param attachmentCode the attachmentCode to set
	 */
	public void setAttachmentCode(String attachmentCode) {
		this.attachmentCode = attachmentCode;
	}

	/**
	 * @return the attachmentPath
	 */
	public String getAttachmentPath() {
		return attachmentPath;
	}
	
	/**
	 * @return the attachmentName
	 */
	public String getAttachmentName() {
		return attachmentName;
	}

	/**
	 * @param attachmentName the attachmentName to set
	 */
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	/**
	 * @param attachmentPath the attachmentPath to set
	 */
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	/**
	 * @return the attachmentContentType
	 */
	public String getAttachmentContentType() {
		return attachmentContentType;
	}

	/**
	 * @param attachmentContentType the attachmentContentType to set
	 */
	public void setAttachmentContentType(String attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}
	
	
}
