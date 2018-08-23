package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.FlatEntity;

public interface FlatDao extends Dao<FlatEntity, Long> {

	Double countSumTotalPriceFlatsPerClient(Long id);

	Double countAveragePriceFlatInBuilding(Long id);

	Integer countNumberFlatsInBuildingInStatus(Long id, Long idSta);

	List<ClientEntity> findCustomersWhoBuyMoreThanOneFlats();

	BuildingEntity findBuildingWherIsMostFreeFlats();

	List<FlatEntity> findAllFlatsToDisablePerson();

}
