package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.SearchCriteriaDao;
import com.capgemini.domain.FlatEntity;
import com.capgemini.domain.SearchCriteriaEntity;

@Repository
public class SearchCriteriaDaoImpl extends AbstractDao<FlatEntity, Long> implements SearchCriteriaDao {

	@Override
	public List<FlatEntity> findFlatByArea(Double areaFlatFrom, Double areaFlatTo) {

		TypedQuery<FlatEntity> query = entityManager.createQuery(
				"SELECT f FROM FlatEntity f WHERE areaFlatFrom>=:f.areaFlat AND f.areaFlatTo<=:areaFlatTo AND (flatStatusFree OR flatStatusBook) MEMBER OF f.flatStatus",
				FlatEntity.class);
		query.setParameter("areaFlatFrom", areaFlatFrom);
		query.setParameter("areaFlatTo", areaFlatTo);

		query.setParameter("flatStatusFree", 1L);
		query.setParameter("flatStatusBook", 2L);
		return query.getResultList();

	}

	@Override
	public List<FlatEntity> findFlatByNumberRoom(Integer numberRoomFrom, Integer numberRoomTo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FlatEntity> findFlatByNumberBalconies(Integer numberBalconiesFrom, Integer numberBalconiesTo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FlatEntity> findFlatByCriteria(SearchCriteriaEntity searchCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
