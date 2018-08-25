package com.capgemini.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.ClientRepository;
import com.capgemini.domain.ClientEntity;

@Repository
public class ClientRepositoryImpl extends AbstractDao<ClientEntity, Long> implements ClientRepository {

	@Override
	public List<ClientEntity> findSumPriceFlatsBuyByOneClient(Long id) {

		// TypedQuery<ClientEntity> queryBuyFlat = entityManager.createQuery(
		// "SELECT client.clientBuy FROM ClientEntity client WHERE :id=id",
		// ClientEntity.class);
		// queryBuyFlat.setParameter("id", id);
		// ClientEntity buyFlats=queryBuyFlat.getSingleResult();
		//
		// List<FlatEntity> clientBuyFlats=buyFlats.getBuyFlats();
		List l = entityManager
				.createQuery(
						"SELECT f.owner, COUNT(c.id) FROM ClientEntity c, FlatEntity f WHERE c=f.owner GROUP BY f.owner")
				.getResultList();

		// TypedQuery<ClientEntity> query = entityManager.createQuery(
		// "SELECT c FROM ClientEntity c, FlatEntity f WHERE c.id=f.owner GROUP
		// BY f.owner", ClientEntity.class);
		//

		// query.setParameter("id", id);

		// "select c from ClientEntity c where c.id in " +
		// "(select c2.id from ClientEntity c2 where count(c.apartments) > 1",
		// ClientEntity.class);

		return l;

	}

}
