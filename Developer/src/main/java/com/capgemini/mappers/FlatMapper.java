package com.capgemini.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.FlatEntity;
import com.capgemini.domain.FlatEntity.FlatEntityBuilder;
import com.capgemini.domain.StatusEntity;
import com.capgemini.types.FlatTO;
import com.capgemini.types.FlatTO.FlatTOBuilder;

@Component
public class FlatMapper {

	@PersistenceContext
	private EntityManager entityManager;

	public FlatTO toFlatTO(FlatEntity flatEntity) {

		if (flatEntity == null)
			return null;

		FlatTOBuilder newFlatTO = new FlatTOBuilder().withIdFlat(flatEntity.getIdFlat())
				.withAreaFlat(flatEntity.getAreaFlat()).withNumberRoom(flatEntity.getNumberRoom())
				.withNumberBalconie(flatEntity.getNumberBalconie()).withFloor(flatEntity.getFloor())
				.withAddress(AddressMapper.mapToTO(flatEntity.getAddress()))
				.withBuilding(flatEntity.getBuilding().getIdBuilding())
				.withFlatStatus(flatEntity.getFlatStatus().getIdStatus()).withPrice(flatEntity.getPrice())
				.withVersion(flatEntity.getVersion()).withOwner(flatEntity.getOwner().getIdClient());

		if (flatEntity.getListClientBook() != null) {
			newFlatTO.withListClientBook(
					flatEntity.getListClientBook().stream().map(s -> s.getIdClient()).collect(Collectors.toList()));
		}

		if (flatEntity.getListClientBuy() != null) {
			newFlatTO.withListClientBuy(
					flatEntity.getListClientBuy().stream().map(s -> s.getIdClient()).collect(Collectors.toList()));
		}

		return newFlatTO.build();
	}

	public FlatEntity toFlatEntity(FlatTO flatTO) {

		if (flatTO == null)
			return null;

		List<Long> listBookFlat = flatTO.getListClientBook();
		List<ClientEntity> newListBookFlat = new ArrayList<>();
		if (listBookFlat != null) {
			for (Long idClient : listBookFlat) {
				newListBookFlat.add(entityManager.getReference(ClientEntity.class, idClient));
			}
		}

		List<Long> listBuyFlat = flatTO.getListClientBuy();
		List<ClientEntity> newListBuyFlat = new ArrayList<>();
		if (listBuyFlat != null) {
			for (Long idClient : listBuyFlat) {
				newListBuyFlat.add(entityManager.getReference(ClientEntity.class, idClient));
			}
		}

		StatusEntity newStatus = new StatusEntity();
		Long idStatus = flatTO.getFlatStatus();
		newStatus = entityManager.getReference(StatusEntity.class, idStatus);

		BuildingEntity newBuilding = new BuildingEntity();
		Long idBuilding = flatTO.getBuilding();
		newBuilding = entityManager.getReference(BuildingEntity.class, idBuilding);

		ClientEntity newOwnerFlat = new ClientEntity();
		Long idClient = flatTO.getOwner();
		newOwnerFlat = entityManager.getReference(ClientEntity.class, idClient);

		FlatEntityBuilder FlatEntityBuilder = new FlatEntityBuilder().withIdFlat(flatTO.getIdFlat())
				.withAreaFlat(flatTO.getAreaFlat()).withNumberRoom(flatTO.getNumberRoom())
				.withNumberBalconie(flatTO.getNumberBalconie()).withFloor(flatTO.getFloor())
				.withAddress(AddressMapper.mapToEntity(flatTO.getAddress())).withListClientBook(newListBookFlat)
				.withListClientBuy(newListBuyFlat).withFlatStatus(newStatus).withBuilding(newBuilding)
				.withVersion(flatTO.getVersion()).withOwner(newOwnerFlat);

		return FlatEntityBuilder.build();

	}

	public List<FlatTO> map2TOs(List<FlatEntity> FlatEntitys) {
		return FlatEntitys.stream().map(this::toFlatTO).collect(Collectors.toList());
	}

	public List<FlatEntity> map2Entities(List<FlatTO> FlatTOs) {
		return FlatTOs.stream().map(this::toFlatEntity).collect(Collectors.toList());
	}

}
