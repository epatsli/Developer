package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.BuildingDao;
import com.capgemini.domain.BuildingEntity;
import com.capgemini.exception.MissingParameterException;
import com.capgemini.mappers.AddressMapper;
import com.capgemini.mappers.BuildingMapper;
import com.capgemini.mappers.FlatMapper;
import com.capgemini.service.BuildingService;
import com.capgemini.types.AddressMap;
import com.capgemini.types.BuildingTO;

@Service
@Transactional(readOnly = true)
public class BuildingServiceImpl implements BuildingService {

	private final BuildingDao buildingDao;
	private final BuildingMapper buildingMapper;
	private final AddressMapper addressMapper;
	private final FlatMapper flatMapper;

	@Autowired
	public BuildingServiceImpl(BuildingDao buildingDao, BuildingMapper buildingMapper, AddressMapper addressMapper,
			FlatMapper flatMapper) {
		this.buildingDao = buildingDao;
		this.buildingMapper = buildingMapper;
		this.addressMapper = addressMapper;
		this.flatMapper = flatMapper;
	}

	@Override
	@Transactional(readOnly = false)
	public BuildingTO saveBuilding(BuildingTO building) {

		BuildingEntity buildingEntity = buildingDao.save(buildingMapper.toBuildingEntity(building));
		return buildingMapper.toBuildingTO(buildingEntity);
	}

	@Override
	@Transactional(readOnly = false)
	public BuildingTO updateBuilding(BuildingTO building) {

		BuildingEntity buildingEntity = buildingDao.findById(building.getId());
		buildingEntity.setDescription(building.getDescription());

		if (building.getAddress() != null) {
			buildingEntity.setAddress(addressMapper.mapToEntity(building.getAddress()));
		}
		buildingEntity.setNumberFloor(building.getNumberFloor());
		buildingEntity.setElevator(building.getElevator());
		buildingEntity.setNumberFlat(building.getNumberFlat());
		buildingEntity.setFlats(flatMapper.map2EntityLong(building.getFlats()));
		buildingDao.save(buildingEntity);

		return buildingMapper.toBuildingTO(buildingEntity);
	}

	@Override
	public BuildingTO findById(Long id) {

		return buildingMapper.toBuildingTO(buildingDao.findById(id));
	}

	@Override
	public List<BuildingTO> findByAddress(AddressMap address) {

		if (address == null)
			throw new MissingParameterException("This address can not be empty.");

		List<BuildingEntity> findBuildings = buildingDao.findByAddress(addressMapper.mapToEntity(address));
		return buildingMapper.map2TOs(findBuildings);
	}

	@Override
	@Transactional(readOnly = false)
	public void removeById(Long id) {

		buildingDao.removeById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void removeByAddress(AddressMap address) {

		buildingDao.removeByAddress(addressMapper.mapToEntity(address));
	}

}
