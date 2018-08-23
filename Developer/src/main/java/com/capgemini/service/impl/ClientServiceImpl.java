package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.ClientDao;
import com.capgemini.domain.ClientEntity;
import com.capgemini.mappers.AddressMapper;
import com.capgemini.mappers.ClientMapper;
import com.capgemini.mappers.FlatMapper;
import com.capgemini.service.ClientService;
import com.capgemini.types.ClientTO;

@Service
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {

	private final ClientDao clientDao;
	private final ClientMapper clientMapper;
	private final AddressMapper addressMapper;
	private final FlatMapper flatMapper;

	@Autowired
	public ClientServiceImpl(ClientDao clientDao, ClientMapper clientMapper, AddressMapper addressMapper,
			FlatMapper flatMapper) {
		this.clientDao = clientDao;
		this.clientMapper = clientMapper;
		this.addressMapper = addressMapper;
		this.flatMapper = flatMapper;
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

		ClientEntity clientEntity = clientDao.findById(client.getId());
		clientEntity.setFirstName(client.getFirstName());
		clientEntity.setLastName(client.getLastName());

		if (client.getAddress() != null) {
			clientEntity.setAddress(addressMapper.mapToEntity(client.getAddress()));
		}
		clientEntity.setPhoneNumber(client.getPhoneNumber());
		clientEntity.setBookFlats(flatMapper.map2EntityLong(client.getBookFlats()));
		clientEntity.setBuyFlats(flatMapper.map2EntityLong(client.getBuyFlats()));
		clientEntity.setOwnerFlats(flatMapper.map2EntityLong(client.getOwnerFlats()));
		clientDao.save(clientEntity);
		return clientMapper.toClientTO(clientEntity);
	}

	@Override
	public ClientTO findById(Long id) {

		return clientMapper.toClientTO(clientDao.findById(id));

	}

	@Override
	public List<ClientTO> findByFirstNameAndLastName(String firstName, String lastName) {

		List<ClientEntity> findClients = clientDao.findByFirstNameAndLastName(firstName, lastName);
		return clientMapper.map2TOs(findClients);
	}

	@Override
	@Transactional(readOnly = false)
	public void removeById(Long id) {

		clientDao.removeById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void removeByFirstNameAndLastName(String firstName, String lastName) {

		clientDao.removeByFirstNameAndLastName(firstName, lastName);

	}

}
