package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.FlatEntity;

public interface BusinessLogicDao extends Dao<ClientEntity, Long> {

	List<ClientEntity> findClientWhoBuyOrBookFlat(FlatEntity flat);

	ClientEntity bookFlatByClient(ClientEntity client, FlatEntity flat);
}
