package com.capgemini.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.domain.BuildingEntity;

@Repository
public interface BuildingDao extends JpaRepository<BuildingEntity, Long>, BuildingDaoCustom {

	// void addBuildingToBuilding(BuildingEntity building);

	BuildingEntity findByIdBuilding(Long idBuilding);

	// void update(BuildingEntity idBuilding);

	List<BuildingEntity> removeByIdBuilding(Long idBuilding);

}
