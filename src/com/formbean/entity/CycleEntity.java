package com.formbean.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CYCLE")
@NamedQuery(name="CycleEntity.findAll", query="SELECT c FROM CycleEntity c")
public class CycleEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cycle_id")
	private Long cycleId;
	
	@Column(name="cycle_name")
	private String cycleName;
	
	@OneToMany(mappedBy = "cycle", fetch=FetchType.LAZY)
	private List<CycleCurseSessionEntity> cycleCurseSessionEntity;
	

	public Long getCycleId() {
		return cycleId;
	}

	public void setCycleId(Long cycleId) {
		this.cycleId = cycleId;
	}

	public String getCycleName() {
		return cycleName;
	}

	public void setCycleName(String cycleName) {
		this.cycleName = cycleName;
	}
	
	public List<CycleCurseSessionEntity> getCycleCurseSessionEntity() {
		return cycleCurseSessionEntity;
	}

	public void setCycleCurseSessionEntity(List<CycleCurseSessionEntity> cycleCurseSessionEntity) {
		this.cycleCurseSessionEntity = cycleCurseSessionEntity;
	}
	
	

}
