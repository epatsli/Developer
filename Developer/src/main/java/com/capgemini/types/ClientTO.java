package com.capgemini.types;

import java.util.List;

import com.capgemini.exception.IncorrectParameterException;

public class ClientTO {
	private Long id;
	private String firstName;
	private String lastName;
	private AddressMap address;
	private String phoneNumber;
	private List<Long> bookFlats;
	private List<Long> buyFlats;
	private Long version;
	private List<Long> ownerFlats;

	public ClientTO() {
	}

	public ClientTO(ClientTOBuilder builder) {
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

	public ClientTOBuilder builder() {
		return new ClientTOBuilder();
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

	public List<Long> getBookFlats() {
		return bookFlats;
	}

	public void setBookFlats(List<Long> bookFlats) {
		this.bookFlats = bookFlats;
	}

	public List<Long> getBuyFlats() {
		return buyFlats;
	}

	public void setBuyFlats(List<Long> buyFlats) {
		this.buyFlats = buyFlats;
	}

	public List<Long> getOwnerFlats() {
		return ownerFlats;
	}

	public void setOwnerFlats(List<Long> ownerFlats) {
		this.ownerFlats = ownerFlats;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public static class ClientTOBuilder {
		private Long id;
		private String firstName;
		private String lastName;
		private AddressMap address;
		private String phoneNumber;
		private List<Long> bookFlats;
		private List<Long> buyFlats;
		private List<Long> ownerFlats;
		private Long version;

		public ClientTOBuilder() {
		}

		public ClientTOBuilder withId(Long id) {
			this.id = id;
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

		public ClientTOBuilder withBookFlats(List<Long> bookFlats) {
			this.bookFlats = bookFlats;
			return this;
		}

		public ClientTOBuilder withBuyFlats(List<Long> buyFlats) {
			this.buyFlats = buyFlats;
			return this;
		}

		public ClientTOBuilder withOwnerFlats(List<Long> ownerFlats) {
			this.ownerFlats = ownerFlats;
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
