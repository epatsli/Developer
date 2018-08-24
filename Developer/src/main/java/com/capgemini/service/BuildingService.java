package com.capgemini.service;

import java.util.List;

import com.capgemini.types.AddressMap;
import com.capgemini.types.BuildingTO;

public interface BuildingService {

	BuildingTO saveBuilding(BuildingTO building);

	BuildingTO updateBuilding(BuildingTO building);

	BuildingTO findById(Long id);

	List<BuildingTO> findByAddress(AddressMap address);

	void removeById(Long id);

	void removeByAddress(AddressMap address);

}
