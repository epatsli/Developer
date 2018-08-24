package com.capgemini.dao;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.domain.FlatEntity;

public interface FlatDao extends CrudRepository<FlatEntity, Long> {

	FlatEntity findById(Long id);

	void removeById(Long id);

	// Double countSumTotalPriceFlatsPerClient(Long id);
	//
	// Double countAveragePriceFlatInBuilding(Long id);
	//
	// Integer countNumberFlatsInBuildingInStatus(Long id, Long idSta);
	//
	// List<ClientEntity> findCustomersWhoBuyMoreThanOneFlats();
	//
	// BuildingEntity findBuildingWherIsMostFreeFlats();
	//
	// List<FlatEntity> findAllFlatsToDisablePerson();

}
