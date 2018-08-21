package com.capgemini.types;

import java.util.List;

import com.capgemini.exception.IncorrectParameterException;

public class ClientTO {
	private Long idClient;
	private String firstName;
	private String lastName;
	private AddressTO address;
	private String phoneNumber;
	private List<Long> listBookFlat;
	private List<Long> listBuyFlat;

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
	}

	public ClientTOBuilder builder() {
		return new ClientTOBuilder();
	}

	public static class ClientTOBuilder {
		private Long idClient;
		private String firstName;
		private String lastName;
		private AddressTO address;
		private String phoneNumber;
		private List<Long> listBookFlat;
		private List<Long> listBuyFlat;

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
