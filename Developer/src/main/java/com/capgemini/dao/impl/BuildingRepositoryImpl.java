package com.capgemini.dao.impl;

import java.util.List;

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
	public List<BuildingEntity> findBuildingWhichHaveMostEmptyFlats() {
		TypedQuery<BuildingEntity> query = entityManager.createQuery(
				"SELECT b FROM FlatEntity f JOIN f.building b WHERE f.flatStatus =:status GROUP BY f.building ORDER BY COUNT(f) DESC",
				BuildingEntity.class);
		query.setParameter("status", "Empty");
		return query.getResultList();
	}

}
