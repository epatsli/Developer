package com.capgemini.dao;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.domain.StatusEntity;

public interface StatusDao extends CrudRepository<StatusEntity, Long> {

	StatusEntity findById(Long id);

	void removeById(Long id);

	void removeByStatusName(String statusName);

}
