package com.capgemini.dao;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.domain.StatusEntity;

/**
 * Interface for dao status.
 *
 */
public interface StatusDao extends CrudRepository<StatusEntity, Long> {

	/**
	 * This method find status by id.
	 * 
	 * @param id
	 *            index status
	 * @return find status
	 */
	StatusEntity findById(Long id);

	/**
	 * This method remove status by id.
	 * 
	 * @param id
	 *            index status
	 */
	void removeById(Long id);

}
