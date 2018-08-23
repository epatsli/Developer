package com.capgemini.dao;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.domain.Address;
import com.capgemini.domain.ClientEntity;

//import org.assertj.core.api.Assertions;
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ClientDaoTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ClientDao clientDao;

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateClientWithoutFirstName() {

		// given

		// when
		new ClientEntity().builder().withLastName("Kowal").withPhoneNumber("748785478").build();

		// then

	}

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateClientWithoutLastName() {

		// given

		// when
		new ClientEntity().builder().withFirstName("Jan").withPhoneNumber("445145214").build();

		// then

	}

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateClientWithoutPhoneNumber() {

		// given

		// when
		new ClientEntity().builder().withFirstName("Jan").withLastName("Kowal").build();

		// then

	}

	@Test
	public void shouldThrownException() {

		assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
			throw new RuntimeException("Incorrect parameter. This client can't be created.");
		}).withMessage("Incorrect parameter. This client can't be created.")
				.withStackTraceContaining("RuntimeException");
	}

	@Test
	public void shoulCreateClient() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientEntity client = new ClientEntity().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();

		// when
		ClientEntity saveClient = clientDao.save(client);

		// then
		assertEquals(client, saveClient);
	}

	@Test
	public void shoulCreateOnlyOneClient() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientEntity clientOne = new ClientEntity().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientEntity clientTwo = new ClientEntity().builder().withFirstName("Edward").withLastName("Bak")
				.withPhoneNumber("625451474").withAddress(address).build();
		ClientEntity clientThree = new ClientEntity().builder().withFirstName("Ola").withLastName("Ssak")
				.withPhoneNumber("785474547").withAddress(address).build();

		List<ClientEntity> clientList = new ArrayList<>();

		clientDao.save(clientOne);
		ClientEntity saveClient = clientDao.save(clientTwo);
		clientDao.save(clientThree);

		// when
		clientList.add(clientOne);
		clientList.add(clientTwo);
		clientList.add(clientThree);

		// then
		Assert.assertNotNull(clientList);
		Assert.assertEquals(3, clientList.size());

	}

	@Test
	public void shoulFindOnlyOneClient() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientEntity clientOne = new ClientEntity().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientEntity clientTwo = new ClientEntity().builder().withFirstName("Edward").withLastName("Bak")
				.withPhoneNumber("625451474").withAddress(address).build();
		ClientEntity clientThree = new ClientEntity().builder().withFirstName("Ola").withLastName("Ssak")
				.withPhoneNumber("785474547").withAddress(address).build();

		List<ClientEntity> clientList = new ArrayList<>();

		clientDao.save(clientOne);
		ClientEntity saveClient = clientDao.save(clientTwo);
		clientDao.save(clientThree);

		clientList.add(clientOne);
		clientList.add(clientTwo);
		clientList.add(clientThree);

		// when
		ClientEntity findClient = clientDao.findById(saveClient.getId());

		// then
		Assert.assertNotNull(findClient);
		assertEquals(saveClient, findClient);
	}

	@Test
	public void shoulCantFindClient() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientEntity clientOne = new ClientEntity().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientEntity clientTwo = new ClientEntity().builder().withFirstName("Edward").withLastName("Bak")
				.withPhoneNumber("625451474").withAddress(address).build();
		ClientEntity clientThree = new ClientEntity().builder().withFirstName("Ola").withLastName("Ssak")
				.withPhoneNumber("785474547").withAddress(address).build();

		List<ClientEntity> clientList = new ArrayList<>();

		clientDao.save(clientOne);
		ClientEntity saveClient = clientDao.save(clientTwo);
		clientDao.save(clientThree);

		clientList.add(clientOne);
		clientList.add(clientTwo);
		clientList.add(clientThree);

		// when
		List<ClientEntity> findClient = clientDao.findByFirstNameAndLastName("Szymon", "Hytry");

		// then

		Assert.assertEquals(0, findClient.size());
	}

	@Test
	public void shoulFindOnlyOneClientByFirstNameAndLastName() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientEntity clientOne = new ClientEntity().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientEntity clientTwo = new ClientEntity().builder().withFirstName("Edward").withLastName("Bak")
				.withPhoneNumber("625451474").withAddress(address).build();
		ClientEntity clientThree = new ClientEntity().builder().withFirstName("Ola").withLastName("Ssak")
				.withPhoneNumber("785474547").withAddress(address).build();

		List<ClientEntity> clientList = new ArrayList<>();

		clientDao.save(clientOne);
		ClientEntity saveClient = clientDao.save(clientTwo);
		clientDao.save(clientThree);

		clientList.add(clientOne);
		clientList.add(clientTwo);
		clientList.add(clientThree);

		// when
		List<ClientEntity> findClient = clientDao.findByFirstNameAndLastName(saveClient.getFirstName(),
				saveClient.getLastName());

		// then
		assertEquals(1, findClient.size());
		assertEquals(saveClient, findClient.get(0));
	}

	@Test
	public void shoulFindTwoClientByFirstNameAndLastName() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientEntity clientOne = new ClientEntity().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientEntity clientTwo = new ClientEntity().builder().withFirstName("Edward").withLastName("Bak")
				.withPhoneNumber("625451474").withAddress(address).build();
		ClientEntity clientThree = new ClientEntity().builder().withFirstName("Edward").withLastName("Bak")
				.withPhoneNumber("785474547").withAddress(address).build();

		clientDao.save(clientOne);
		clientDao.save(clientTwo);
		clientDao.save(clientThree);

		// when
		List<ClientEntity> findClient = clientDao.findByFirstNameAndLastName("Edward", "Bak");

		// then
		assertEquals(2, findClient.size());
		assertEquals(clientTwo, findClient.get(0));
		assertEquals(clientThree, findClient.get(1));

	}

	@Test
	public void shoulCantFindClientByFirstNameAndLastName() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientEntity clientOne = new ClientEntity().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientEntity clientTwo = new ClientEntity().builder().withFirstName("Edward").withLastName("Bak")
				.withPhoneNumber("625451474").withAddress(address).build();
		ClientEntity clientThree = new ClientEntity().builder().withFirstName("Edward").withLastName("Bak")
				.withPhoneNumber("785474547").withAddress(address).build();

		clientDao.save(clientOne);
		clientDao.save(clientTwo);
		clientDao.save(clientThree);

		// when
		List<ClientEntity> findClient = clientDao.findByFirstNameAndLastName("Milosz", "Ted");

		// then
		assertEquals(0, findClient.size());
	}

	@Test
	public void shoulCantFindClientFindByEmptyFirstNameAndLastName() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientEntity clientOne = new ClientEntity().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientEntity clientTwo = new ClientEntity().builder().withFirstName("Edward").withLastName("Bak")
				.withPhoneNumber("625451474").withAddress(address).build();
		ClientEntity clientThree = new ClientEntity().builder().withFirstName("Edward").withLastName("Bak")
				.withPhoneNumber("785474547").withAddress(address).build();

		clientDao.save(clientOne);
		clientDao.save(clientTwo);
		clientDao.save(clientThree);

		// when
		List<ClientEntity> findClient = clientDao.findByFirstNameAndLastName("", "");

		// then
		assertEquals(0, findClient.size());
	}
}
