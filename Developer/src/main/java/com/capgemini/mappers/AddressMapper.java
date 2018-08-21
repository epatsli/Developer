package com.capgemini.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.domain.AddressEntity;
import com.capgemini.types.AddressTO;

public class AddressMapper {
	public static AddressTO mapToTO(AddressEntity addressEntity) {

		return new AddressTO().builder().withStreet(addressEntity.getStreet())
				.withHouseNumber(addressEntity.getNumberHous()).withCity(addressEntity.getCity())
				.withPostCode(addressEntity.getPostCode()).build();
	}

	public static AddressEntity mapToEntity(AddressTO addressTO) {

		return new AddressEntity().builder().withStreet(addressTO.getStreet())
				.withHouseNumber(addressTO.getHouseNumber()).withCity(addressTO.getCity())
				.withPostCode(addressTO.getPostCode()).build();
	}

	public static List<AddressTO> mapToTO(List<AddressEntity> addressEntity) {
		return addressEntity.stream().map(AddressMapper::mapToTO).collect(Collectors.toList());
	}

	public static List<AddressEntity> mapToEntity(List<AddressTO> addressTOs) {
		return addressTOs.stream().map(AddressMapper::mapToEntity).collect(Collectors.toList());
	}
}
