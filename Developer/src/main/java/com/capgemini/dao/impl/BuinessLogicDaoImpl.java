package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.BusinessLogicDao;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.FlatEntity;
import com.capgemini.exception.ToMuchBookFlats;

@Repository
public class BuinessLogicDaoImpl extends AbstractDao<ClientEntity, Long> implements BusinessLogicDao {

	@Override
	public List<ClientEntity> findClientWhoBuyOrBookFlat(FlatEntity flat) {

		TypedQuery<ClientEntity> query = entityManager.createQuery(
				"SELECT client FROM ClientEntity client WHERE :flat MEMBER OF client.buyFlats", ClientEntity.class);
		query.setParameter("flat", flat);
		return query.getResultList();

	}

	@Override
	public Boolean bookFlatByClient(ClientEntity client, FlatEntity flat) {

		Long idClient = client.getId();

		TypedQuery<ClientEntity> query = entityManager
				.createQuery("SELECT client FROM ClientEntity client WHERE client.id=:id", ClientEntity.class);
		query.setParameter("id", idClient);
		List<ClientEntity> findClient = query.getResultList();
		List<FlatEntity> clientBookFlats = findClient.get(0).getBookFlats();

		if ((clientBookFlats != null) && (clientBookFlats.size() >= 3))
			throw new ToMuchBookFlats("You can't book this flat.");
		else {
			List<FlatEntity> bookFlats = client.getBookFlats();
			bookFlats.add(flat);
			client.setBookFlats(bookFlats);
			return true;
		}

	}

}
