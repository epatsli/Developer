package com.capgemini.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.service.BusinessLogic;
import com.capgemini.types.ClientTO;
import com.capgemini.types.FlatTO;

@Service
@Transactional(readOnly = true)
public class BusinessLogicImpl implements BusinessLogic {

	@Override
	public List<ClientTO> ffindClientWhoBuyOrBookFlat(FlatTO flat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean bookFlatByClient(ClientTO client, FlatTO flat) {
		// TODO Auto-generated method stub
		return null;
	}

}
