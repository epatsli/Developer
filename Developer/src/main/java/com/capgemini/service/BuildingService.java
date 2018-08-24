package com.capgemini.service;

import java.util.List;

import com.capgemini.types.BuildingTO;

public interface BuildingService {

	BuildingTO saveBuilding(BuildingTO building);

	BuildingTO updateBuilding(BuildingTO building);

	BuildingTO findById(Long id);

	List<BuildingTO> findByLocation(String location);

	void removeById(Long id);

	void removeByLocation(String location);

}
