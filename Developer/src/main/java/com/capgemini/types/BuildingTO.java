package com.capgemini.types;

import java.util.List;

import com.capgemini.exception.IncorrectParameterException;

public class BuildingTO {
	private Long id;
	private String description;
	private AddressMap address;
	private Integer numberFloor;
	private Boolean elevator;
	private Integer numberFlat;
	private List<Long> flats;
	private Long version;

	public BuildingTO() {
	}

	public BuildingTO(BuildingTOBuilder builder) {

		this.id = builder.id;
		this.description = builder.description;
		this.address = builder.address;
		this.numberFloor = builder.numberFloor;
		this.elevator = builder.elevator;
		this.numberFlat = builder.numberFlat;
		this.flats = builder.flats;
		this.version = builder.version;
	}

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

	public AddressMap getAddress() {
		return address;
	}

	public void setAddress(AddressMap address) {
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

	public static class BuildingTOBuilder {
		private Long id;
		private String description;
		private AddressMap address;
		private Integer numberFloor;
		private Boolean elevator;
		private Integer numberFlat;
		private List<Long> flats;
		private Long version;

		public BuildingTOBuilder() {
		}

		public BuildingTOBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		public BuildingTOBuilder withDescription(String description) {
			this.description = description;
			return this;
		}

		public BuildingTOBuilder withAddress(AddressMap address) {
			this.address = address;
			return this;
		}

		public BuildingTOBuilder withNumberFloor(Integer numberFloor) {
			this.numberFloor = numberFloor;
			return this;
		}

		public BuildingTOBuilder withElevator(Boolean elevator) {
			this.elevator = elevator;
			return this;
		}

		public BuildingTOBuilder withNumberFlat(Integer numberFlat) {
			this.numberFlat = numberFlat;
			return this;
		}

		public BuildingTOBuilder withFlats(List<Long> flats) {
			this.flats = flats;
			return this;
		}

		public BuildingTOBuilder withVersion(Long version) {
			this.version = version;
			return this;
		}

		public BuildingTO build() {
			checkBeforeBuild();
			return new BuildingTO(this);
		}

		private void checkBeforeBuild() {

			if (numberFloor == null || elevator == null || numberFlat == null) {
				throw new IncorrectParameterException("This building can't be created.");
			}

		}

	}
}
