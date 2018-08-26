package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import com.capgemini.dao.FlatRepository;
import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.FlatEntity;

public class FlatRepositoryImpl extends AbstractDao<FlatEntity, Long> implements FlatRepository {

	@Override
	public Double findAverageFlatPriceInBuilding(BuildingEntity building) {

		TypedQuery<Double> query = entityManager
				.createQuery("SELECT AVG(f.price) from FlatEntity f WHERE :building = f.building", Double.class);
		query.setParameter("building", building);
		return query.getSingleResult();
	}

	@Override
	public List<FlatEntity> findAllFlatSuitableForDisabilitiesPeople() {
		// TODO Auto-generated method stub
		return null;
	}

}
