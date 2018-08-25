package com.capgemini.dao;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.domain.FlatEntity;

/**
 * Interface for dao flat.
 *
 */
public interface FlatDao extends CrudRepository<FlatEntity, Long> {

	/**
	 * This method find flat by id.
	 * 
	 * @param id
	 *            index flat
	 * @return find flat
	 */
	FlatEntity findById(Long id);

	/**
	 * This method remove flat by id.
	 * 
	 * @param id
	 *            index id
	 */
	void removeById(Long id);
}
