package com.capgemini.service;

import java.util.List;

import com.capgemini.types.ClientTO;

public interface ClientService {

	ClientTO saveClient(ClientTO client);

	ClientTO updateClient(ClientTO client);

	ClientTO findById(Long id);

	List<ClientTO> findByFirstNameAndLastName(String firstName, String lastName);

	void removeById(Long id);

	void removeByFirstNameAndLastName(String firstName, String lastName);

}
