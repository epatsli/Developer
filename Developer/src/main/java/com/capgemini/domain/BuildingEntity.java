package com.capgemini.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

/**
 * Building entity
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ Listener.class })
@Table(name = "BUILDINGS")
public class BuildingEntity extends AbstractListenerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Version
	private Long version;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "description", length = 250)
	private String description;

	@Column(name = "location", length = 40)
	private String location;

	@Column(name = "numberFloor")
	private Integer numberFloor;

	@Column(name = "elevator")
	private Boolean elevator;

	@Column(name = "numberFlats")
	private Integer numberFlat;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "building", orphanRemoval = true)
	private List<FlatEntity> flats = new ArrayList<>();

	/**
	 * No-argument constructor
	 */
	public BuildingEntity() {
	}

	/**
	 * Constructor creating building.
	 * 
	 * @param builder
	 *            object BuildingEntityBuilder
	 */
	public BuildingEntity(BuildingEntityBuilder builder) {

		this.id = builder.id;
		this.description = builder.description;
		this.location = builder.location;
		this.numberFloor = builder.numberFloor;
		this.elevator = builder.elevator;
		this.numberFlat = builder.numberFlat;
		this.flats = builder.flats;
		this.version = builder.version;
	}

	/**
	 * This method create new object builder.
	 */
	public BuildingEntityBuilder builder() {
		return new BuildingEntityBuilder();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public List<FlatEntity> getFlats() {
		return flats;
	}

	public void setFlats(List<FlatEntity> flats) {
		this.flats = flats;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	/**
	 * Class to construct building.
	 *
	 */
	public static class BuildingEntityBuilder {
		private Long id;
		private String description;
		private String location;
		private Integer numberFloor;
		private Boolean elevator;
		private Integer numberFlat;
		private List<FlatEntity> flats;
		private Long version;

		/**
		 * No-argument constructor
		 */
		public BuildingEntityBuilder() {
		}

		/**
		 * This method create building with id.
		 * 
		 * @param id
		 *            index building
		 * @return new building object
		 */
		public BuildingEntityBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		/**
		 * This building create building with description.
		 * 
		 * @param description
		 *            description building
		 * @return new building object
		 */
		public BuildingEntityBuilder withDescription(String description) {
			this.description = description;
			return this;
		}

		/**
		 * This method create building with location.
		 * 
		 * @param location
		 *            building location
		 * @return new building object
		 */
		public BuildingEntityBuilder withLocation(String location) {
			this.location = location;
			return this;
		}

		/**
		 * This method create building with number floor.
		 * 
		 * @param numberFloor
		 *            building floor
		 * @return new building object
		 */
		public BuildingEntityBuilder withNumberFloor(Integer numberFloor) {
			this.numberFloor = numberFloor;
			return this;
		}

		/**
		 * This method create building with elevator.
		 * 
		 * @param elevator
		 *            is elevator in building
		 * @return new object with information about elevator in building
		 */
		public BuildingEntityBuilder withElevator(Boolean elevator) {
			this.elevator = elevator;
			return this;
		}

		/**
		 * This method create building with number flat.
		 * 
		 * @param numberFlat
		 *            number flat in building
		 * @return new building object
		 */
		public BuildingEntityBuilder withNumberFlat(Integer numberFlat) {
			this.numberFlat = numberFlat;
			return this;
		}

		/**
		 * This method return object with list flats.
		 * 
		 * @param flats
		 *            list flats in building
		 * @return new building object
		 */
		public BuildingEntityBuilder withFlats(List<FlatEntity> flats) {
			this.flats = flats;
			return this;
		}

		/**
		 * This method return version building object.
		 * 
		 * @param version
		 *            building version
		 * @return new building object
		 */
		public BuildingEntityBuilder withVersion(Long version) {
			this.version = version;
			return this;
		}

		/**
		 * This method build new building object.
		 */
		public BuildingEntity build() {
			checkBeforeBuild();
			return new BuildingEntity(this);
		}

		private void checkBeforeBuild() {

			if (numberFloor == null || elevator == null || numberFlat == null || location == null) {
				throw new IncorrectParameterException("This building can't be created.");
			}

		}

	}

}
