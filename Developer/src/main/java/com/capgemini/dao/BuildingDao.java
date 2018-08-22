package com.capgemini.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.domain.BuildingEntity;

public interface BuildingDao extends JpaRepository<BuildingEntity, Long> {

	// void addBuildingToBuilding(BuildingEntity building);

	BuildingEntity findByIdBuilding(Long idBuilding);

	// void updateBuildingEntity(Long idBuilding);

	List<BuildingEntity> removeByIdBuilding(Long idBuilding);

}
