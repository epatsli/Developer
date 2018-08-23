package com.capgemini.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

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

		// when
		new ClientTO().builder().withLastName("Kowal").withPhoneNumber("748785478").build();

		// then

	}

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateClientWithoutLastName() {

		// given

		// when
		new ClientTO().builder().withFirstName("Jan").withPhoneNumber("445145214").build();

		// then

	}

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateClientWithoutPhoneNumber() {

		// given

		// when
		new ClientTO().builder().withFirstName("Jan").withLastName("Kowal").build();

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
		List<Long> bookFlats = new ArrayList<>();
		List<Long> buyFlats = new ArrayList<>();
		List<Long> ownerFlats = new ArrayList<>();

		ClientTO client = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).withBookFlats(bookFlats).withBuyFlats(buyFlats)
				.withOwnerFlats(ownerFlats).build();

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

		String lastName = "Kowalski";
		saveClient.setLastName(lastName);

		// when
		ClientTO updateClientd = clientService.updateClient(saveClient);

		// then
		assertEquals("Jan", updateClientd.getFirstName());
		assertEquals("Kowalski", updateClientd.getLastName());
		assertEquals("74547454", updateClientd.getPhoneNumber());
	}

	@Test
	public void shouldTesVersion() {

		// given
		String lastName = "Kowalski";
		AddressMap adress = new AddressMap().builder().withCity("Poznan").withPostCode("52-111").withHouseNumber("14/2")
				.withStreet("Biala").build();
		ClientTO client = new ClientTO().builder().withFirstName("Michal").withLastName("Tracz").withAddress(adress)
				.withPhoneNumber("787474787").build();
		ClientTO saveClient = clientService.saveClient(client);
		Long versionTest = 1L;

		// when
		saveClient.setLastName(lastName);
		Long version1 = clientService.findById(saveClient.getId()).getVersion();
		clientService.updateClient(saveClient);
		Long version2 = clientService.findById(saveClient.getId()).getVersion();

		// then
		assertThat(version1).isNotEqualTo(version2);
		assertEquals(versionTest, version2);
	}

}
