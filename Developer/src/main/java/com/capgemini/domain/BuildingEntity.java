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
@Table(name = "BUILDINGS")
public class BuildingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Version
	public Long version;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idBuilding;

	@Column(name = "description", length = 250)
	private String description;

	private AddressEntity address;

	@Column(name = "numberFloor", nullable = false)
	private Integer numberFloor;

	@Column(name = "elevator", nullable = false)
	private Boolean elevator;

	@Column(name = "numberFlats", nullable = false)
	private Integer numberFlat;

	@OneToMany(mappedBy = "building")
	private List<FlatEntity> listFlat = new ArrayList<>();
}
