package com.capgemini.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.capgemini.exception.IncorrectParameterException;
import com.capgemini.listener.Listener;

/**
 * ClientEntity
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ Listener.class })
@Table(name = "CLIENTS")
public class ClientEntity extends AbstractListenerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Version
	private Long version;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "firstName", length = 16)
	private String firstName;

	@Column(name = "lastName", length = 16)
	private String lastName;

	@Embedded
	private Address address;

	@Column(name = "phoneNumber", length = 16)
	private String phoneNumber;

	@ManyToMany(mappedBy = "clientBook", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<FlatEntity> bookFlats = new ArrayList<>();

	@ManyToMany(mappedBy = "clientBuy", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<FlatEntity> buyFlats = new ArrayList<>();

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private List<FlatEntity> ownerFlats = new ArrayList<>();

	/**
	 * No-argument constructor
	 */
	public ClientEntity() {
	}

	/**
	 * Constructor creating client.
	 * 
	 * @param builder
	 *            object ClientEntityBuilder
	 */
	public ClientEntity(ClientEntityBuilder builder) {
		this.id = builder.id;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.address = builder.address;
		this.phoneNumber = builder.phoneNumber;
		this.bookFlats = builder.bookFlats;
		this.buyFlats = builder.buyFlats;
		this.ownerFlats = builder.ownerFlats;
		this.version = builder.version;
	}

	/**
	 * Builder to build client object.
	 * 
	 * @return new client object
	 */
	public ClientEntityBuilder builder() {
		return new ClientEntityBuilder();
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<FlatEntity> getBookFlats() {
		return bookFlats;
	}

	public void setBookFlats(List<FlatEntity> bookFlats) {
		this.bookFlats = bookFlats;
	}

	public List<FlatEntity> getBuyFlats() {
		return buyFlats;
	}

	public void setBuyFlats(List<FlatEntity> buyFlats) {
		this.buyFlats = buyFlats;
	}

	public List<FlatEntity> getOwnerFlats() {
		return ownerFlats;
	}

	public void setOwnerFlats(List<FlatEntity> ownerFlats) {
		this.ownerFlats = ownerFlats;
	}

	/**
	 * Client builder class
	 *
	 */
	/**
	 * @author PATRSLIW
	 *
	 */
	public static class ClientEntityBuilder {
		private Long id;
		private String firstName;
		private String lastName;
		private Address address;
		private String phoneNumber;
		private List<FlatEntity> bookFlats;
		private List<FlatEntity> buyFlats;
		private List<FlatEntity> ownerFlats;
		private Long version;

		/**
		 * No-argument constructor
		 */
		public ClientEntityBuilder() {
		}

		/**
		 * This method create client with id.
		 * 
		 * @param id
		 *            index client
		 * @return new client object with id
		 */
		public ClientEntityBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		/**
		 * This method create client with first name.
		 * 
		 * @param firstName
		 *            client name
		 * @return new client object with name
		 */
		public ClientEntityBuilder withFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		/**
		 * This method create client with last name.
		 * 
		 * @param lastName
		 *            client surname
		 * @return new client object with surname
		 */
		public ClientEntityBuilder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		/**
		 * This method create client with address.
		 * 
		 * @param address
		 *            client address
		 * @return new client object with address
		 */
		public ClientEntityBuilder withAddress(Address address) {
			this.address = address;
			return this;
		}

		/**
		 * This method create client with phone number.
		 * 
		 * @param phoneNumber
		 *            client phone number
		 * @return new client object with phone number
		 */
		public ClientEntityBuilder withPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		/**
		 * This method create client with list book flats.
		 * 
		 * @param bookFlats
		 *            flats which book by client
		 * @return new client object with list book flats
		 */
		public ClientEntityBuilder withBookFlats(List<FlatEntity> bookFlats) {
			this.bookFlats = bookFlats;
			return this;
		}

		/**
		 * This method create client with list buy flats.
		 * 
		 * @param buyFlats
		 *            flats which was buy by client
		 * @return new client object with list buy flats
		 */
		public ClientEntityBuilder withBuyFlats(List<FlatEntity> buyFlats) {
			this.buyFlats = buyFlats;
			return this;
		}

		/**
		 * This method create client with list flats where he is owner flats.
		 * 
		 * @param ownerFlats
		 *            list flats where client is owner
		 * @return new client object with flats where client is owner
		 */
		public ClientEntityBuilder withOwnerFlats(List<FlatEntity> ownerFlats) {
			this.ownerFlats = ownerFlats;
			return this;
		}

		/**
		 * This method create client with version.
		 * 
		 * @param version
		 *            client object version
		 * @return new client object with version
		 */
		public ClientEntityBuilder withVersion(Long version) {
			this.version = version;
			return this;
		}

		/**
		 * This method build client object.
		 * 
		 * @return new client object
		 */
		public ClientEntity build() {
			checkBeforeBuild();
			return new ClientEntity(this);
		}

		private void checkBeforeBuild() {

			if (firstName == null || lastName == null || phoneNumber == null) {
				throw new IncorrectParameterException("This client can't be created.");
			}

		}
	}

}
