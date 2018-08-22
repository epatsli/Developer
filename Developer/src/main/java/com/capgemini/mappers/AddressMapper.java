package com.capgemini.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.domain.Address;
import com.capgemini.types.AddressMap;

public class AddressMapper {

	public static AddressMap mapToTO(Address addressEntity) {

		return new AddressMap().builder().withStreet(addressEntity.getStreet())
				.withHouseNumber(addressEntity.getNumberHous()).withCity(addressEntity.getCity())
				.withPostCode(addressEntity.getPostCode()).build();
	}

	public static Address mapToEntity(AddressMap addressTO) {

		return new Address().builder().withStreet(addressTO.getStreet()).withHouseNumber(addressTO.getHouseNumber())
				.withCity(addressTO.getCity()).withPostCode(addressTO.getPostCode()).build();
	}

	public static List<AddressMap> mapToTO(List<Address> addressEntity) {
		return addressEntity.stream().map(AddressMapper::mapToTO).collect(Collectors.toList());
	}

	public static List<Address> mapToEntity(List<AddressMap> addressTOs) {
		return addressTOs.stream().map(AddressMapper::mapToEntity).collect(Collectors.toList());
	}
}
