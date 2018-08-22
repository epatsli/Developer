package com.capgemini.types;

import java.util.List;

import com.capgemini.exception.IncorrectParameterException;

public class ClientTO {
	private Long idClient;
	private String firstName;
	private String lastName;
	private AddressMap address;
	private String phoneNumber;
	private List<Long> listBookFlat;
	private List<Long> listBuyFlat;
	private Long version;

	public ClientTO() {
	}

	public ClientTO(ClientTOBuilder builder) {
		this.idClient = builder.idClient;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.address = builder.address;
		this.phoneNumber = builder.phoneNumber;
		this.listBookFlat = builder.listBookFlat;
		this.listBuyFlat = builder.listBuyFlat;
		this.version = builder.version;
	}

	public ClientTOBuilder builder() {
		return new ClientTOBuilder();
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

	public AddressMap getAddress() {
		return address;
	}

	public void setAddress(AddressMap address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Long> getListBookFlat() {
		return listBookFlat;
	}

	public void setListBookFlat(List<Long> listBookFlat) {
		this.listBookFlat = listBookFlat;
	}

	public List<Long> getListBuyFlat() {
		return listBuyFlat;
	}

	public void setListBuyFlat(List<Long> listBuyFlat) {
		this.listBuyFlat = listBuyFlat;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public static class ClientTOBuilder {
		private Long idClient;
		private String firstName;
		private String lastName;
		private AddressMap address;
		private String phoneNumber;
		private List<Long> listBookFlat;
		private List<Long> listBuyFlat;
		private Long version;

		public ClientTOBuilder() {
		}

		public ClientTOBuilder withIdClient(Long idClient) {
			this.idClient = idClient;
			return this;
		}

		public ClientTOBuilder withFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public ClientTOBuilder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public ClientTOBuilder withAddress(AddressMap address) {
			this.address = address;
			return this;
		}

		public ClientTOBuilder withPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public ClientTOBuilder withListBookFlat(List<Long> listBookFlat) {
			this.listBookFlat = listBookFlat;
			return this;
		}

		public ClientTOBuilder withListBuyFlat(List<Long> listBuyFlat) {
			this.listBuyFlat = listBuyFlat;
			return this;
		}

		public ClientTOBuilder withVersion(Long version) {
			this.version = version;
			return this;
		}

		public ClientTO build() {
			checkBeforeBuild();
			return new ClientTO(this);
		}

		private void checkBeforeBuild() {

			if (firstName == null || lastName == null || phoneNumber == null) {
				throw new IncorrectParameterException("Thic client can't be created.");
			}

		}
	}

}
