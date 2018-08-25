package com.capgemini.service;

import java.util.List;

import com.capgemini.types.BuildingTO;

/**
 * Interface for servis building.
 *
 */
public interface BuildingService {

	/**
	 * This method save building.
	 * 
	 * @param building
	 *            building which was save
	 * @return save building
	 */
	BuildingTO saveBuilding(BuildingTO building);

	/**
	 * @param building
	 *            building which was update
	 * @return update building
	 */
	BuildingTO updateBuilding(BuildingTO building);

	/**
	 * This method finds a building by id.
	 * 
	 * @param id
	 *            index building
	 * @return building
	 */
	BuildingTO findById(Long id);

	/**
	 * This method finds a list of buildings by location.
	 * 
	 * @param location
	 *            location buildings
	 * @return list buildings
	 */
	List<BuildingTO> findByLocation(String location);

	/**
	 * This method remove building by id.
	 * 
	 * @param id
	 *            index building
	 */
	void removeById(Long id);

	/**
	 * This method remove building after location.
	 * 
	 * @param location
	 *            building location
	 */
	void removeByLocation(String location);

}
