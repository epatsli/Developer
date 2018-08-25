package com.capgemini.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.domain.ClientEntity;

/**
 * Interface for Dao client.
 *
 */
public interface ClientDao extends CrudRepository<ClientEntity, Long> {

	/**
	 * This method find client by id.
	 * 
	 * @param id
	 *            index client
	 * @return client
	 */
	ClientEntity findById(Long id);

	/**
	 * This method return client by name and surname.
	 * 
	 * @param firstName
	 *            client name
	 * @param lastName
	 *            client surname
	 * @return list find clients
	 */
	List<ClientEntity> findByFirstNameAndLastName(String firstName, String lastName);

	/**
	 * This method remove client by id.
	 * 
	 * @param id
	 *            index client
	 */
	void removeById(Long id);

	/**
	 * This method remove client by name and surname.
	 * 
	 * @param firstName
	 *            client name
	 * @param lastName
	 *            client surname
	 */
	void removeByFirstNameAndLastName(String firstName, String lastName);

}
