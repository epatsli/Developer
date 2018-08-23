package com.capgemini.service.impl;

import java.util.List;

import javax.persistence.OptimisticLockException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.BuildingDao;
import com.capgemini.domain.Address;
import com.capgemini.domain.BuildingEntity;
import com.capgemini.mappers.AddressMapper;
import com.capgemini.mappers.BuildingMapper;
import com.capgemini.service.BuildingService;
import com.capgemini.types.BuildingTO;

@Service
@Transactional(readOnly = true)
public class BuildingServiceImpl implements BuildingService {

	private final BuildingDao buildingDao;
	private final BuildingMapper buildingMapper;
	private final AddressMapper addressMapper;

	@Autowired
	public BuildingServiceImpl(BuildingDao buildingDao, BuildingMapper buildingMapper, AddressMapper addressMapper) {
		this.buildingDao = buildingDao;
		this.buildingMapper = buildingMapper;
		this.addressMapper = addressMapper;
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

		if (buildingEntity.getVersion() != building.getVersion()) {
			throw new OptimisticLockException("Update can not be performed. Refresh page.");
		}

		buildingEntity.setDescription(building.getDescription());

		if (building.getAddress() != null) {
			buildingEntity.setAddress(addressMapper.mapToEntity(building.getAddress()));
		}
		buildingEntity.setNumberFloor(building.getNumberFloor());
		buildingEntity.setElevator(building.getElevator());
		buildingEntity.setNumberFlat(building.getNumberFlat());

		buildingDao.save(buildingEntity);

		return buildingMapper.toBuildingTO(buildingEntity);

	}

	@Override
	public BuildingTO findById(Long id) {

		return buildingMapper.toBuildingTO(buildingDao.findById(id));

	}

	@Override
	public List<BuildingTO> findByAddress(Address address) {
		List<BuildingEntity> findBuildings = buildingDao.findByAddress(address);
		return buildingMapper.map2TOs(findBuildings);

	}

	@Override
	@Transactional(readOnly = false)
	public void removeById(Long id) {
		buildingDao.removeById(id);

	}

	@Override
	@Transactional(readOnly = false)
	public void removeByAddress(Address address) {
		buildingDao.removeByAddress(address);
	}

}
