package com.capgemini.service.impl;

import java.util.List;

import javax.persistence.OptimisticLockException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.FlatDao;
import com.capgemini.domain.FlatEntity;
import com.capgemini.mappers.AddressMapper;
import com.capgemini.mappers.FlatMapper;
import com.capgemini.service.FlatService;
import com.capgemini.types.FlatTO;

@Service
@Transactional(readOnly = true)
public class FlatServiceImpl implements FlatService {

	private final FlatDao flatDao;
	private final FlatMapper flatMapper;
	private final AddressMapper addressMapper;

	@Autowired
	public FlatServiceImpl(FlatDao flatDao, FlatMapper flatMapper, AddressMapper addressMapper) {
		this.flatDao = flatDao;
		this.flatMapper = flatMapper;
		this.addressMapper = addressMapper;
	}

	@Override
	@Transactional(readOnly = false)
	public FlatTO saveFlat(FlatTO flat) {

		FlatEntity flatEntity = flatDao.save(flatMapper.toFlatEntity(flat));
		return flatMapper.toFlatTO(flatEntity);
	}

	@Override
	@Transactional(readOnly = false)
	public FlatTO updateFlat(FlatTO flat) {

		FlatEntity flatEntity = flatDao.findById(flat.getId());

		if (flatEntity.getVersion() != flat.getVersion()) {
			throw new OptimisticLockException("Update can not be performed. Refresh page.");
		}

		flatEntity.setAreaFlat(flat.getAreaFlat());
		flatEntity.setNumberRoom(flat.getNumberRoom());
		flatEntity.setNumberBalconie(flat.getNumberBalconie());
		flatEntity.setFloor(flat.getFloor());
		flatEntity.setAddress(addressMapper.mapToEntity(flat.getAddress()));
		// flatEntity.setFlatStatus(flat.getFlatStatus());

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlatTO findById(Long id) {

		return flatMapper.toFlatTO(flatDao.findById(id));
	}

	@Override
	public List<FlatTO> findByNumberRooms(Integer numberRooms) {

		List<FlatEntity> findFlats = flatDao.findByNumberRooms(numberRooms);
		return flatMapper.map2TOs(findFlats);
	}

	@Override
	@Transactional(readOnly = false)
	public void removeById(Long id) {

		flatDao.removeById(id);
	}

}
