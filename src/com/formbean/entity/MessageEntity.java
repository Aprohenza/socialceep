package com.formbean.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MESSAGES database table.
 * 
 */
@Entity
@Table(name="MESSAGES")
@NamedQuery(name="MessageEntity.findAll", query="SELECT m FROM MessageEntity m")
public class MessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="message_id")
	private int messageId;

	/*
	 * @Column(name="message_author") private String messageAuthor;
	 */
	
	 @ManyToOne(fetch = FetchType.LAZY)	 
	 @JoinColumn(name = "message_author")
	 private UserEntity messageAuthor;

	@Column(name="message_body")
	private String messageBody;

	@Column(name="message_date")
	private String messageDate;

	@Column(name="message_status")
	private String messageStatus;

	//bi-directional one-to-one association to ConversationsMessageEntity
	@OneToOne(mappedBy="message", cascade = {CascadeType.ALL})
	private ConversationsMessageEntity conversationsMessage;

	public MessageEntity() {
	}

	public int getMessageId() {
		return this.messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public UserEntity getMessageAuthor() {
		return this.messageAuthor;
	}

	public void setMessageAuthor(UserEntity messageAuthor) {
		this.messageAuthor = messageAuthor;
	}

	public String getMessageBody() {
		return this.messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public String getMessageDate() {
		return this.messageDate;
	}

	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}

	public String getMessageStatus() {
		return this.messageStatus;
	}

	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}

	public ConversationsMessageEntity getConversationsMessage() {
		return this.conversationsMessage;
	}

	public void setConversationsMessage(ConversationsMessageEntity conversationsMessage) {
		this.conversationsMessage = conversationsMessage;
	}

}