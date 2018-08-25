package com.capgemini.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.FlatDao;
import com.capgemini.domain.FlatEntity;
import com.capgemini.mappers.AddressMapper;
import com.capgemini.mappers.BuildingMapper;
import com.capgemini.mappers.ClientMapper;
import com.capgemini.mappers.FlatMapper;
import com.capgemini.mappers.StatusMapper;
import com.capgemini.service.FlatService;
import com.capgemini.types.FlatTO;

/**
 * Class implements flat service.
 *
 */
@Service
@Transactional(readOnly = true)
public class FlatServiceImpl implements FlatService {

	private final FlatDao flatDao;
	private final FlatMapper flatMapper;
	private final ClientMapper clientMapper;
	private final StatusMapper statusMapper;
	private final BuildingMapper buildingMapper;
	private final AddressMapper addressMapper;

	@Autowired
	public FlatServiceImpl(FlatDao flatDao, FlatMapper flatMapper, ClientMapper clientMapper, StatusMapper statusMapper,
			BuildingMapper buildingMapper, AddressMapper addressMapper) {
		this.flatDao = flatDao;
		this.flatMapper = flatMapper;
		this.clientMapper = clientMapper;
		this.statusMapper = statusMapper;
		this.buildingMapper = buildingMapper;
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
		flatEntity.setAreaFlat(flat.getAreaFlat());
		flatEntity.setNumberRoom(flat.getNumberRoom());
		flatEntity.setNumberBalconie(flat.getNumberBalconie());
		flatEntity.setFloor(flat.getFloor());
		flatEntity.setAddress(addressMapper.mapToEntity(flat.getAddress()));
		flatEntity.setFlatStatus(statusMapper.map2EntityLong(flat.getFlatStatus()));
		flatEntity.setBuilding(buildingMapper.map2EntityLong(flat.getBuilding()));
		flatEntity.setPrice(flat.getPrice());
		flatEntity.setClientBook(clientMapper.map2EntityLong(flat.getBookByClient()));
		flatEntity.setClientBuy(clientMapper.map2EntityLong(flat.getBuyByClient()));
		flatEntity.setOwner(clientMapper.map2EntityOnlyIdLong(flat.getOwner()));
		flatDao.save(flatEntity);
		return flatMapper.toFlatTO(flatEntity);
	}

	@Override
	public FlatTO findById(Long id) {

		return flatMapper.toFlatTO(flatDao.findById(id));
	}

	@Override
	@Transactional(readOnly = false)
	public void removeById(Long id) {

		flatDao.removeById(id);
	}

}
