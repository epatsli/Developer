package com.capgemini.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.types.AddressMap;
import com.capgemini.types.ClientTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ClientServiceTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ClientService clientService;

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateClientWithoutFirstName() {

		// given
		ClientTO client = new ClientTO().builder().withLastName("Kowal").withPhoneNumber("748785478").build();

		// when
		clientService.saveClient(client);

		// then

	}

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateClientWithoutLastName() {

		// given
		ClientTO client = new ClientTO().builder().withFirstName("Jan").withPhoneNumber("445145214").build();

		// when
		clientService.saveClient(client);

		// then

	}

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateClientWithoutPhoneNumber() {

		// given
		ClientTO client = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal").build();

		// when
		clientService.saveClient(client);

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
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO client = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();

		// when
		ClientTO saveClient = clientService.saveClient(client);

		// then
		assertEquals("Jan", saveClient.getFirstName());
		assertEquals("Kowal", saveClient.getLastName());
		assertEquals("74547454", saveClient.getPhoneNumber());
	}

	@Test
	public void shoulUpdateClient() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientBeforeUpdate = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClient = clientService.saveClient(clientBeforeUpdate);

		ClientTO clientAfterUpdate = new ClientTO().builder().withFirstName("Jan").withLastName("Michnik")
				.withPhoneNumber("74547454").withAddress(address).build();

		String lastName = "Kowalski";
		saveClient.setLastName(lastName);
		ClientTO updateClientd = clientService.updateClient(saveClient);
		// when
		// ClientTO updateClient =
		// clientService.updateClient(clientAfterUpdate);

		// then
		assertEquals("Jan", updateClientd.getFirstName());
		assertEquals("Kowalski", updateClientd.getLastName());
		assertEquals("74547454", updateClientd.getPhoneNumber());
	}

}
