package com.capgemini.dao.impl;

import java.util.List;

import com.capgemini.dao.FlatDao;
import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.FlatEntity;

public class FlatDaoImpl extends AbstractDao<FlatEntity, Long> implements FlatDao {

	@Override
	public Double countSumTotalPriceFlatsPerClient(Long idClient) {
		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public Double countAveragePriceFlatInBuilding(Long idBuilding) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countNumberFlatsInBuildingInStatus(Long idBuilding, Long idStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClientEntity> findCustomersWhoBuyMoreThanOneFlats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BuildingEntity findBuildingWherIsMostFreeFlats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FlatEntity> findAllFlatsToDisablePerson() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlatEntity findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FlatEntity> findByNumberRooms(Integer numberRooms) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeById(Long id) {
		// TODO Auto-generated method stub

	}

}
