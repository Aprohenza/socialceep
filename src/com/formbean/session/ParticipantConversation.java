package com.formbean.session;

public class ParticipantConversation {
	
	private String participantConversationThread;
	
	private String participantId;
	
	private String participantName;
	
	private String participantLastName;
	
	private String participantRole;
	
	private String participantNationality;
	
	private String participantPhotoProfile;
	
	public ParticipantConversation() {
		
	}

	public ParticipantConversation(String participantConversationThread, String participantId, String participantName, String participantLastName,
			String participantRole, String participantNationality, String participantPhotoProfile) {
		super();
		this.setParticipantConversationThread(participantConversationThread);
		this.participantId = participantId;
		this.participantName = participantName;
		this.participantLastName = participantLastName;
		this.participantRole = participantRole;
		this.participantNationality = participantNationality;
		this.participantPhotoProfile = participantPhotoProfile;
	}

	public String getParticipantId() {
		return participantId;
	}

	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}

	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	public String getParticipantLastName() {
		return participantLastName;
	}

	public void setParticipantLastName(String participantLastName) {
		this.participantLastName = participantLastName;
	}

	public String getParticipantRole() {
		return participantRole;
	}

	public void setParticipantRole(String participantRole) {
		this.participantRole = participantRole;
	}

	public String getParticipantNationality() {
		return participantNationality;
	}

	public void setParticipantNationality(String participantNationality) {
		this.participantNationality = participantNationality;
	}

	public String getParticipantPhotoProfile() {
		return participantPhotoProfile;
	}

	public void setParticipantPhotoProfile(String participantPhotoProfile) {
		this.participantPhotoProfile = participantPhotoProfile;
	}

	public String getParticipantConversationThread() {
		return participantConversationThread;
	}

	public void setParticipantConversationThread(String participantConversationThread) {
		this.participantConversationThread = participantConversationThread;
	}
	
	

}
