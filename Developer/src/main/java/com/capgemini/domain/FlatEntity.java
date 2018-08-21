package com.capgemini.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.capgemini.exception.IncorrectParameterException;

@Entity
@Table(name = "FLATS")
public class FlatEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Version
	public Long version;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idFlat;

	@Column(name = "areaFlat", nullable = false)
	private Double areaFlat;

	@Column(name = "numberRooms")
	private Integer numberRoom;

	@Column(name = "numberBalconies")
	private Integer numberBalconie;

	@Column(name = "floor")
	private Integer floor;

	@Embedded
	private AddressEntity address;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idStatus", nullable = false)
	private StatusEntity flatStatus;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Building")
	private BuildingEntity building;

	@Column(name = "price")
	private Double price;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "CLIENT_BOOK_FLAT", joinColumns = @JoinColumn(name = "idClient") , inverseJoinColumns = @JoinColumn(name = "idFlat") )
	private List<ClientEntity> listClientBook = new ArrayList<>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "CLIENT_BUY_FLAT", joinColumns = @JoinColumn(name = "idClient") , inverseJoinColumns = @JoinColumn(name = "idFlat") )
	private List<ClientEntity> listClientBuy = new ArrayList<>();

	public FlatEntity() {
	}

	public FlatEntity(FlatEntityBuilder builder) {
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

	public FlatEntityBuilder builder() {
		return new FlatEntityBuilder();
	}

	public static class FlatEntityBuilder {
		private Long idFlat;
		private Double areaFlat;
		private Integer numberRoom;
		private Integer numberBalconie;
		private Integer floor;
		private AddressEntity address;
		private StatusEntity flatStatus;
		private BuildingEntity building;
		private Double price;
		private List<ClientEntity> listClientBook;
		private List<ClientEntity> listClientBuy;

		public FlatEntityBuilder() {
		}

		public FlatEntityBuilder withIdFlat(Long idFlat) {
			this.idFlat = idFlat;
			return this;
		}

		public FlatEntityBuilder withAreaFlat(Double areaFlat) {
			this.areaFlat = areaFlat;
			return this;
		}

		public FlatEntityBuilder withNumberRoom(Integer numberRoom) {
			this.numberRoom = numberRoom;
			return this;
		}

		public FlatEntityBuilder withNumberBalconie(Integer numberBalconie) {
			this.numberBalconie = numberBalconie;
			return this;
		}

		public FlatEntityBuilder withFloor(Integer floor) {
			this.floor = floor;
			return this;
		}

		public FlatEntityBuilder withAddress(AddressEntity address) {
			this.address = address;
			return this;
		}

		public FlatEntityBuilder withFlatStatus(StatusEntity flatStatus) {
			this.flatStatus = flatStatus;
			return this;
		}

		public FlatEntityBuilder withBuilding(BuildingEntity building) {
			this.building = building;
			return this;
		}

		public FlatEntityBuilder withPrice(Double price) {
			this.price = price;
			return this;
		}

		public FlatEntityBuilder withListClientBook(List<ClientEntity> listClientBook) {
			this.listClientBook = listClientBook;
			return this;
		}

		public FlatEntityBuilder withListClientBuy(List<ClientEntity> listClientBuy) {
			this.listClientBuy = listClientBuy;
			return this;
		}

		public FlatEntity build() {
			checkBeforeBuild();
			return new FlatEntity(this);
		}

		private void checkBeforeBuild() {

			if (areaFlat == null || flatStatus == null) {
				throw new IncorrectParameterException("This flat can't be created.");
			}

		}

	}

}
