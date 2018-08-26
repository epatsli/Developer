package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.FlatEntity;

/**
 * Interface for flat repository
 *
 */
public interface FlatRepository {

	/**
	 * This method find average flat price in building.
	 * 
	 * @param building
	 *            building which find average flat price
	 * @return average flat price in building
	 */
	Double findAverageFlatPriceInBuilding(BuildingEntity building);

	/**
	 * This method find all flats which are suitable for disabilities people.
	 * 
	 * @return list flats which are suitable for disabilities people
	 */
	List<FlatEntity> findAllFlatSuitableForDisabilitiesPeople();

}
