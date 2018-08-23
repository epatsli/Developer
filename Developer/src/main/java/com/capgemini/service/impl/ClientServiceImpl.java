package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.ClientDao;
import com.capgemini.domain.ClientEntity;
import com.capgemini.mappers.AddressMapper;
import com.capgemini.mappers.ClientMapper;
import com.capgemini.service.ClientService;
import com.capgemini.types.ClientTO;

@Service
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {

	private final ClientDao clientDao;
	private final ClientMapper clientMapper;
	private final AddressMapper addressMapper;

	@Autowired
	public ClientServiceImpl(ClientDao clientDao, ClientMapper clientMapper, AddressMapper addressMapper) {
		this.clientDao = clientDao;
		this.clientMapper = clientMapper;
		this.addressMapper = addressMapper;
	}

	@Override
	@Transactional(readOnly = false)
	public ClientTO saveClient(ClientTO client) {
		ClientEntity clientEntity = clientDao.save(clientMapper.toClientEntity(client));
		return clientMapper.toClientTO(clientEntity);
	}

	@Override
	@Transactional(readOnly = false)
	public ClientTO updateClient(ClientTO client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientTO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClientTO> findByFirstNameAndLastName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public void removeById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(readOnly = false)
	public void removeByFirstNameAndLastName(String firstName, String lastName) {
		// TODO Auto-generated method stub

	}

}
