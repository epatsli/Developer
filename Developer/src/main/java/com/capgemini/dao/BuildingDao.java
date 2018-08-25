package com.capgemini.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.domain.BuildingEntity;

/**
 * Interface for dao buildings.
 *
 */
public interface BuildingDao extends CrudRepository<BuildingEntity, Long> {

	/**
	 * This method finds a building by id.
	 * 
	 * @param id
	 *            index building
	 * @return building
	 */
	BuildingEntity findById(Long id);

	/**
	 * This method finds a list of buildings by location.
	 * 
	 * @param location
	 *            location buildings
	 * @return list buildings
	 */
	List<BuildingEntity> findByLocation(String location);

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
