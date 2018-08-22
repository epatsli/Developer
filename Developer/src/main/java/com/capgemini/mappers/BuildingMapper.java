package com.capgemini.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.BuildingEntity.BuildingEntityBuilder;
import com.capgemini.domain.FlatEntity;
import com.capgemini.types.BuildingTO;
import com.capgemini.types.BuildingTO.BuildingTOBuilder;

@Component
public class BuildingMapper {

	@PersistenceContext
	private EntityManager entityManager;

	public BuildingTO toBuildingTO(BuildingEntity buildingEntity) {

		if (buildingEntity == null)
			return null;

		BuildingTOBuilder newBuildingTO = new BuildingTOBuilder().withIdBuilding(buildingEntity.getIdBuilding())
				.withDescription(buildingEntity.getDescription())
				.withAddress(AddressMapper.mapToTO(buildingEntity.getAddress()))
				.withNumberFloor(buildingEntity.getNumberFloor()).withElevator(buildingEntity.getElevator())
				.withNumberFlat(buildingEntity.getNumberFlat());

		if (buildingEntity.getListFlat() != null) {
			newBuildingTO.withListFlat(
					buildingEntity.getListFlat().stream().map(s -> s.getIdFlat()).collect(Collectors.toList()));
		}
		return newBuildingTO.build();
	}

	public BuildingEntity toBuildingEntity(BuildingTO buildingTO) {

		if (buildingTO == null)
			return null;

		List<Long> listFlat = buildingTO.getListFlat();
		List<FlatEntity> newListFlat = new ArrayList<>();

		if (listFlat != null) {
			for (Long idFlat : listFlat) {
				newListFlat.add(entityManager.getReference(FlatEntity.class, idFlat));
			}
		}

		BuildingEntityBuilder buildingEntityBuilder = new BuildingEntityBuilder()
				.withIdBuilding(buildingTO.getIdBuilding()).withDescription(buildingTO.getDescription())
				.withAddress(AddressMapper.mapToEntity(buildingTO.getAddress()))
				.withNumberFloor(buildingTO.getNumberFloor()).withElevator(buildingTO.getElevator())
				.withNumberFlat(buildingTO.getNumberFlat()).withListFlat(newListFlat);
		return buildingEntityBuilder.build();

	}

	public List<BuildingTO> map2TOs(List<BuildingEntity> BuildingEntitys) {
		return BuildingEntitys.stream().map(this::toBuildingTO).collect(Collectors.toList());
	}

	public List<BuildingEntity> map2Entities(List<BuildingTO> BuildingTOs) {
		return BuildingTOs.stream().map(this::toBuildingEntity).collect(Collectors.toList());
	}

}
