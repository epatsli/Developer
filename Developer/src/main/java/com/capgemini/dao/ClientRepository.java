package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.ClientEntity;

public interface ClientRepository {

	List<ClientEntity> findSumPriceFlatsBuyByOneClient(Long id);
}
