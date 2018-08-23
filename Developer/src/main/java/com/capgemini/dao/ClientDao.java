package com.capgemini.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.domain.ClientEntity;

public interface ClientDao extends JpaRepository<ClientEntity, Long> {

	ClientEntity findByIdClient(Long idClient);

	// void update(BuildingEntity idBuilding);

	List<ClientEntity> removeByIdClient(Long idClient);

}
