package com.socialceep.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CURSES")
@NamedQuery(name="CurseEntity.findAll", query="SELECT c FROM CurseEntity c")
public class CurseEntity {
	
	@Id
	@Column(name = "curses_id")
	private int curseId;
	
	@Column(name = "curse_name")
	private String curseName;
	
	@OneToMany(mappedBy = "cycleCurse", fetch=FetchType.LAZY)
	private List<CycleCurseSessionEntity> cycleCurseSessionEntity;
	

	public int getCurseId() {
		return curseId;
	}

	public void setCurseId(int curseId) {
		this.curseId = curseId;
	}

	public String getCurseName() {
		return curseName;
	}

	public void setCurseName(String curseName) {
		this.curseName = curseName;
	}
	
	public List<CycleCurseSessionEntity> getCycleCurseSessionEntity() {
		return cycleCurseSessionEntity;
	}

	public void setCycleCurseSessionEntity(List<CycleCurseSessionEntity> cycleCurseSessionEntity) {
		this.cycleCurseSessionEntity = cycleCurseSessionEntity;
	}
	
	

}
