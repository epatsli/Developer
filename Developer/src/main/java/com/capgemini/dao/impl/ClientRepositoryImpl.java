package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.ClientRepository;
import com.capgemini.domain.ClientEntity;

@Repository
public class ClientRepositoryImpl extends AbstractDao<ClientEntity, Long> implements ClientRepository {

	@Override
	public Double findSumPriceFlatsBuyByOneClient(ClientEntity client) {

		TypedQuery<Double> queryBuyFlat = entityManager
				.createQuery("SELECT SUM(f.price) FROM FlatEntity f WHERE :client = f.owner", Double.class);
		queryBuyFlat.setParameter("client", client);
		return queryBuyFlat.getSingleResult();
	}

	@Override
	public List<ClientEntity> findAllClientWhoBuyMoreThanOneFlat() {

		TypedQuery<ClientEntity> query = entityManager.createQuery(
				"SELECT c FROM ClientEntity c LEFT JOIN com.capgemini.domain.FlatEntity f WHERE c.id = f.owner GROUP BY f.owner HAVING COUNT(f.owner)>1",
				ClientEntity.class);

		return query.getResultList();
	}

}
