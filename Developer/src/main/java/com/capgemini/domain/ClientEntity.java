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
	public Long version;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idClient;

	@Column(name = "firstName", length = 16, nullable = false)
	private String firstName;

	@Column(name = "lastName", length = 16, nullable = false)
	private String lastName;

	@Embedded
	private AddressEntity address;

	@Column(name = "phoneNumber", length = 16, nullable = false)
	private String phoneNumber;

	@ManyToMany(mappedBy = "listClientBook", cascade = CascadeType.ALL)
	private List<FlatEntity> listBookFlat = new ArrayList<>();

	@ManyToMany(mappedBy = "listClientBuy", cascade = CascadeType.ALL)
	private List<FlatEntity> listBuyFlat = new ArrayList<>();

	public ClientEntity() {
	}

	public ClientEntity(ClientEntityBuilder builder) {
		this.idClient = builder.idClient;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.address = builder.address;
		this.phoneNumber = builder.phoneNumber;
		this.listBookFlat = builder.listBookFlat;
		this.listBuyFlat = builder.listBuyFlat;
	}

	public ClientEntityBuilder builder() {
		return new ClientEntityBuilder();
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
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

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<FlatEntity> getListBookFlat() {
		return listBookFlat;
	}

	public void setListBookFlat(List<FlatEntity> listBookFlat) {
		this.listBookFlat = listBookFlat;
	}

	public List<FlatEntity> getListBuyFlat() {
		return listBuyFlat;
	}

	public void setListBuyFlat(List<FlatEntity> listBuyFlat) {
		this.listBuyFlat = listBuyFlat;
	}

	public static class ClientEntityBuilder {
		private Long idClient;
		private String firstName;
		private String lastName;
		private AddressEntity address;
		private String phoneNumber;
		private List<FlatEntity> listBookFlat;
		private List<FlatEntity> listBuyFlat;

		public ClientEntityBuilder() {
		}

		public ClientEntityBuilder withIdClient(Long idClient) {
			this.idClient = idClient;
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

		public ClientEntityBuilder withAddress(AddressEntity address) {
			this.address = address;
			return this;
		}

		public ClientEntityBuilder withPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public ClientEntityBuilder withListBookFlat(List<FlatEntity> listBookFlat) {
			this.listBookFlat = listBookFlat;
			return this;
		}

		public ClientEntityBuilder withListBuyFlat(List<FlatEntity> listBuyFlat) {
			this.listBuyFlat = listBuyFlat;
			return this;
		}

		public ClientEntity build() {
			checkBeforeBuild();
			return new ClientEntity(this);
		}

		private void checkBeforeBuild() {

			if (firstName == null || lastName == null || phoneNumber == null) {
				throw new IncorrectParameterException("Thic client can't be created.");
			}

		}
	}

}
