package com.socialceep.session;

import com.socialceep.dto.UserProfileDto;

public class ParticipantConversation extends UserProfileDto {
	
	private String participantConversationThread;
	
	
	
	public ParticipantConversation() {
		
	}

	
	
	public ParticipantConversation(String userProfileId, String userProfileName, String userProfileLastName,
			String userProfileRole, String userProfilePhotoProfile, String userProfilePhotoCover,
			String userProfileNationality) {
		super(userProfileId, userProfileName, userProfileLastName, userProfileRole, userProfilePhotoProfile,
				userProfilePhotoCover, userProfileNationality, null, null, null);
		// TODO Auto-generated constructor stub
	}



	public String getParticipantConversationThread() {
		return participantConversationThread;
	}

	public void setParticipantConversationThread(String participantConversationThread) {
		this.participantConversationThread = participantConversationThread;
	}
	
	

}
