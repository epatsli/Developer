package com.capgemini.service;

import com.capgemini.types.FlatTO;

/**
 * Interface for service flat.
 *
 */
public interface FlatService {

	/**
	 * This method save flat.
	 * 
	 * @param flat
	 *            flat to save
	 * @return save flat
	 */
	FlatTO saveFlat(FlatTO flat);

	/**
	 * This method update flat.
	 * 
	 * @param flat
	 *            flat to update
	 * @return update flat
	 */
	FlatTO updateFlat(FlatTO flat);

	/**
	 * This method find flat by id.
	 * 
	 * @param id
	 *            index flat
	 * @return find flat
	 */
	FlatTO findById(Long id);

	/**
	 * This method remove flat by id.
	 * 
	 * @param id
	 *            index id
	 */
	void removeById(Long id);

}
