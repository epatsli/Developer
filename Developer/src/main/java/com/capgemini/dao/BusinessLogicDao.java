package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.FlatEntity;

/**
 * Interface implements business logic.
 *
 */
public interface BusinessLogicDao extends Dao<ClientEntity, Long> {

	/**
	 * This method find clients who buy or book flat.
	 * 
	 * @param flat
	 *            flat, for which we check whether it was bought or booked
	 * @return list client
	 */
	List<ClientEntity> findClientWhoBuyOrBookFlat(FlatEntity flat);

	/**
	 * This method book flats. If
	 * 
	 * @param client
	 *            client who want book flat
	 * @param flat
	 *            flat which book
	 * @return client who book flat
	 * @exception if
	 *                client have 3 book flats, he cannot book next flat
	 */
	ClientEntity bookFlatByClient(ClientEntity client, FlatEntity flat);
}
