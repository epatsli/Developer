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

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ Listener.class })
@Table(name = "CLIENTS")
public class ClientEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Version
	private Long version;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "firstName", length = 16, nullable = false)
	private String firstName;

	@Column(name = "lastName", length = 16, nullable = false)
	private String lastName;

	@Embedded
	private Address address;

	@Column(name = "phoneNumber", length = 16, nullable = false)
	private String phoneNumber;

	@ManyToMany(mappedBy = "clientBook", cascade = CascadeType.ALL)
	private List<FlatEntity> bookFlats = new ArrayList<>();

	@ManyToMany(mappedBy = "clientBuy", cascade = CascadeType.ALL)
	private List<FlatEntity> buyFlats = new ArrayList<>();

	@OneToMany(mappedBy = "owner")
	private List<FlatEntity> ownerFlats;

	public ClientEntity() {
	}

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

		public ClientEntityBuilder() {
		}

		public ClientEntityBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		public ClientEntityBuilder withFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public ClientEntityBuilder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public ClientEntityBuilder withAddress(Address address) {
			this.address = address;
			return this;
		}

		public ClientEntityBuilder withPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public ClientEntityBuilder withBookFlats(List<FlatEntity> bookFlats) {
			this.bookFlats = bookFlats;
			return this;
		}

		public ClientEntityBuilder withBuyFlats(List<FlatEntity> buyFlats) {
			this.buyFlats = buyFlats;
			return this;
		}

		public ClientEntityBuilder withOwnerFlats(List<FlatEntity> wnerFlats) {
			this.ownerFlats = ownerFlats;
			return this;
		}

		public ClientEntityBuilder withVersion(Long version) {
			this.version = version;
			return this;
		}

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
