package com.capgemini.dao.impl;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.BuildingRepository;
import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.StatusEntity;

@Repository
public class BuildingRepositoryImpl extends AbstractDao<BuildingEntity, Long> implements BuildingRepository {

	@Override
	public Long findCountFlatsInStatusInBuilding(BuildingEntity building, StatusEntity status) {

		TypedQuery<Long> query = entityManager.createQuery(
				"SELECT COUNT(f) FROM FlatEntity f WHERE ((:building = f.building) AND (:status = f.flatStatus))",
				Long.class);
		query.setParameter("building", building);
		query.setParameter("status", status);
		return query.getSingleResult();
	}

	@Override
	public Integer findBuildingWhichHaveMostEmptyFlats() {
		TypedQuery<Integer> query = entityManager.createQuery(
				"SELECT COUNT(f) FROM FlatEntity f WHERE :status = f.flatStatus GROUP BY f.building ORDER BY COUNT(f) DESC",
				Integer.class);
		query.setParameter("status", "Empty");
		query.setMaxResults(1);
		return null;
	}

}
