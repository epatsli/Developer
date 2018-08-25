package com.capgemini.service;

import com.capgemini.types.StatusTO;

/**
 * Interface for service status.
 *
 */
public interface StatusService {

	/**
	 * This method save status.
	 * 
	 * @param status
	 *            status to save
	 * @return save status
	 */
	StatusTO saveStatus(StatusTO status);

	/**
	 * This method update status.
	 * 
	 * @param status
	 *            status to update
	 * @return update status
	 */
	StatusTO updateStatus(StatusTO status);

	/**
	 * This method find status by id.
	 * 
	 * @param id
	 *            index status
	 * @return find status
	 */
	StatusTO findById(Long id);

	/**
	 * This method remove status by id.
	 * 
	 * @param id
	 *            index status
	 */
	void removeById(Long id);

}
