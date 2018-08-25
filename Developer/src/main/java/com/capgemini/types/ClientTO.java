package com.capgemini.types;

import java.util.List;

import com.capgemini.exception.IncorrectParameterException;

/**
 * Client TO
 *
 */
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

	/**
	 * No-argument constructor
	 */
	public ClientTO() {
	}

	/**
	 * Constructor creating client.
	 * 
	 * @param builder
	 *            object ClientTOBuilder
	 */
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

	/**
	 * Builder to build client object.
	 * 
	 * @return new client object
	 */
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

	/**
	 * Client builder class
	 *
	 */
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

		/**
		 * No-argument constructor
		 */
		public ClientTOBuilder() {
		}

		/**
		 * This method create client with id.
		 * 
		 * @param id
		 *            index client
		 * @return new client object with id
		 */
		public ClientTOBuilder withId(Long id) {
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
		public ClientTOBuilder withFirstName(String firstName) {
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
		public ClientTOBuilder withLastName(String lastName) {
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
		public ClientTOBuilder withAddress(AddressMap address) {
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
		public ClientTOBuilder withPhoneNumber(String phoneNumber) {
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
		public ClientTOBuilder withBookFlats(List<Long> bookFlats) {
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
		public ClientTOBuilder withBuyFlats(List<Long> buyFlats) {
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
		public ClientTOBuilder withOwnerFlats(List<Long> ownerFlats) {
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
		public ClientTOBuilder withVersion(Long version) {
			this.version = version;
			return this;
		}

		/**
		 * This method build client object.
		 * 
		 * @return new client object
		 */
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
