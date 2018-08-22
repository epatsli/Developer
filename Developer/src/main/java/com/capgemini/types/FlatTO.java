package com.capgemini.types;

import java.util.List;

import com.capgemini.exception.IncorrectParameterException;

public class FlatTO {

	private Long idFlat;
	private Double areaFlat;
	private Integer numberRoom;
	private Integer numberBalconie;
	private Integer floor;
	private AddressMap address;
	private Long flatStatus;
	private Long building;
	private Double price;
	private List<Long> listClientBook;
	private List<Long> listClientBuy;
	private Long version;

	public FlatTO() {
	}

	public FlatTO(FlatTOBuilder builder) {
		this.idFlat = builder.idFlat;
		this.areaFlat = builder.areaFlat;
		this.numberRoom = builder.numberRoom;
		this.numberBalconie = builder.numberBalconie;
		this.floor = builder.floor;
		this.address = builder.address;
		this.flatStatus = builder.flatStatus;
		this.building = builder.building;
		this.price = builder.price;
		this.listClientBook = builder.listClientBook;
		this.listClientBuy = builder.listClientBuy;
		this.version = builder.version;
	}

	public FlatTOBuilder builder() {
		return new FlatTOBuilder();
	}

	public Long getIdFlat() {
		return idFlat;
	}

	public void setIdFlat(Long idFlat) {
		this.idFlat = idFlat;
	}

	public Double getAreaFlat() {
		return areaFlat;
	}

	public void setAreaFlat(Double areaFlat) {
		this.areaFlat = areaFlat;
	}

	public Integer getNumberRoom() {
		return numberRoom;
	}

	public void setNumberRoom(Integer numberRoom) {
		this.numberRoom = numberRoom;
	}

	public Integer getNumberBalconie() {
		return numberBalconie;
	}

	public void setNumberBalconie(Integer numberBalconie) {
		this.numberBalconie = numberBalconie;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public AddressMap getAddress() {
		return address;
	}

	public void setAddress(AddressMap address) {
		this.address = address;
	}

	public Long getFlatStatus() {
		return flatStatus;
	}

	public void setFlatStatus(Long flatStatus) {
		this.flatStatus = flatStatus;
	}

	public Long getBuilding() {
		return building;
	}

	public void setBuilding(Long building) {
		this.building = building;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<Long> getListClientBook() {
		return listClientBook;
	}

	public void setListClientBook(List<Long> listClientBook) {
		this.listClientBook = listClientBook;
	}

	public List<Long> getListClientBuy() {
		return listClientBuy;
	}

	public void setListClientBuy(List<Long> listClientBuy) {
		this.listClientBuy = listClientBuy;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public static class FlatTOBuilder {
		private Long idFlat;
		private Double areaFlat;
		private Integer numberRoom;
		private Integer numberBalconie;
		private Integer floor;
		private AddressMap address;
		private Long flatStatus;
		private Long building;
		private Double price;
		private List<Long> listClientBook;
		private List<Long> listClientBuy;
		private Long version;

		public FlatTOBuilder() {
		}

		public FlatTOBuilder withIdFlat(Long idFlat) {
			this.idFlat = idFlat;
			return this;
		}

		public FlatTOBuilder withAreaFlat(Double areaFlat) {
			this.areaFlat = areaFlat;
			return this;
		}

		public FlatTOBuilder withNumberRoom(Integer numberRoom) {
			this.numberRoom = numberRoom;
			return this;
		}

		public FlatTOBuilder withNumberBalconie(Integer numberBalconie) {
			this.numberBalconie = numberBalconie;
			return this;
		}

		public FlatTOBuilder withFloor(Integer floor) {
			this.floor = floor;
			return this;
		}

		public FlatTOBuilder withAddress(AddressMap address) {
			this.address = address;
			return this;
		}

		public FlatTOBuilder withFlatStatus(Long flatStatus) {
			this.flatStatus = flatStatus;
			return this;
		}

		public FlatTOBuilder withBuilding(Long building) {
			this.building = building;
			return this;
		}

		public FlatTOBuilder withPrice(Double price) {
			this.price = price;
			return this;
		}

		public FlatTOBuilder withListClientBook(List<Long> listClientBook) {
			this.listClientBook = listClientBook;
			return this;
		}

		public FlatTOBuilder withListClientBuy(List<Long> listClientBuy) {
			this.listClientBuy = listClientBuy;
			return this;
		}

		public FlatTOBuilder withVersion(Long version) {
			this.version = version;
			return this;
		}

		public FlatTO build() {
			checkBeforeBuild();
			return new FlatTO(this);
		}

		private void checkBeforeBuild() {

			if (areaFlat == null || numberRoom == null) {
				throw new IncorrectParameterException("This flat can't be created.");
			}

		}

	}
}
