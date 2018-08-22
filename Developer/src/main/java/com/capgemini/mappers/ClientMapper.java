package com.capgemini.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.ClientEntity.ClientEntityBuilder;
import com.capgemini.domain.FlatEntity;
import com.capgemini.types.ClientTO;
import com.capgemini.types.ClientTO.ClientTOBuilder;

public class ClientMapper {

	@PersistenceContext
	private EntityManager entityManager;

	public ClientTO toClientTO(ClientEntity clientEntity) {

		if (clientEntity == null)
			return null;

		AddressMapper.mapToTO(clientEntity.getAddress());
		ClientTOBuilder newClientTO = new ClientTOBuilder().withIdClient(clientEntity.getIdClient())
				.withFirstName(clientEntity.getFirstName()).withLastName(clientEntity.getLastName())
				.withAddress(AddressMapper.mapToTO(clientEntity.getAddress()))
				.withPhoneNumber(clientEntity.getPhoneNumber());

		if (clientEntity.getListBookFlat() != null) {
			newClientTO.withListBookFlat(
					clientEntity.getListBookFlat().stream().map(s -> s.getIdFlat()).collect(Collectors.toList()));
		}

		if (clientEntity.getListBuyFlat() != null) {
			newClientTO.withListBuyFlat(
					clientEntity.getListBuyFlat().stream().map(s -> s.getIdFlat()).collect(Collectors.toList()));
		}

		return newClientTO.build();
	}

	public ClientEntity toClientEntity(ClientTO clientTO) {

		if (clientTO == null)
			return null;

		List<Long> listBookFlat = clientTO.getListBookFlat();
		List<FlatEntity> newListBookFlat = new ArrayList<>();

		if (listBookFlat != null) {
			for (Long idFlat : listBookFlat) {
				newListBookFlat.add(entityManager.getReference(FlatEntity.class, idFlat));
			}
		}

		List<Long> listBuyFlat = clientTO.getListBuyFlat();
		List<FlatEntity> newListBuyFlat = new ArrayList<>();

		if (listBuyFlat != null) {
			for (Long idFlat : listBuyFlat) {
				newListBuyFlat.add(entityManager.getReference(FlatEntity.class, idFlat));
			}
		}

		ClientEntityBuilder ClientEntityBuilder = new ClientEntityBuilder().withIdClient(clientTO.getIdClient())
				.withFirstName(clientTO.getFirstName()).withLastName(clientTO.getLastName())
				.withAddress(AddressMapper.mapToEntity(clientTO.getAddress()))
				.withPhoneNumber(clientTO.getPhoneNumber()).withListBookFlat(newListBookFlat)
				.withListBuyFlat(newListBuyFlat);

		return ClientEntityBuilder.build();

	}

	public List<ClientTO> map2TOs(List<ClientEntity> clientEntitys) {
		return clientEntitys.stream().map(this::toClientTO).collect(Collectors.toList());
	}

	public List<ClientEntity> map2Entities(List<ClientTO> clientTOs) {
		return clientTOs.stream().map(this::toClientEntity).collect(Collectors.toList());
	}

}
