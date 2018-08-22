package com.capgemini.types;

import com.capgemini.exception.IncorrectParameterException;

public class AddressMap {
	private String street;
	private String houseNumber;
	private String city;
	private String postCode;

	public AddressMap() {
	}

	public AddressMap(AddressTOBuilder build) {
		this.street = build.street;
		this.houseNumber = build.houseNumber;
		this.city = build.city;
		this.postCode = build.postCode;
	}

	public AddressTOBuilder builder() {
		return new AddressTOBuilder();
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * This is auxiliary class to build new object.
	 *
	 */
	public static class AddressTOBuilder {
		private String street;
		private String houseNumber;
		private String city;
		private String postCode;

		/**
		 * No-argument constructor
		 */
		public AddressTOBuilder() {
		}

		/**
		 * Method which create object with street name.
		 * 
		 * @param street
		 *            new street name
		 * @return new object
		 */
		public AddressTOBuilder withStreet(String street) {
			this.street = street;
			return this;
		}

		/**
		 * Method which create object with house number.
		 * 
		 * @param houseNumber
		 * @return new object
		 */
		public AddressTOBuilder withHouseNumber(String houseNumber) {
			this.houseNumber = houseNumber;
			return this;
		}

		/**
		 * Method which create object with city name.
		 * 
		 * @param city
		 * @return new object
		 */
		public AddressTOBuilder withCity(String city) {
			this.city = city;
			return this;
		}

		/**
		 * Method which create object with post code.
		 * 
		 * @param postCode
		 * @return new object
		 */
		public AddressTOBuilder withPostCode(String postCode) {
			this.postCode = postCode;
			return this;
		}

		private void checkBeforeBuild() {
			if (street == null || houseNumber == null || city == null || postCode == null) {
				throw new IncorrectParameterException("This address can't be created.");
			}
		}

		/**
		 * This method creates a new object if we provide all the required
		 * parameters.
		 * 
		 * @return new object
		 */
		public AddressMap build() {
			checkBeforeBuild();
			return new AddressMap(this);
		}

	}

}
