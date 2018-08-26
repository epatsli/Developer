package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.ClientEntity;

/**
 * Interface for client repository
 *
 */
public interface ClientRepository {

	/**
	 * This method return sum price for buying flats one client.
	 * 
	 * @param client
	 *            client for whom calculate amount of purchased flats
	 * @return amount of purchased flats
	 */
	Double findSumPriceFlatsBuyByOneClient(ClientEntity client);

	/**
	 * This method return list clients who buy more than one flat.
	 * 
	 * @return list clients who buy more than one flat
	 */
	List<ClientEntity> findAllClientWhoBuyMoreThanOneFlat();
}
