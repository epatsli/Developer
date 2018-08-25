package com.capgemini.mappers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.capgemini.domain.Address;
import com.capgemini.exception.IncorrectParameterException;
import com.capgemini.types.AddressMap;

@Component
public class AddressMapper {

	@PersistenceContext
	private EntityManager entityManager;

	public static AddressMap mapToTO(Address addressEntity) {

		return new AddressMap().builder().withStreet(addressEntity.getStreet())
				.withHouseNumber(addressEntity.getNumberHous()).withCity(addressEntity.getCity())
				.withPostCode(addressEntity.getPostCode()).build();
	}

	public static Address mapToEntity(AddressMap addressTO) {

		if (addressTO == null)
			throw new IncorrectParameterException("Address can't be empty.");

		return new Address().builder().withStreet(addressTO.getStreet()).withHouseNumber(addressTO.getHouseNumber())
				.withCity(addressTO.getCity()).withPostCode(addressTO.getPostCode()).build();
	}

	// public static List<AddressMap> mapToTO(List<Address> addressEntity) {
	// return
	// addressEntity.stream().map(AddressMapper::mapToTO).collect(Collectors.toList());
	// }
	//
	// public static List<Address> mapToEntity(List<AddressMap> addressTOs) {
	// return
	// addressTOs.stream().map(AddressMapper::mapToEntity).collect(Collectors.toList());
	// }
}
