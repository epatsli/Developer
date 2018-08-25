package com.capgemini.types;

import java.util.List;

import com.capgemini.exception.IncorrectParameterException;

/**
 * Building TO
 *
 */
public class BuildingTO {
	private Long id;
	private String description;
	private String location;
	private Integer numberFloor;
	private Boolean elevator;
	private Integer numberFlat;
	private List<Long> flats;
	private Long version;

	/**
	 * No-argument constructor
	 */
	public BuildingTO() {
	}

	/**
	 * Constructor creating building.
	 * 
	 * @param builder
	 *            object BuildingTOBuilder
	 */
	public BuildingTO(BuildingTOBuilder builder) {

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
	public BuildingTOBuilder builder() {
		return new BuildingTOBuilder();
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

	public List<Long> getFlats() {
		return flats;
	}

	public void setFlats(List<Long> flats) {
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
	public static class BuildingTOBuilder {
		private Long id;
		private String description;
		private String location;
		private Integer numberFloor;
		private Boolean elevator;
		private Integer numberFlat;
		private List<Long> flats;
		private Long version;

		/**
		 * No-argument constructor
		 */
		public BuildingTOBuilder() {
		}

		/**
		 * This method create building with id.
		 * 
		 * @param id
		 *            index building
		 * @return new building object
		 */
		public BuildingTOBuilder withId(Long id) {
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
		public BuildingTOBuilder withDescription(String description) {
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
		public BuildingTOBuilder withLocation(String location) {
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
		public BuildingTOBuilder withNumberFloor(Integer numberFloor) {
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
		public BuildingTOBuilder withElevator(Boolean elevator) {
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
		public BuildingTOBuilder withNumberFlat(Integer numberFlat) {
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
		public BuildingTOBuilder withFlats(List<Long> flats) {
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
		public BuildingTOBuilder withVersion(Long version) {
			this.version = version;
			return this;
		}

		/**
		 * This method build new building object.
		 */
		public BuildingTO build() {
			checkBeforeBuild();
			return new BuildingTO(this);
		}

		private void checkBeforeBuild() {

			if (numberFloor == null || elevator == null || numberFlat == null || location == null) {
				throw new IncorrectParameterException("This building can't be created.");
			}

		}

	}
}
