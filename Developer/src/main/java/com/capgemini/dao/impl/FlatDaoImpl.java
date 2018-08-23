package com.capgemini.dao.impl;

import java.util.List;

import com.capgemini.dao.FlatDao;
import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.FlatEntity;

public class FlatDaoImpl implements FlatDao {

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

	@Override
	public <S extends FlatEntity> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends FlatEntity> Iterable<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlatEntity findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<FlatEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<FlatEntity> findAll(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(FlatEntity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends FlatEntity> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
