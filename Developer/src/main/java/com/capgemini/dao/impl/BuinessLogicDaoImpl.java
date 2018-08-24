package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.BusinessLogicDao;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.FlatEntity;

@Repository
public class BuinessLogicDaoImpl extends AbstractDao<ClientEntity, Long> implements BusinessLogicDao {

	@Override
	public List<ClientEntity> findClientWhoBuyOrBookFlat(FlatEntity flat) {

		TypedQuery<ClientEntity> query = entityManager.createQuery(
				"SELECT client FROM ClientEntity client WHERE :flat MEMBER OF client.buyFlats OR :flat MEMBER OF client.bookFlats)",
				ClientEntity.class);
		query.setParameter("flat", flat);
		return query.getResultList();

	}

	@Override
	public Boolean bookFlatByClient(Long idClient) {
		// TODO Auto-generated method stub
		return null;
	}

}
