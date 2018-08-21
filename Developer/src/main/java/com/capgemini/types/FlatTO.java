package com.capgemini.types;

import java.util.List;

import com.capgemini.exception.IncorrectParameterException;

public class FlatTO {

	private Long idFlat;
	private Double areaFlat;
	private Integer numberRoom;
	private Integer numberBalconie;
	private Integer floor;
	private AddressTO address;
	private Long flatStatus;
	private Long building;
	private Double price;
	private List<Long> listClientBook;
	private List<Long> listClientBuy;

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
	}

	public FlatTOBuilder builder() {
		return new FlatTOBuilder();
	}

	public static class FlatTOBuilder {
		private Long idFlat;
		private Double areaFlat;
		private Integer numberRoom;
		private Integer numberBalconie;
		private Integer floor;
		private AddressTO address;
		private Long flatStatus;
		private Long building;
		private Double price;
		private List<Long> listClientBook;
		private List<Long> listClientBuy;

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

		public FlatTOBuilder withAddress(AddressTO address) {
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
