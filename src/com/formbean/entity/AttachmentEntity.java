package com.formbean.entity;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.*;


/**
 * The persistent class for the ATTACHMENT database table.
 * 
 */
@Entity
@Table(name="ATTACHMENT")
@NamedQueries({
	@NamedQuery(name="AttachmentEntity.findAll", query="SELECT a FROM AttachmentEntity a"),
	@NamedQuery(name="AttachmentEntity.findAttachmentCodeByPostId", query="SELECT a FROM AttachmentEntity a WHERE a.attachmentPost=:attachmentPost")
})
public class AttachmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="attachment_id")
	private int attachmentId;

	@Column(name="attachment_file_code")
	private String attachmentFileCode;

	@Column(name="attachment_file_name")
	private String attachmentFileName;

	@Column(name="attachment_file_content_type")
	private String attachmentFileContentType;

	@Column(name="attachment_post")
	private String attachmentPost;
	
	@Column(name="attachment_file_path")
	private String attachmentFilePath;
	

	/**
	 * @return the attachmentFilePath
	 */
	public String getAttachmentFilePath() {
		return attachmentFilePath;
	}

	/**
	 * @param attachmentFilePath the attachmentFilePath to set
	 */
	public void setAttachmentFilePath(String attachmentFilePath) {
		this.attachmentFilePath = attachmentFilePath;
	}

	public AttachmentEntity() {
	}

	public int getAttachmentId() {
		return this.attachmentId;
	}

	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getAttachmentFileCode() {
		return this.attachmentFileCode;
	}

	public void setAttachmentFileCode(String attachmentFileCode) {
		this.attachmentFileCode = attachmentFileCode;
	}

	public String getAttachmentFileName() {
		return this.attachmentFileName;
	}

	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

	public String getAttachmentContentType() {
		return this.attachmentFileContentType;
	}

	public void setAttachmentContentType(String attachmentFileContentType) {
		this.attachmentFileContentType = attachmentFileContentType;
	}

	public String getAttachmentPost() {
		return this.attachmentPost;
	}

	public void setAttachmentPost(String attachmentPost) {
		this.attachmentPost = attachmentPost;
	}

}