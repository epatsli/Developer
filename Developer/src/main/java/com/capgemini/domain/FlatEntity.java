package com.capgemini.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.capgemini.exception.IncorrectParameterException;
import com.capgemini.listener.Listener;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ Listener.class })
@Table(name = "FLATS")
public class FlatEntity extends AbstractListenerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Version
	private Long version;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "areaFlat", nullable = false)
	private Double areaFlat;

	@Column(name = "numberRooms")
	private Integer numberRoom;

	@Column(name = "numberBalconies")
	private Integer numberBalconie;

	@Column(name = "floor")
	private Integer floor;

	@Column(name = "numberFlat")
	private Integer numberFlat;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Status.id", nullable = false)
	private StatusEntity flatStatus;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Building")
	private BuildingEntity building;

	@Column(name = "price")
	private Double price;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "CLIENT_BOOK_FLAT", joinColumns = @JoinColumn(name = "idFlat") , inverseJoinColumns = @JoinColumn(name = "idClient") )
	private List<ClientEntity> clientBook = new ArrayList<>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "CLIENT_BUY_FLAT", joinColumns = @JoinColumn(name = "idFlat") , inverseJoinColumns = @JoinColumn(name = "idClient") )
	private List<ClientEntity> clientBuy = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private ClientEntity owner;

	public FlatEntity() {
	}

	public FlatEntity(FlatEntityBuilder builder) {
		this.id = builder.id;
		this.areaFlat = builder.areaFlat;
		this.numberRoom = builder.numberRoom;
		this.numberBalconie = builder.numberBalconie;
		this.floor = builder.floor;
		this.numberFlat = builder.numberFlat;
		this.flatStatus = builder.flatStatus;
		this.building = builder.building;
		this.price = builder.price;
		this.clientBook = builder.clientBook;
		this.clientBuy = builder.clientBuy;
		this.owner = builder.owner;
		this.version = builder.version;
	}

	public FlatEntityBuilder builder() {
		return new FlatEntityBuilder();
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
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

	public Integer getNumberFlat() {
		return numberFlat;
	}

	public void setNumberFlat(Integer numberFlat) {
		this.numberFlat = numberFlat;
	}

	public StatusEntity getFlatStatus() {
		return flatStatus;
	}

	public void setFlatStatus(StatusEntity flatStatus) {
		this.flatStatus = flatStatus;
	}

	public BuildingEntity getBuilding() {
		return building;
	}

	public void setBuilding(BuildingEntity building) {
		this.building = building;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<ClientEntity> getClientBook() {
		return clientBook;
	}

	public void setClientBook(List<ClientEntity> clientBook) {
		this.clientBook = clientBook;
	}

	public List<ClientEntity> getClientBuy() {
		return clientBuy;
	}

	public void setClientBuy(List<ClientEntity> clientBuy) {
		this.clientBuy = clientBuy;
	}

	public ClientEntity getOwner() {
		return owner;
	}

	public void setOwner(ClientEntity owner) {
		this.owner = owner;
	}

	public static class FlatEntityBuilder {
		private Long id;
		private Double areaFlat;
		private Integer numberRoom;
		private Integer numberBalconie;
		private Integer floor;
		private Integer numberFlat;
		private StatusEntity flatStatus;
		private BuildingEntity building;
		private Double price;
		private List<ClientEntity> clientBook;
		private List<ClientEntity> clientBuy;
		private ClientEntity owner;
		private Long version;

		public FlatEntityBuilder() {
		}

		public FlatEntityBuilder withId(Long id) {
			this.id = id;
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

		public FlatEntityBuilder withNumberFlat(Integer numberFlat) {
			this.numberFlat = numberFlat;
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

		public FlatEntityBuilder withClientBook(List<ClientEntity> clientBook) {
			this.clientBook = clientBook;
			return this;
		}

		public FlatEntityBuilder withClientBuy(List<ClientEntity> clientBuy) {
			this.clientBuy = clientBuy;
			return this;
		}

		public FlatEntityBuilder withOwner(ClientEntity owner) {
			this.owner = owner;
			return this;
		}

		public FlatEntityBuilder withVersion(Long version) {
			this.version = version;
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
