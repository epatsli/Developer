package com.capgemini.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.capgemini.exception.IncorrectParameterException;
import com.capgemini.listener.Listener;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ Listener.class })
@Table(name = "BUILDINGS")
public class BuildingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Version
	private Long version;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idBuilding;

	@Column(name = "description", length = 250)
	private String description;

	private Address address;

	@Column(name = "numberFloor", nullable = false)
	private Integer numberFloor;

	@Column(name = "elevator", nullable = false)
	private Boolean elevator;

	@Column(name = "numberFlats", nullable = false)
	private Integer numberFlat;

	@OneToMany(mappedBy = "building")
	private List<FlatEntity> listFlat = new ArrayList<>();

	// @Column
	// @Temporal(TemporalType.DATE)
	// private Date publishingDate;

	public BuildingEntity() {
	}

	public BuildingEntity(BuildingEntityBuilder builder) {

		this.idBuilding = builder.idBuilding;
		this.description = builder.description;
		this.address = builder.address;
		this.numberFloor = builder.numberFloor;
		this.elevator = builder.elevator;
		this.numberFlat = builder.numberFlat;
		this.listFlat = builder.listFlat;
		this.version = builder.version;
	}

	public BuildingEntityBuilder builder() {
		return new BuildingEntityBuilder();
	}

	public Long getIdBuilding() {
		return idBuilding;
	}

	public void setIdBuilding(Long idBuilding) {
		this.idBuilding = idBuilding;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Integer getNumberFloor() {
		return numberFloor;
	}

	public void setNumberFloor(Integer numberFloor) {
		this.numberFloor = numberFloor;
	}

	public Boolean getElevator() {
		return elevator;
	}

	public void setElevator(Boolean elevator) {
		this.elevator = elevator;
	}

	public Integer getNumberFlat() {
		return numberFlat;
	}

	public void setNumberFlat(Integer numberFlat) {
		this.numberFlat = numberFlat;
	}

	public List<FlatEntity> getListFlat() {
		return listFlat;
	}

	public void setListFlat(List<FlatEntity> listFlat) {
		this.listFlat = listFlat;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public static class BuildingEntityBuilder {
		private Long idBuilding;
		private String description;
		private Address address;
		private Integer numberFloor;
		private Boolean elevator;
		private Integer numberFlat;
		private List<FlatEntity> listFlat;
		private Long version;

		public BuildingEntityBuilder() {
		}

		public BuildingEntityBuilder withIdBuilding(Long idBuilding) {
			this.idBuilding = idBuilding;
			return this;
		}

		public BuildingEntityBuilder withDescription(String description) {
			this.description = description;
			return this;
		}

		public BuildingEntityBuilder withAddress(Address address) {
			this.address = address;
			return this;
		}

		public BuildingEntityBuilder withNumberFloor(Integer numberFloor) {
			this.numberFloor = numberFloor;
			return this;
		}

		public BuildingEntityBuilder withElevator(Boolean elevator) {
			this.elevator = elevator;
			return this;
		}

		public BuildingEntityBuilder withNumberFlat(Integer numberFlat) {
			this.numberFlat = numberFlat;
			return this;
		}

		public BuildingEntityBuilder withListFlat(List<FlatEntity> listFlat) {
			this.listFlat = listFlat;
			return this;
		}

		public BuildingEntityBuilder withVersion(Long version) {
			this.version = version;
			return this;
		}

		public BuildingEntity build() {
			checkBeforeBuild();
			return new BuildingEntity(this);
		}

		private void checkBeforeBuild() {

			if (numberFloor == null || elevator == null || numberFlat == null) {
				throw new IncorrectParameterException("This building can't be created.");
			}

		}

	}

}
