package com.capgemini.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "STATUS")
public class StatusEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Version
	public Long version;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idStatus;

	@Column(name = "statusName", length = 16, nullable = false)
	private String statusName;

	@OneToMany(mappedBy = "flatStatus")
	private List<FlatEntity> listFlat = new ArrayList<>();

	public StatusEntity() {
	}

	public StatusEntity(String statusName) {
		this.statusName = statusName;
	}

	public StatusEntity(Long idStatu, String statusName) {
		this.idStatus = idStatu;
		this.statusName = statusName;
	}

}
