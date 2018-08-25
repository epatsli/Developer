package com.capgemini.service;

import java.util.List;

import com.capgemini.types.ClientTO;
import com.capgemini.types.FlatTO;

/**
 * Interface implements business logic.
 *
 */
public interface BusinessLogic {

	/**
	 * This method find clients who buy or book flat.
	 * 
	 * @param flat
	 *            flat, for which we check whether it was bought or booked
	 * @return list client
	 */
	List<ClientTO> findClientWhoBuyOrBookFlat(FlatTO flat);

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
	ClientTO bookFlatByClient(ClientTO client, FlatTO flat);

}
