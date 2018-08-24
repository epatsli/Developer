package com.capgemini.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.capgemini.exception.IncorrectParameterException;

@Embeddable
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "street", length = 32) // , nullable = false)
	private String street;

	@Column(name = "houseNumber", length = 16) // , nullable = false)
	private String houseNumber;

	@Column(name = "city", length = 32) // , nullable = false)
	private String city;

	@Column(name = "postCode", length = 8) // , nullable = false)
	private String postCode;

	/**
	 * No-argument constructor
	 */
	public Address() {
	}

	/**
	 * Constructor creating an address with the help of a builder.
	 * 
	 * @param build
	 *            object AddressDataBuilder
	 * 
	 */
	public Address(AddressEntityBuilder build) {
		this.street = build.street;
		this.houseNumber = build.houseNumber;
		this.city = build.city;
		this.postCode = build.postCode;
	}

	/**
	 * This is method which crate new object.
	 * 
	 * @return AddressDataBuilder calls the builder'constructor
	 */
	public AddressEntityBuilder builder() {
		return new AddressEntityBuilder();
	}

	/**
	 * This is the getter which get street name.
	 * 
	 * @return street name
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * This is method which set street name.
	 * 
	 * @param street
	 *            new street name
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * This is method which get house number.
	 * 
	 * @return get house number
	 */
	public String getNumberHous() {
		return houseNumber;
	}

	/**
	 * This is method which set house number.
	 * 
	 * @param houseNumber
	 */
	public void setNumberHous(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	/**
	 * This method return name city.
	 * 
	 * @return name city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * This is method which set name city.
	 * 
	 * @param city
	 *            new name city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * This method return post code.
	 * 
	 * @return post code
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * This is method which set new post code.
	 * 
	 * @param poctCode
	 *            new post code
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * This is auxiliary class to build new object.
	 *
	 */
	public static class AddressEntityBuilder {

		private String street;
		private String houseNumber;
		private String city;
		private String postCode;

		/**
		 * No-argument constructor
		 */
		public AddressEntityBuilder() {
		}

		/**
		 * Method which create object with street name.
		 * 
		 * @param street
		 *            new street name
		 * @return new object
		 */
		public AddressEntityBuilder withStreet(String street) {
			this.street = street;
			return this;
		}

		/**
		 * Method which create object with house number.
		 * 
		 * @param houseNumber
		 * @return new object
		 */
		public AddressEntityBuilder withHouseNumber(String houseNumber) {
			this.houseNumber = houseNumber;
			return this;
		}

		/**
		 * Method which create object with city name.
		 * 
		 * @param city
		 * @return new object
		 */
		public AddressEntityBuilder withCity(String city) {
			this.city = city;
			return this;
		}

		/**
		 * Method which create object with post code.
		 * 
		 * @param postCode
		 * @return new object
		 */
		public AddressEntityBuilder withPostCode(String postCode) {
			this.postCode = postCode;
			return this;
		}

		/**
		 * This method checks if all required parameters have been given.
		 * 
		 * @throws can
		 *             get IncorrectParameterException
		 */
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
		public Address build() {
			checkBeforeBuild();
			return new Address(this);
		}
	}

}
