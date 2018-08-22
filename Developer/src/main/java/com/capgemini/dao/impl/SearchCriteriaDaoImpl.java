package com.capgemini.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
				"SELECT f FROM FlatEntity f WHERE (flatStatusFree MEMBER OF f.flatStatus OR flatStatusBook MEMBER OF f.flatStatus) AND areaFlatFrom:>=f.areaFlat AND f.areaFlat<=:areaFlatTo ",
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

		TypedQuery<FlatEntity> query = entityManager.createQuery(
				"SELECT f FROM FlatEntity f WHERE (flatStatusFree MEMBER OF f.flatStatus OR flatStatusBook MEMBER OF f.flatStatus) AND :numberRoomFrom>=f.numberRoom AND f.numberRoom<=:numberRoomTo",
				FlatEntity.class);
		query.setParameter("numberRoomFrom", numberRoomFrom);
		query.setParameter("numberRoomTo", numberRoomTo);

		query.setParameter("flatStatusFree", 1L);
		query.setParameter("flatStatusBook", 2L);
		return query.getResultList();

	}

	@Override
	public List<FlatEntity> findFlatByNumberBalconies(Integer numberBalconiesFrom, Integer numberBalconiesTo) {
		// TODO Auto-generated method stub
		TypedQuery<FlatEntity> query = entityManager.createQuery(
				"SELECT f FROM FlatEntity f WHERE (flatStatusFree MEMBER OF f.flatStatus OR flatStatusBook MEMBER OF f.flatStatus) AND :numberBalconiesFrom>=f.numberBalconie AND f.numberBalconie<=:numberBalconiesTo ",
				FlatEntity.class);
		query.setParameter("numberBalconiesFrom", numberBalconiesFrom);
		query.setParameter("numberBalconiesTo", numberBalconiesTo);

		query.setParameter("flatStatusFree", 1L);
		query.setParameter("flatStatusBook", 2L);
		return query.getResultList();
	}

	@Override
	public List<FlatEntity> findFlatByCriteria(SearchCriteriaEntity searchCriteria) {
		// TODO Auto-generated method stub

		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(
				"SELECT f FROM FlatEntity f WHERE (flatStatusFree MEMBER OF f.flatStatus OR flatStatusBook MEMBER OF f.flatStatus) AND ");

		parameters.put("flatStatusFree", 1L);
		parameters.put("flatStatusBook", 2L);

		if (searchCriteria.getAreaFlatFrom() != null) {
			stringBuilder.append("areaFlatFrom:>=areaFlat AND ");
			FlatEntity areaFlatFrom = entityManager.getReference(FlatEntity.class, searchCriteria.getAreaFlatFrom());
			parameters.put("areaFlatFrom", areaFlatFrom);
		}

		if (searchCriteria.getAreaFlatTo() != null) {
			stringBuilder.append("areaFlat<=:areaFlatTo AND");
			FlatEntity areaFlatTo = entityManager.getReference(FlatEntity.class, searchCriteria.getAreaFlatTo());
			parameters.put("areaFlatTo", areaFlatTo);
		}

		if (searchCriteria.getNumberRoomFrom() != null) {
			stringBuilder.append(":numberRoomFrom>=numberRoom AND ");
			FlatEntity numberRoomFrom = entityManager.getReference(FlatEntity.class,
					searchCriteria.getNumberRoomFrom());
			parameters.put("numberRoomFrom", numberRoomFrom);
		}

		if (searchCriteria.getNumberRoomTo() != null) {
			stringBuilder.append("numberRoom<=:numberRoomTo AND  ");
			FlatEntity numberRoomTo = entityManager.getReference(FlatEntity.class, searchCriteria.getNumberRoomTo());
			parameters.put("numberRoomTo", numberRoomTo);
		}

		if (searchCriteria.getNumberBalconiesFrom() != null) {
			stringBuilder.append(":numberBalconiesFrom>=numberBalconie AND");
			FlatEntity numberBalconiesFrom = entityManager.getReference(FlatEntity.class,
					searchCriteria.getNumberBalconiesFrom());
			parameters.put("numberBalconiesFrom", numberBalconiesFrom);
		}

		if (searchCriteria.getNumberBalconiesTo() != null) {
			stringBuilder.append("numberBalconie<=:numberBalconiesTo");
			FlatEntity numberBalconiesTo = entityManager.getReference(FlatEntity.class,
					searchCriteria.getNumberBalconiesTo());
			parameters.put("numberBalconiesTo", numberBalconiesTo);
		}

		TypedQuery<FlatEntity> query = entityManager.createQuery(stringBuilder.toString(), FlatEntity.class);
		parameters.keySet().forEach(q -> query.setParameter(q, parameters.get(q)));
		return query.getResultList();

	}

}
