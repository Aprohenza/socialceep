package com.socialceep.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CONVERSATIONS_MESSAGES database table.
 * 
 */
@Entity
@Table(name="CONVERSATIONS_MESSAGES")
@NamedQuery(name="ConversationsMessageEntity.findAll", query="SELECT c FROM ConversationsMessageEntity c")
public class ConversationsMessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="coversation_message_id")
	private int coversationMessageId;

	//bi-directional many-to-one association to ConversationEntity
	@ManyToOne
	@JoinColumn(name="conversation_message_thread")
	private ConversationEntity conversation;

	//bi-directional one-to-one association to MessageEntity
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="conversation_message_messageid")
	private MessageEntity message;

	public ConversationsMessageEntity() {
	}

	public int getCoversationMessageId() {
		return this.coversationMessageId;
	}

	public void setCoversationMessageId(int coversationMessageId) {
		this.coversationMessageId = coversationMessageId;
	}

	public ConversationEntity getConversation() {
		return this.conversation;
	}

	public void setConversation(ConversationEntity conversation) {
		this.conversation = conversation;
	}

	public MessageEntity getMessage() {
		return this.message;
	}

	public void setMessage(MessageEntity message) {
		this.message = message;
	}

}