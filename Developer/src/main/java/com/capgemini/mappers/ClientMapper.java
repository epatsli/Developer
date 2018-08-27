package com.capgemini.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.ClientEntity.ClientEntityBuilder;
import com.capgemini.domain.FlatEntity;
import com.capgemini.types.ClientTO;
import com.capgemini.types.ClientTO.ClientTOBuilder;

/**
 * Client mapper
 *
 */
@Component
public class ClientMapper {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * This client change client entity to client TO.
	 * 
	 * @param clientEntity
	 *            client entity
	 * @return client TO
	 */
	public ClientTO toClientTO(ClientEntity clientEntity) {

		if (clientEntity == null)
			return null;

		AddressMapper.mapToTO(clientEntity.getAddress());
		ClientTOBuilder newClientTO = new ClientTOBuilder().withId(clientEntity.getId())
				.withFirstName(clientEntity.getFirstName()).withLastName(clientEntity.getLastName())
				.withAddress(AddressMapper.mapToTO(clientEntity.getAddress()))
				.withPhoneNumber(clientEntity.getPhoneNumber()).withVersion(clientEntity.getVersion());

		if (clientEntity.getBookFlats() != null) {
			newClientTO.withBookFlats(
					clientEntity.getBookFlats().stream().map(s -> s.getId()).collect(Collectors.toList()));
		}

		if (clientEntity.getBuyFlats() != null) {
			newClientTO
					.withBuyFlats(clientEntity.getBuyFlats().stream().map(s -> s.getId()).collect(Collectors.toList()));
		}

		if (clientEntity.getOwnerFlats() != null) {
			newClientTO.withOwnerFlats(
					clientEntity.getOwnerFlats().stream().map(s -> s.getId()).collect(Collectors.toList()));
		}

		return newClientTO.build();
	}

	/**
	 * This method change client TO to client entity.
	 * 
	 * @param clientTO
	 *            client TO
	 * @return client entity
	 */
	public ClientEntity toClientEntity(ClientTO clientTO) {

		if (clientTO == null)
			return null;

		List<Long> bookFlats = clientTO.getBookFlats();
		List<FlatEntity> newBookFlats = new ArrayList<>();

		if (bookFlats != null) {
			for (Long id : bookFlats) {
				newBookFlats.add(entityManager.getReference(FlatEntity.class, id));
			}
		}

		List<Long> buyFlat = clientTO.getBuyFlats();
		List<FlatEntity> newBuyFlats = new ArrayList<>();

		if (buyFlat != null) {
			for (Long id : buyFlat) {
				newBuyFlats.add(entityManager.getReference(FlatEntity.class, id));
			}
		}

		List<Long> ownerFlats = clientTO.getBuyFlats();
		List<FlatEntity> newOwnerFlats = new ArrayList<>();

		if (ownerFlats != null) {
			for (Long id : ownerFlats) {
				newOwnerFlats.add(entityManager.getReference(FlatEntity.class, id));
			}
		}

		ClientEntityBuilder ClientEntityBuilder = new ClientEntityBuilder().withId(clientTO.getId())
				.withFirstName(clientTO.getFirstName()).withLastName(clientTO.getLastName())
				.withAddress(AddressMapper.mapToEntity(clientTO.getAddress()))
				.withPhoneNumber(clientTO.getPhoneNumber()).withBookFlats(newBookFlats).withBuyFlats(newBuyFlats)
				.withVersion(clientTO.getVersion()).withOwnerFlats(newOwnerFlats);

		return ClientEntityBuilder.build();

	}

	/**
	 * This method change list client entity to list client TO.
	 * 
	 * @param clientEntitys
	 *            list client entity
	 * @return list client TO
	 */
	public List<ClientTO> map2TOs(List<ClientEntity> clientEntitys) {
		return clientEntitys.stream().map(this::toClientTO).collect(Collectors.toList());
	}

	/**
	 * This method change index client to new object client entity.
	 * 
	 * @param clientLong
	 *            index client
	 * @return new object client only with index
	 */
	public ClientEntity map2EntityOnlyIdLong(Long clientLong) {
		return entityManager.getReference(ClientEntity.class, clientLong);
	}

	/**
	 * This method change list index client to list new object client entity.
	 * 
	 * @param clientLong
	 *            index client
	 * @return new list objects client with index
	 */
	public List<ClientEntity> map2EntityLong(List<Long> clientLong) {

		List<ClientEntity> clients = new ArrayList<>();
		for (Long id : clientLong) {
			clients.add(entityManager.getReference(ClientEntity.class, id));
		}
		return clients;
	}

}
