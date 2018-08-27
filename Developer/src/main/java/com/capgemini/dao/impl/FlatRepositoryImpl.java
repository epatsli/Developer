package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.FlatRepository;
import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.FlatEntity;

@Repository
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

		TypedQuery<FlatEntity> query = entityManager.createQuery(
				"SELECT f from FlatEntity f JOIN f.building b WHERE ((b.elevator=TRUE) OR (f.floor=:floor))",
				FlatEntity.class);
		query.setParameter("floor", new Integer(0));
		return query.getResultList();

	}

}
