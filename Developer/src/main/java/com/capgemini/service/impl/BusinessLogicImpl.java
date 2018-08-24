package com.capgemini.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.service.BusinessLogic;
import com.capgemini.types.ClientTO;

@Service
@Transactional(readOnly = true)
public class BusinessLogicImpl implements BusinessLogic {

	@Override
	public ClientTO findClientWhoBuyFlat(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientTO findClientWhoBookFlat(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean bookFlatByClient(Long idClient) {
		// TODO Auto-generated method stub
		return null;
	}

}
