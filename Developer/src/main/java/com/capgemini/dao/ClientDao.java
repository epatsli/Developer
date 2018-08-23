package com.capgemini.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.domain.ClientEntity;

//JpaRepository
public interface ClientDao extends CrudRepository<ClientEntity, Long> {

	ClientEntity findById(Long id);

	List<ClientEntity> findByFirstNameAndLastName(String firstName, String lastName);

	void removeById(Long id);

	void removeByFirstNameAndLastName(String firstName, String lastName);

}
