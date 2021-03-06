package com.capgemini.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
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

/**
 * FlatEntity
 *
 */
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

	@Embedded
	private Address address;

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
	private List<ClientEntity> clientBook;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "CLIENT_BUY_FLAT", joinColumns = @JoinColumn(name = "idFlat") , inverseJoinColumns = @JoinColumn(name = "idClient") )
	private List<ClientEntity> clientBuy;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private ClientEntity owner;

	/**
	 * No-argument constructor
	 */
	public FlatEntity() {
	}

	/**
	 * This method create new flat object with builder.
	 * 
	 * @param builder
	 *            object FlatEntityBuilder
	 */
	public FlatEntity(FlatEntityBuilder builder) {
		this.id = builder.id;
		this.areaFlat = builder.areaFlat;
		this.numberRoom = builder.numberRoom;
		this.numberBalconie = builder.numberBalconie;
		this.floor = builder.floor;
		this.address = builder.address;
		this.flatStatus = builder.flatStatus;
		this.building = builder.building;
		this.price = builder.price;
		this.clientBook = builder.clientBook;
		this.clientBuy = builder.clientBuy;
		this.owner = builder.owner;
		this.version = builder.version;
	}

	/**
	 * This method build new flat object.
	 * 
	 * @return new flat object
	 */
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	/**
	 * Flat builder class
	 *
	 */
	public static class FlatEntityBuilder {
		private Long id;
		private Double areaFlat;
		private Integer numberRoom;
		private Integer numberBalconie;
		private Integer floor;
		private Address address;
		private StatusEntity flatStatus;
		private BuildingEntity building;
		private Double price;
		private List<ClientEntity> clientBook;
		private List<ClientEntity> clientBuy;
		private ClientEntity owner;
		private Long version;

		/**
		 * No-argument constructor
		 */
		public FlatEntityBuilder() {
		}

		/**
		 * This method create flat with id.
		 * 
		 * @param id
		 *            index flat
		 * @return new flat object with id
		 */
		public FlatEntityBuilder withId(Long id) {
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
		public FlatEntityBuilder withAreaFlat(Double areaFlat) {
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
		public FlatEntityBuilder withNumberRoom(Integer numberRoom) {
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
		public FlatEntityBuilder withNumberBalconie(Integer numberBalconie) {
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
		public FlatEntityBuilder withFloor(Integer floor) {
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
		public FlatEntityBuilder withAddress(Address address) {
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
		public FlatEntityBuilder withFlatStatus(StatusEntity flatStatus) {
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
		public FlatEntityBuilder withBuilding(BuildingEntity building) {
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
		public FlatEntityBuilder withPrice(Double price) {
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
		public FlatEntityBuilder withClientBook(List<ClientEntity> clientBook) {
			this.clientBook = clientBook;
			return this;
		}

		/**
		 * This method return flat with list client who buy this flat.
		 * 
		 * @param clientBuy
		 *            list client who buy this flat
		 * @return new flat object with list client who buy flats
		 */
		public FlatEntityBuilder withClientBuy(List<ClientEntity> clientBuy) {
			this.clientBuy = clientBuy;
			return this;
		}

		/**
		 * This method return client who is owner this flat.
		 * 
		 * @param owner
		 *            client who is owner this flat
		 * @return new flat object with client who is owner this flat
		 */
		public FlatEntityBuilder withOwner(ClientEntity owner) {
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
		public FlatEntityBuilder withVersion(Long version) {
			this.version = version;
			return this;
		}

		/**
		 * This method build new flat object.
		 * 
		 * @return create new flat object
		 */
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