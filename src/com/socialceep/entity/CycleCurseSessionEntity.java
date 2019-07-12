package com.socialceep.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CYCLE_CURSE_SESSION")
@NamedQueries({
	@NamedQuery(name = "CycleCurseSessionEntity.findAll", query = "SELECT c FROM CycleCurseSessionEntity c"),
	@NamedQuery(name = "CycleCurseSessionEntity.findClassToUser", query = "SELECT c FROM CycleCurseSessionEntity c WHERE c.cycleId = :cycleId AND c.cycleCurseId = :cycleCurseId AND c.cycleSessionId = :cycleSessionId ")
})

public class CycleCurseSessionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "cycle_id", insertable = false, updatable = false)
	private Long cycleId;
	
	

	@Column(name = "cycle_curse", insertable = false, updatable = false)
	private Integer cycleCurseId;
	
	

	@Column(name = "cycle_session", insertable = false, updatable = false)
	private Integer cycleSessionId;

	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cycle_id")
	private CycleEntity cycle;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cycle_curse")
	private CurseEntity cycleCurse;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cycle_session")
	private SessionEntity cycleSession;
	
	@OneToMany(mappedBy="cycleCurseSessionEntity")
	private List<UserEntity> user;

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Long getCycleId() {
		return cycleId;
	}

	public void setCycleId(Long cycleId) {
		this.cycleId = cycleId;
	}
	
	public int getCycleCurseId() {
		return cycleCurseId;
	}

	public void setCycleCurseId(int cycleCurseId) {
		this.cycleCurseId = cycleCurseId;
	}
	
	public int getCycleSessionId() {
		return cycleSessionId;
	}

	public void setCycleSessionId(int cycleSessionId) {
		this.cycleSessionId = cycleSessionId;
	}

	public CycleEntity getCycle() {
		return cycle;
	}

	public void setCycle(CycleEntity cycle) {
		this.cycle = cycle;
	}

	public CurseEntity getCycleCurse() {
		return cycleCurse;
	}

	public void setCycleCurse(CurseEntity cycleCurse) {
		this.cycleCurse = cycleCurse;
	}

	public SessionEntity getCycleSession() {
		return cycleSession;
	}

	public void setCycleSession(SessionEntity cycleSession) {
		this.cycleSession = cycleSession;
	}
	
	public List<UserEntity> getUser() {
		return user;
	}

	public void setUser(List<UserEntity> user) {
		this.user = user;
	}

}
