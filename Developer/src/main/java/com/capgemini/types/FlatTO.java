package com.capgemini.types;

import java.util.List;

import com.capgemini.exception.IncorrectParameterException;

public class FlatTO {

	private Long id;
	private Double areaFlat;
	private Integer numberRoom;
	private Integer numberBalconie;
	private Integer floor;
	private AddressMap address;
	private Long flatStatus;
	private Long building;
	private Double price;
	private List<Long> bookByClient;
	private List<Long> buyByClient;
	private Long version;
	private Long owner;

	public FlatTO() {
	}

	public FlatTO(FlatTOBuilder builder) {
		this.id = builder.id;
		this.areaFlat = builder.areaFlat;
		this.numberRoom = builder.numberRoom;
		this.numberBalconie = builder.numberBalconie;
		this.floor = builder.floor;
		this.address = builder.address;
		this.flatStatus = builder.flatStatus;
		this.building = builder.building;
		this.price = builder.price;
		this.bookByClient = builder.bookByClient;
		this.buyByClient = builder.buyByClient;
		this.version = builder.version;
		this.owner = builder.owner;
	}

	public FlatTOBuilder builder() {
		return new FlatTOBuilder();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Long> getBookByClient() {
		return bookByClient;
	}

	public void setBookByClient(List<Long> bookByClient) {
		this.bookByClient = bookByClient;
	}

	public List<Long> getBuyByClient() {
		return buyByClient;
	}

	public void setBuyByClient(List<Long> buyByClient) {
		this.buyByClient = buyByClient;
	}

	public Long getOwner() {
		return owner;
	}

	public void setOwner(Long owner) {
		this.owner = owner;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public static class FlatTOBuilder {
		private Long id;
		private Double areaFlat;
		private Integer numberRoom;
		private Integer numberBalconie;
		private Integer floor;
		private AddressMap address;
		private Long flatStatus;
		private Long building;
		private Double price;
		private List<Long> bookByClient;
		private List<Long> buyByClient;
		private Long owner;
		private Long version;

		public FlatTOBuilder() {
		}

		public FlatTOBuilder withId(Long id) {
			this.id = id;
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

		public FlatTOBuilder withBookByClient(List<Long> bookByClient) {
			this.bookByClient = bookByClient;
			return this;
		}

		public FlatTOBuilder withBuyByClient(List<Long> buyByClient) {
			this.buyByClient = buyByClient;
			return this;
		}

		public FlatTOBuilder withOwner(Long owner) {
			this.owner = owner;
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
