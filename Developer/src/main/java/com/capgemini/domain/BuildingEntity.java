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
import com.capgemini.listener.InsertListener;
import com.capgemini.listener.UpdateListener;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ UpdateListener.class, InsertListener.class })
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
	}

	public BuildingEntityBuilder builder() {
		return new BuildingEntityBuilder();
	}

	public static class BuildingEntityBuilder {
		private Long idBuilding;
		private String description;
		private AddressEntity address;
		private Integer numberFloor;
		private Boolean elevator;
		private Integer numberFlat;
		private List<FlatEntity> listFlat;

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

		public BuildingEntityBuilder withAddress(AddressEntity address) {
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
