package com.capgemini.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.StatusDao;
import com.capgemini.domain.StatusEntity;
import com.capgemini.mappers.FlatMapper;
import com.capgemini.mappers.StatusMapper;
import com.capgemini.service.StatusService;
import com.capgemini.types.StatusTO;

@Service
@Transactional(readOnly = true)
public class StatusServiceImpl implements StatusService {

	private final StatusDao statusDao;
	private final StatusMapper statusMapper;

	@Autowired
	private FlatMapper flatMapper;

	@Autowired
	public StatusServiceImpl(StatusDao statusDao, StatusMapper statusMapper) {
		this.statusDao = statusDao;
		this.statusMapper = statusMapper;
	}

	@Override
	@Transactional(readOnly = false)
	public StatusTO savetSatus(StatusTO status) {

		StatusEntity statusEntity = statusDao.save(statusMapper.toStatusEntity(status));
		return statusMapper.toStatusTO(statusEntity);
	}

	@Override
	@Transactional(readOnly = false)
	public StatusTO updateStatus(StatusTO status) {

		StatusEntity statusEntity = statusDao.findById(status.getId());
		statusEntity.setStatusName(status.getStatusName());
		statusEntity.setFlatsInStatus(flatMapper.map2EntityLong(status.getFlats()));
		statusDao.save(statusEntity);
		return statusMapper.toStatusTO(statusEntity);
	}

	@Override
	public StatusTO findById(Long id) {

		return statusMapper.toStatusTO(statusDao.findById(id));
	}

	@Override
	@Transactional(readOnly = false)
	public void removeById(Long id) {

		statusDao.removeById(id);
	}

}
