package com.capgemini.types;

import java.util.List;

import com.capgemini.exception.IncorrectParameterException;

/**
 * FlatService
 *
 */
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

	/**
	 * No-argument constructor
	 */
	public FlatTO() {
	}

	/**
	 * This method create new flat object with builder.
	 * 
	 * @param builder
	 *            object FlatTOBuilder
	 */
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

	/**
	 * This method build new flat object.
	 * 
	 * @return new flat object
	 */
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

	/**
	 * Flat builder class
	 *
	 */
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

		/**
		 * No-argument constructor
		 */
		public FlatTOBuilder() {
		}

		/**
		 * This method create flat with id.
		 * 
		 * @param id
		 *            index flat
		 * @return new flat object with id
		 */
		public FlatTOBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		/**
		 * This method create flat with flat area.
		 * 
		 * @param areaFlat
		 *            flat area
		 * @return new flat object with area
		 */
		public FlatTOBuilder withAreaFlat(Double areaFlat) {
			this.areaFlat = areaFlat;
			return this;
		}

		/**
		 * This method create flat with number rooms.
		 * 
		 * @param numberRoom
		 *            number rooms in flat
		 * @return new flat object with number rooms
		 */
		public FlatTOBuilder withNumberRoom(Integer numberRoom) {
			this.numberRoom = numberRoom;
			return this;
		}

		/**
		 * This method return flat with number balconies.
		 * 
		 * @param numberBalconie
		 *            number balconies in flat
		 * @return new flat object with number balconies
		 */
		public FlatTOBuilder withNumberBalconie(Integer numberBalconie) {
			this.numberBalconie = numberBalconie;
			return this;
		}

		/**
		 * This method return flat with number floor where is flat.
		 * 
		 * @param floor
		 *            number flat where is flat
		 * @return new flat object with number floor
		 */
		public FlatTOBuilder withFloor(Integer floor) {
			this.floor = floor;
			return this;
		}

		/**
		 * This method return flat with address.
		 * 
		 * @param address
		 *            flats address
		 * @return new flat object with address
		 */
		public FlatTOBuilder withAddress(AddressMap address) {
			this.address = address;
			return this;
		}

		/**
		 * This method return flat status.
		 * 
		 * @param flatStatus
		 *            flat status
		 * @return new flat object with status
		 */
		public FlatTOBuilder withFlatStatus(Long flatStatus) {
			this.flatStatus = flatStatus;
			return this;
		}

		/**
		 * This method return flat with information about building where is
		 * flat.
		 * 
		 * @param building
		 *            building where is flat
		 * @return new flat object with information about building
		 */
		public FlatTOBuilder withBuilding(Long building) {
			this.building = building;
			return this;
		}

		/**
		 * This method return flat with price.
		 * 
		 * @param price
		 *            flat price
		 * @return new flat object with price
		 */
		public FlatTOBuilder withPrice(Double price) {
			this.price = price;
			return this;
		}

		/**
		 * This method return flat with list client who book this flat.
		 * 
		 * @param clientBook
		 *            list client who book this flat
		 * @return new flat object with list client who book flats
		 */
		public FlatTOBuilder withBookByClient(List<Long> bookByClient) {
			this.bookByClient = bookByClient;
			return this;
		}

		/**
		 * This method return flat with list client who buy this flat.
		 * 
		 * @param clientBuy
		 *            list client who buy this flat
		 * @return new flat object with list client who buy flats
		 */
		public FlatTOBuilder withBuyByClient(List<Long> buyByClient) {
			this.buyByClient = buyByClient;
			return this;
		}

		/**
		 * This method return client who is owner this flat.
		 * 
		 * @param owner
		 *            client who is owner this flat
		 * @return new flat object with client who is owner this flat
		 */
		public FlatTOBuilder withOwner(Long owner) {
			this.owner = owner;
			return this;
		}

		/**
		 * This method return client with version.
		 * 
		 * @param version
		 *            flat version
		 * @return new flat object with version
		 */
		public FlatTOBuilder withVersion(Long version) {
			this.version = version;
			return this;
		}

		/**
		 * This method build new flat object.
		 * 
		 * @return create new flat object
		 */
		public FlatTO build() {
			checkBeforeBuild();
			return new FlatTO(this);
		}

		private void checkBeforeBuild() {

			if (areaFlat == null || flatStatus == null) {
				throw new IncorrectParameterException("This flat can't be created.");
			}

		}

	}
}