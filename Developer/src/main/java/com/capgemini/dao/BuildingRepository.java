package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.StatusEntity;

/**
 * Interface for building repository
 *
 */
public interface BuildingRepository {

	/**
	 * This method return count flats which have status and are located in
	 * building.
	 * 
	 * @param building
	 *            building which search flats
	 * @param status
	 *            status how should have search flats
	 * @return count flats which have status and are located in building
	 */
	Long findCountFlatsInStatusInBuilding(BuildingEntity building, StatusEntity status);

	/**
	 * This method return building which have most empty flat.
	 * 
	 * @return building which have most empty flats
	 */
	List<BuildingEntity> findBuildingWhichHaveMostEmptyFlats();
}
