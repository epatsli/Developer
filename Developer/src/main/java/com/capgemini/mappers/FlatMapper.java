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

/**
 * Flat mapper
 *
 */
@Component
public class FlatMapper {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Thi method change flat entity to flat TO.
	 * 
	 * @param flatEntity
	 *            flat entity
	 * @return flat TO
	 */
	public FlatTO toFlatTO(FlatEntity flatEntity) {

		if (flatEntity == null)
			return null;

		FlatTOBuilder newFlatTO = new FlatTOBuilder().withId(flatEntity.getId()).withAreaFlat(flatEntity.getAreaFlat())
				.withNumberRoom(flatEntity.getNumberRoom()).withNumberBalconie(flatEntity.getNumberBalconie())
				.withFloor(flatEntity.getFloor()).withAddress(AddressMapper.mapToTO(flatEntity.getAddress()))
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

	/**
	 * This method change flat TO to flat entity.
	 * 
	 * @param flatTO
	 *            flat TO
	 * @return flat entity
	 */
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
		if (idBuilding != null) {
			newBuilding = entityManager.getReference(BuildingEntity.class, idBuilding);
		}

		ClientEntity newOwnerFlat = new ClientEntity();
		Long idClient = flatTO.getOwner();
		if (idClient != null) {
			newOwnerFlat = entityManager.getReference(ClientEntity.class, idClient);
		}
		FlatEntityBuilder FlatEntityBuilder = new FlatEntityBuilder().withId(flatTO.getId())
				.withAreaFlat(flatTO.getAreaFlat()).withNumberRoom(flatTO.getNumberRoom())
				.withNumberBalconie(flatTO.getNumberBalconie()).withFloor(flatTO.getFloor())
				.withAddress(AddressMapper.mapToEntity(flatTO.getAddress())).withClientBook(newBookFlats)
				.withClientBuy(newBuyFlats).withFlatStatus(newStatus).withBuilding(newBuilding)
				.withVersion(flatTO.getVersion()).withOwner(newOwnerFlat);

		return FlatEntityBuilder.build();

	}

	/**
	 * This method change list flat entity to list flat TO.
	 * 
	 * @param flatEntitys
	 *            list flat entity
	 * @return list flat TO
	 */
	public List<FlatTO> map2TOs(List<FlatEntity> flatEntitys) {
		return flatEntitys.stream().map(this::toFlatTO).collect(Collectors.toList());
	}

	/**
	 * This method change list flat TO to list flat entity.
	 * 
	 * @param flatTOs
	 *            list flat TO
	 * @return list flat entity
	 */
	public List<FlatEntity> map2Entities(List<FlatTO> flatTOs) {
		return flatTOs.stream().map(this::toFlatEntity).collect(Collectors.toList());
	}

	/**
	 * This method change list index flat to list new object flat entity.
	 * 
	 * @param flatLong
	 *            index flat
	 * @return new list flat object with index
	 */
	public List<FlatEntity> map2EntityLong(List<Long> flatLong) {

		List<FlatEntity> flats = new ArrayList<>();
		for (Long id : flatLong) {
			flats.add(entityManager.getReference(FlatEntity.class, id));
		}
		return flats;
	}
}
