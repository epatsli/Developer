package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.FlatEntity;

public interface FlatDao extends Dao<FlatEntity, Long> {

	Double countSumTotalPriceFlatsPerClient(Long idClient);

	Double countAveragePriceFlatInBuilding(Long idBuilding);

	Integer countNumberFlatsInBuildingInStatus(Long idBuilding, Long idStatus);

	List<ClientEntity> findCustomersWhoBuyMoreThanOneFlats();

	BuildingEntity findBuildingWherIsMostFreeFlats();

	List<FlatEntity> findAllFlatsToDisablePerson();

}
