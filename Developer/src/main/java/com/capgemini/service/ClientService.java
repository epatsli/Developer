package com.capgemini.service;

import java.util.List;

import com.capgemini.types.ClientTO;

/**
 * Interface for client service.
 *
 */
public interface ClientService {

	/**
	 * This method save client.
	 * 
	 * @param client
	 *            client to save
	 * @return save client
	 */
	ClientTO saveClient(ClientTO client);

	/**
	 * This method update date client.
	 * 
	 * @param client
	 *            client to update
	 * @return update client
	 */
	ClientTO updateClient(ClientTO client);

	/**
	 * This method find client by id.
	 * 
	 * @param id
	 *            index client
	 * @return client
	 */
	ClientTO findById(Long id);

	/**
	 * This method return client by name and surname.
	 * 
	 * @param firstName
	 *            client name
	 * @param lastName
	 *            client surname
	 * @return list find clients
	 */
	List<ClientTO> findByFirstNameAndLastName(String firstName, String lastName);

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
