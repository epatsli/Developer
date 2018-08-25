package com.capgemini.dao.impl;

import java.util.ArrayList;
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
				"SELECT client FROM ClientEntity client WHERE :flat MEMBER OF buyFlats OR :flat MEMBER OF bookFlats",
				ClientEntity.class);
		query.setParameter("flat", flat);
		return query.getResultList();

	}

	@Override
	public ClientEntity bookFlatByClient(ClientEntity client, FlatEntity flat) {

		Long idClient = client.getId();

		TypedQuery<ClientEntity> query = entityManager
				.createQuery("SELECT client FROM ClientEntity client WHERE client.id=:id", ClientEntity.class);
		query.setParameter("id", idClient);
		List<ClientEntity> clients = query.getResultList();
		ClientEntity findClient = clients.get(0);
		List<FlatEntity> clientBookFlats = findClient.getBookFlats();

		if ((findClient != null) && (clientBookFlats.size() >= 3))
			throw new ToMuchBookFlats("You can't book this flat.");
		else if (clientBookFlats == null) {
			List<FlatEntity> bookFlats = new ArrayList<>();
			bookFlats.add(flat);
			findClient.setBookFlats(bookFlats);
			return update(findClient);
		} else {
			List<FlatEntity> bookFlats = clientBookFlats;
			bookFlats.add(flat);
			findClient.setBookFlats(bookFlats);
			return update(findClient);
		}

	}

}
