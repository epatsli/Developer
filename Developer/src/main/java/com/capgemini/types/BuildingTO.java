package com.capgemini.types;

import java.util.List;

import com.capgemini.domain.AddressEntity;
import com.capgemini.exception.IncorrectParameterException;

public class BuildingTO {
	private Long idBuilding;
	private String description;
	private AddressEntity address;
	private Integer numberFloor;
	private Boolean elevator;
	private Integer numberFlat;
	private List<Long> listFlat;

	public BuildingTO() {
	}

	public BuildingTO(BuildingTOBuilder builder) {

		this.idBuilding = builder.idBuilding;
		this.description = builder.description;
		this.address = builder.address;
		this.numberFloor = builder.numberFloor;
		this.elevator = builder.elevator;
		this.numberFlat = builder.numberFlat;
		this.listFlat = builder.listFlat;
	}

	public BuildingTOBuilder builder() {
		return new BuildingTOBuilder();
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

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
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

	public List<Long> getListFlat() {
		return listFlat;
	}

	public void setListFlat(List<Long> listFlat) {
		this.listFlat = listFlat;
	}

	public static class BuildingTOBuilder {
		private Long idBuilding;
		private String description;
		private AddressEntity address;
		private Integer numberFloor;
		private Boolean elevator;
		private Integer numberFlat;
		private List<Long> listFlat;

		public BuildingTOBuilder() {
		}

		public BuildingTOBuilder withIdBuilding(Long idBuilding) {
			this.idBuilding = idBuilding;
			return this;
		}

		public BuildingTOBuilder withDescription(String description) {
			this.description = description;
			return this;
		}

		public BuildingTOBuilder withAddress(AddressEntity address) {
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

		public BuildingTOBuilder withListFlat(List<Long> listFlat) {
			this.listFlat = listFlat;
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
