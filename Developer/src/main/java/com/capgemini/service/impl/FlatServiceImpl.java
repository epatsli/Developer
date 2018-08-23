package com.capgemini.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.service.FlatService;
import com.capgemini.types.FlatTO;

@Service
@Transactional(readOnly = true)
public class FlatServiceImpl implements FlatService {

	@Override
	public FlatTO saveClient(FlatTO flat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlatTO updateClient(FlatTO flat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlatTO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FlatTO> findByNumberRooms(Integer numberRooms) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeById(Long id) {
		// TODO Auto-generated method stub

	}

}
