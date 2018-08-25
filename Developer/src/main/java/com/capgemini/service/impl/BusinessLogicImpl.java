package com.capgemini.service.impl;

/**
 * Class implements business logic service.
 *
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.BusinessLogicDao;
import com.capgemini.mappers.ClientMapper;
import com.capgemini.mappers.FlatMapper;
import com.capgemini.service.BusinessLogic;
import com.capgemini.types.ClientTO;
import com.capgemini.types.FlatTO;

@Service
@Transactional(readOnly = true)
public class BusinessLogicImpl implements BusinessLogic {

	private final BusinessLogicDao businessLogicDao;
	private final ClientMapper clientMapper;
	private final FlatMapper flatMapper;

	@Autowired
	public BusinessLogicImpl(BusinessLogicDao businessLogicDao, ClientMapper clientMapper, FlatMapper flatMapper) {
		this.businessLogicDao = businessLogicDao;
		this.clientMapper = clientMapper;
		this.flatMapper = flatMapper;
	}

	@Override
	public List<ClientTO> findClientWhoBuyOrBookFlat(FlatTO flat) {

		return clientMapper.map2TOs(businessLogicDao.findClientWhoBuyOrBookFlat(flatMapper.toFlatEntity(flat)));
	}

	@Override
	public ClientTO bookFlatByClient(ClientTO client, FlatTO flat) {

		return clientMapper.toClientTO(
				businessLogicDao.bookFlatByClient(clientMapper.toClientEntity(client), flatMapper.toFlatEntity(flat)));
	}

}
