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

		FlatTOBuilder newFlatTO = new FlatTOBuilder().withId(flatEntity.getId()).withAreaFlat(flatEntity.getAreaFlat())
				.withNumberRoom(flatEntity.getNumberRoom()).withNumberBalconie(flatEntity.getNumberBalconie())
				.withFloor(flatEntity.getFloor()).withNumberFlat(flatEntity.getNumberFlat())
				.withBuilding(flatEntity.getBuilding().getId()).withFlatStatus(flatEntity.getFlatStatus().getId())
				.withPrice(flatEntity.getPrice()).withVersion(flatEntity.getVersion())
				.withOwner(flatEntity.getOwner().getId());

		if (flatEntity.getClientBook() != null) {
			newFlatTO.withBookByClient(
					flatEntity.getClientBook().stream().map(s -> s.getId()).collect(Collectors.toList()));
		}

		if (flatEntity.getClientBuy() != null) {
			newFlatTO.withBuyByClient(
					flatEntity.getClientBuy().stream().map(s -> s.getId()).collect(Collectors.toList()));
		}

		return newFlatTO.build();
	}

	public FlatEntity toFlatEntity(FlatTO flatTO) {

		if (flatTO == null)
			return null;

		List<Long> bookFlats = flatTO.getBookByClient();
		List<ClientEntity> newBookFlats = new ArrayList<>();
		if (bookFlats != null) {
			for (Long id : bookFlats) {
				newBookFlats.add(entityManager.getReference(ClientEntity.class, id));
			}
		}

		List<Long> buyFlats = flatTO.getBuyByClient();
		List<ClientEntity> newBuyFlats = new ArrayList<>();
		if (buyFlats != null) {
			for (Long id : buyFlats) {
				newBuyFlats.add(entityManager.getReference(ClientEntity.class, id));
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

		FlatEntityBuilder FlatEntityBuilder = new FlatEntityBuilder().withId(flatTO.getId())
				.withAreaFlat(flatTO.getAreaFlat()).withNumberRoom(flatTO.getNumberRoom())
				.withNumberBalconie(flatTO.getNumberBalconie()).withFloor(flatTO.getFloor())
				.withNumberFlat(flatTO.getNumberFlat()).withClientBook(newBookFlats).withClientBuy(newBuyFlats)
				.withFlatStatus(newStatus).withBuilding(newBuilding).withVersion(flatTO.getVersion())
				.withOwner(newOwnerFlat);

		return FlatEntityBuilder.build();

	}

	public List<FlatTO> map2TOs(List<FlatEntity> FlatEntitys) {
		return FlatEntitys.stream().map(this::toFlatTO).collect(Collectors.toList());
	}

	public List<FlatEntity> map2Entities(List<FlatTO> FlatTOs) {
		return FlatTOs.stream().map(this::toFlatEntity).collect(Collectors.toList());
	}

}
