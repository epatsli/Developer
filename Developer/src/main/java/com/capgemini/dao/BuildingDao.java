package com.capgemini.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.domain.Address;
import com.capgemini.domain.BuildingEntity;

//JpaRepository
public interface BuildingDao extends CrudRepository<BuildingEntity, Long> {

	BuildingEntity findById(Long id);

	List<BuildingEntity> findByAddress(Address address);

	void removeById(Long id);

	void removeByAddress(Address address);

}
