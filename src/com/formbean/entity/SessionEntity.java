package com.formbean.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SESSIONS")
@NamedQueries({
	@NamedQuery(name="SessionEntity.findAll", query="SELECT c FROM SessionEntity c")
})

public class SessionEntity {
	
	@Id
	@Column(name = "session_id")
	private int sessionId;
	
	@Column(name = "session_name")
	private String sessionName;
	
	@OneToMany(mappedBy = "cycleSession", fetch=FetchType.LAZY)
	private List<CycleCurseSessionEntity> cycleCurseSessionEntity;

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	
	

}
