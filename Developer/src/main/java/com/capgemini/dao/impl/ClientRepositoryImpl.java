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
				.createQuery("SELECT SUM(f.price) FROM FlatEntity f WHERE :client MEMBER OF f.clientBuy", Double.class);
		queryBuyFlat.setParameter("client", client);
		return queryBuyFlat.getSingleResult();
	}

	@Override
	public List<ClientEntity> findAllClientWhoBuyMoreThanOneFlat() {

		TypedQuery<ClientEntity> query = entityManager
				.createQuery("SELECT c FROM ClientEntity c WHERE size(c.buyFlats)>1", ClientEntity.class);

		return query.getResultList();
	}

}
