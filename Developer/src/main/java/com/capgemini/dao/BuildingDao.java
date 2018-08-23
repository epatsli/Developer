package com.capgemini.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.domain.Address;
import com.capgemini.domain.BuildingEntity;

//JpaRepository
public interface BuildingDao extends CrudRepository<BuildingEntity, Long>, BuildingDaoCustom {

	// void addBuildingToBuilding(BuildingEntity building);

	BuildingEntity findById(Long id);

	List<BuildingEntity> findByAddress(Address address);

	// void update(BuildingEntity idBuilding);
	void removeById(Long id);

	void removeByAddress(Address address);

}
