package com.capgemini.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

import com.capgemini.exception.IncorrectParameterException;
import com.capgemini.types.AddressMap;
import com.capgemini.types.ClientTO;
import com.capgemini.types.FlatTO;
import com.capgemini.types.StatusTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ClientServiceTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ClientService clientService;

	@Autowired
	private FlatService flatService;

	@Autowired
	private StatusService statusService;

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

	@Test(expected = IncorrectParameterException.class)
	public void shoulCantCreateClientWithEmptyAddress() {

		// given

		// when
		AddressMap address = new AddressMap().builder().build();
		List<Long> bookFlats = new ArrayList<>();
		List<Long> buyFlats = new ArrayList<>();
		List<Long> ownerFlats = new ArrayList<>();
		ClientTO client = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).withBookFlats(bookFlats).withBuyFlats(buyFlats)
				.withOwnerFlats(ownerFlats).build();

		// then

	}

	@Test(expected = IncorrectParameterException.class)
	public void shoulCantCreateClientWithNullAddress() {

		// given
		List<Long> bookFlats = new ArrayList<>();
		List<Long> buyFlats = new ArrayList<>();
		List<Long> ownerFlats = new ArrayList<>();

		ClientTO client = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(null).withBookFlats(bookFlats).withBuyFlats(buyFlats)
				.withOwnerFlats(ownerFlats).build();

		// when
		ClientTO saveClient = clientService.saveClient(client);

		// then

	}

	@Test
	public void shoulUpdateClientWithOneParameter() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientBeforeUpdate = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClient = clientService.saveClient(clientBeforeUpdate);

		String lastName = "Kowalski";
		saveClient.setLastName(lastName);

		// when
		ClientTO updateClient = clientService.updateClient(saveClient);

		// then
		assertEquals("Jan", updateClient.getFirstName());
		assertEquals("Kowalski", updateClient.getLastName());
		assertEquals("74547454", updateClient.getPhoneNumber());
	}

	@Test
	public void shoulUpdateClientWithMoreParameters() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientBeforeUpdate = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClient = clientService.saveClient(clientBeforeUpdate);

		String firstName = "Adam";
		saveClient.setFirstName(firstName);
		String lastName = "Tyka";
		saveClient.setLastName(lastName);
		String phoneNumber = "78945612";
		saveClient.setPhoneNumber(phoneNumber);
		AddressMap newAddress = new AddressMap().builder().withStreet("Mila").withHouseNumber("9").withCity("Plock")
				.withPostCode("64-254").build();
		saveClient.setAddress(newAddress);

		StatusTO status = new StatusTO().builder().withStatusName("Reserved").build();
		StatusTO saveStatus = statusService.saveStatus(status);
		FlatTO flatOne = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withNumberRoom(new Integer(8)).withAddress(address).build();
		FlatTO saveFlatOne = flatService.saveFlat(flatOne);
		FlatTO flatTwo = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatTwo = flatService.saveFlat(flatTwo);
		List<Long> bookFlats = new ArrayList<>();
		bookFlats.add(saveFlatOne.getId());
		bookFlats.add(saveFlatTwo.getId());
		saveClient.setBookFlats(bookFlats);
		List<Long> buyFlats = new ArrayList<>();
		buyFlats.add(saveFlatTwo.getId());
		saveClient.setBuyFlats(buyFlats);
		List<Long> ownerFlats = new ArrayList<>();
		ownerFlats.add(saveFlatTwo.getId());
		saveClient.setOwnerFlats(ownerFlats);

		// when
		ClientTO updateClient = clientService.updateClient(saveClient);

		// then
		assertEquals("Adam", updateClient.getFirstName());
		assertEquals("Tyka", updateClient.getLastName());
		assertEquals("78945612", updateClient.getPhoneNumber());
		assertEquals(newAddress.getCity(), updateClient.getAddress().getCity());
		assertEquals(newAddress.getStreet(), updateClient.getAddress().getStreet());
		assertEquals(bookFlats, updateClient.getBookFlats());
		assertEquals(buyFlats, updateClient.getBuyFlats());
		assertEquals(ownerFlats, updateClient.getOwnerFlats());
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

	@Test
	public void shoulFindClientByIdWhenFindingClientWasFirstSave() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);
		ClientTO clientTwo = new ClientTO().builder().withFirstName("Igor").withLastName("Nowak")
				.withPhoneNumber("125463251").withAddress(address).build();
		ClientTO saveClientTwo = clientService.saveClient(clientTwo);
		ClientTO clientThree = new ClientTO().builder().withFirstName("Iza").withLastName("Bak")
				.withPhoneNumber("852321562").withAddress(address).build();
		ClientTO saveClientThree = clientService.saveClient(clientThree);

		// when
		ClientTO findClient = clientService.findById(saveClientOne.getId());

		// then
		assertEquals("Jan", findClient.getFirstName());
		assertEquals("Kowal", findClient.getLastName());
		assertEquals("74547454", findClient.getPhoneNumber());
	}

	@Test
	public void shoulFindClientByIdWhenFindingClientWasInsideSave() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);
		ClientTO clientTwo = new ClientTO().builder().withFirstName("Igor").withLastName("Nowak")
				.withPhoneNumber("125463251").withAddress(address).build();
		ClientTO saveClientTwo = clientService.saveClient(clientTwo);
		ClientTO clientThree = new ClientTO().builder().withFirstName("Iza").withLastName("Bak")
				.withPhoneNumber("852321562").withAddress(address).build();
		ClientTO saveClientThree = clientService.saveClient(clientThree);

		// when
		ClientTO findClient = clientService.findById(saveClientTwo.getId());

		// then
		assertEquals("Igor", findClient.getFirstName());
		assertEquals("Nowak", findClient.getLastName());
		assertEquals("125463251", findClient.getPhoneNumber());
	}

	@Test
	public void shoulFindClientByIdWhenFindingClientWasLastSave() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);
		ClientTO clientTwo = new ClientTO().builder().withFirstName("Igor").withLastName("Nowak")
				.withPhoneNumber("125463251").withAddress(address).build();
		ClientTO saveClientTwo = clientService.saveClient(clientTwo);
		ClientTO clientThree = new ClientTO().builder().withFirstName("Iza").withLastName("Bak")
				.withPhoneNumber("852321562").withAddress(address).build();
		ClientTO saveClientThree = clientService.saveClient(clientThree);

		// when
		ClientTO findClient = clientService.findById(saveClientThree.getId());

		// then
		assertEquals("Iza", findClient.getFirstName());
		assertEquals("Bak", findClient.getLastName());
		assertEquals("852321562", findClient.getPhoneNumber());
	}

	@Test
	public void shoulCantFindNullClient() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);
		ClientTO clientTwo = new ClientTO().builder().withFirstName("Igor").withLastName("Nowak")
				.withPhoneNumber("125463251").withAddress(address).build();
		ClientTO saveClientTwo = clientService.saveClient(clientTwo);
		ClientTO clientThree = new ClientTO().builder().withFirstName("Iza").withLastName("Bak")
				.withPhoneNumber("852321562").withAddress(address).build();
		ClientTO saveClientThree = clientService.saveClient(clientThree);

		// when
		ClientTO findClient = clientService.findById(null);

		// then
		Assert.assertTrue(findClient == null);
	}

	@Test
	public void shoulFindClientByLastNameAndFirstNameWhenClientWasFirstSave() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);
		ClientTO clientTwo = new ClientTO().builder().withFirstName("Igor").withLastName("Nowak")
				.withPhoneNumber("125463251").withAddress(address).build();
		ClientTO saveClientTwo = clientService.saveClient(clientTwo);
		ClientTO clientThree = new ClientTO().builder().withFirstName("Iza").withLastName("Bak")
				.withPhoneNumber("852321562").withAddress(address).build();
		ClientTO saveClientThree = clientService.saveClient(clientThree);

		// when
		List<ClientTO> findClient = clientService.findByFirstNameAndLastName(clientOne.getFirstName(),
				clientOne.getLastName());

		// then
		assertEquals(1, findClient.size());
		assertEquals("Jan", findClient.get(0).getFirstName());
		assertEquals("Kowal", findClient.get(0).getLastName());
		assertEquals("74547454", findClient.get(0).getPhoneNumber());
	}

	@Test
	public void shoulFindClientByLastNameAndFirstNameWhenClientWasInsideSave() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);
		ClientTO clientTwo = new ClientTO().builder().withFirstName("Igor").withLastName("Nowak")
				.withPhoneNumber("125463251").withAddress(address).build();
		ClientTO saveClientTwo = clientService.saveClient(clientTwo);
		ClientTO clientThree = new ClientTO().builder().withFirstName("Iza").withLastName("Bak")
				.withPhoneNumber("852321562").withAddress(address).build();
		ClientTO saveClientThree = clientService.saveClient(clientThree);

		// when
		List<ClientTO> findClient = clientService.findByFirstNameAndLastName(clientTwo.getFirstName(),
				clientTwo.getLastName());

		// then
		assertEquals(1, findClient.size());
		assertEquals("Igor", findClient.get(0).getFirstName());
		assertEquals("Nowak", findClient.get(0).getLastName());
		assertEquals("125463251", findClient.get(0).getPhoneNumber());
	}

	@Test
	public void shoulFindClientByLastNameAndFirstNameWhenClientWasLastSave() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);
		ClientTO clientTwo = new ClientTO().builder().withFirstName("Igor").withLastName("Nowak")
				.withPhoneNumber("125463251").withAddress(address).build();
		ClientTO saveClientTwo = clientService.saveClient(clientTwo);
		ClientTO clientThree = new ClientTO().builder().withFirstName("Iza").withLastName("Bak")
				.withPhoneNumber("852321562").withAddress(address).build();
		ClientTO saveClientThree = clientService.saveClient(clientThree);

		// when
		List<ClientTO> findClient = clientService.findByFirstNameAndLastName(clientThree.getFirstName(),
				clientThree.getLastName());

		// then
		assertEquals(1, findClient.size());
		assertEquals("Iza", findClient.get(0).getFirstName());
		assertEquals("Bak", findClient.get(0).getLastName());
		assertEquals("852321562", findClient.get(0).getPhoneNumber());
	}

	@Test
	public void shoulFindTwoClientByLastNameAndFirstName() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);
		ClientTO clientTwo = new ClientTO().builder().withFirstName("Igor").withLastName("Nowak")
				.withPhoneNumber("125463251").withAddress(address).build();
		ClientTO saveClientTwo = clientService.saveClient(clientTwo);
		ClientTO clientThree = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("852321562").withAddress(address).build();
		ClientTO saveClientThree = clientService.saveClient(clientThree);

		// when
		List<ClientTO> findClient = clientService.findByFirstNameAndLastName(clientThree.getFirstName(),
				clientThree.getLastName());

		// then
		assertEquals(2, findClient.size());
		assertEquals("Jan", findClient.get(0).getFirstName());
		assertEquals("Kowal", findClient.get(0).getLastName());
		assertEquals("74547454", findClient.get(0).getPhoneNumber());
		assertEquals("Jan", findClient.get(1).getFirstName());
		assertEquals("Kowal", findClient.get(1).getLastName());
		assertEquals("852321562", findClient.get(1).getPhoneNumber());
	}

	@Test
	public void shoulCantFindClientByLastNameAndFirstName() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);
		ClientTO clientTwo = new ClientTO().builder().withFirstName("Igor").withLastName("Nowak")
				.withPhoneNumber("125463251").withAddress(address).build();
		ClientTO saveClientTwo = clientService.saveClient(clientTwo);
		ClientTO clientThree = new ClientTO().builder().withFirstName("Iza").withLastName("Bak")
				.withPhoneNumber("852321562").withAddress(address).build();
		ClientTO saveClientThree = clientService.saveClient(clientThree);

		// when
		List<ClientTO> findClient = clientService.findByFirstNameAndLastName(null, null);

		// then
		assertEquals(0, findClient.size());
		assertTrue(findClient.isEmpty());
	}

	@Test
	public void shoulRemoveClientByIdWhenRemoveFirstSaveClient() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);
		ClientTO clientTwo = new ClientTO().builder().withFirstName("Igor").withLastName("Nowak")
				.withPhoneNumber("125463251").withAddress(address).build();
		ClientTO saveClientTwo = clientService.saveClient(clientTwo);
		ClientTO clientThree = new ClientTO().builder().withFirstName("Iza").withLastName("Bak")
				.withPhoneNumber("852321562").withAddress(address).build();
		ClientTO saveClientThree = clientService.saveClient(clientThree);

		// when
		clientService.removeById(saveClientOne.getId());
		ClientTO findClientOne = clientService.findById(saveClientOne.getId());
		ClientTO findClientTwo = clientService.findById(saveClientTwo.getId());
		ClientTO findClientThree = clientService.findById(saveClientThree.getId());

		// then
		Assert.assertTrue(findClientOne == null);
		assertEquals(clientTwo.getFirstName(), findClientTwo.getFirstName());
		assertEquals(clientTwo.getLastName(), findClientTwo.getLastName());
		assertEquals(clientTwo.getPhoneNumber(), findClientTwo.getPhoneNumber());
		assertEquals(clientThree.getFirstName(), findClientThree.getFirstName());
		assertEquals(clientThree.getLastName(), findClientThree.getLastName());
		assertEquals(clientThree.getPhoneNumber(), findClientThree.getPhoneNumber());
	}

	@Test
	public void shoulRemoveClientByIdWhenRemoveInideSaveClient() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);
		ClientTO clientTwo = new ClientTO().builder().withFirstName("Igor").withLastName("Nowak")
				.withPhoneNumber("125463251").withAddress(address).build();
		ClientTO saveClientTwo = clientService.saveClient(clientTwo);
		ClientTO clientThree = new ClientTO().builder().withFirstName("Iza").withLastName("Bak")
				.withPhoneNumber("852321562").withAddress(address).build();
		ClientTO saveClientThree = clientService.saveClient(clientThree);

		// when
		clientService.removeById(saveClientTwo.getId());
		ClientTO findClientOne = clientService.findById(saveClientOne.getId());
		ClientTO findClientTwo = clientService.findById(saveClientTwo.getId());
		ClientTO findClientThree = clientService.findById(saveClientThree.getId());

		// then
		Assert.assertTrue(findClientTwo == null);
		assertEquals(clientOne.getFirstName(), findClientOne.getFirstName());
		assertEquals(clientOne.getLastName(), findClientOne.getLastName());
		assertEquals(clientOne.getPhoneNumber(), findClientOne.getPhoneNumber());
		assertEquals(clientThree.getFirstName(), findClientThree.getFirstName());
		assertEquals(clientThree.getLastName(), findClientThree.getLastName());
		assertEquals(clientThree.getPhoneNumber(), findClientThree.getPhoneNumber());
	}

	@Test
	public void shoulRemoveClientByIdWhenRemoveLastSaveClient() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);
		ClientTO clientTwo = new ClientTO().builder().withFirstName("Igor").withLastName("Nowak")
				.withPhoneNumber("125463251").withAddress(address).build();
		ClientTO saveClientTwo = clientService.saveClient(clientTwo);
		ClientTO clientThree = new ClientTO().builder().withFirstName("Iza").withLastName("Bak")
				.withPhoneNumber("852321562").withAddress(address).build();
		ClientTO saveClientThree = clientService.saveClient(clientThree);

		// when
		clientService.removeById(saveClientThree.getId());
		ClientTO findClientOne = clientService.findById(saveClientOne.getId());
		ClientTO findClientTwo = clientService.findById(saveClientTwo.getId());
		ClientTO findClientThree = clientService.findById(saveClientThree.getId());

		// then
		Assert.assertTrue(findClientThree == null);
		assertEquals(clientOne.getFirstName(), findClientOne.getFirstName());
		assertEquals(clientOne.getLastName(), findClientOne.getLastName());
		assertEquals(clientOne.getPhoneNumber(), findClientOne.getPhoneNumber());
		assertEquals(clientTwo.getFirstName(), findClientTwo.getFirstName());
		assertEquals(clientTwo.getLastName(), findClientTwo.getLastName());
		assertEquals(clientTwo.getPhoneNumber(), findClientTwo.getPhoneNumber());

	}

	@Test
	public void shoulDontRemoveClientByIdWhenRemoveIdIsNull() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);
		ClientTO clientTwo = new ClientTO().builder().withFirstName("Igor").withLastName("Nowak")
				.withPhoneNumber("125463251").withAddress(address).build();
		ClientTO saveClientTwo = clientService.saveClient(clientTwo);
		ClientTO clientThree = new ClientTO().builder().withFirstName("Iza").withLastName("Bak")
				.withPhoneNumber("852321562").withAddress(address).build();
		ClientTO saveClientThree = clientService.saveClient(clientThree);

		// when
		clientService.removeById(null);
		ClientTO findClientOne = clientService.findById(saveClientOne.getId());
		ClientTO findClientTwo = clientService.findById(saveClientTwo.getId());
		ClientTO findClientThree = clientService.findById(saveClientThree.getId());

		// then
		assertEquals(clientOne.getFirstName(), findClientOne.getFirstName());
		assertEquals(clientOne.getLastName(), findClientOne.getLastName());
		assertEquals(clientOne.getPhoneNumber(), findClientOne.getPhoneNumber());
		assertEquals(clientTwo.getFirstName(), findClientTwo.getFirstName());
		assertEquals(clientTwo.getLastName(), findClientTwo.getLastName());
		assertEquals(clientTwo.getPhoneNumber(), findClientTwo.getPhoneNumber());
		assertEquals(clientThree.getFirstName(), findClientThree.getFirstName());
		assertEquals(clientThree.getLastName(), findClientThree.getLastName());
		assertEquals(clientThree.getPhoneNumber(), findClientThree.getPhoneNumber());
	}

	@Test
	public void shoulRemoveClientByFirstNameAndLastNameWhenRemoveClientWasFirstSave() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);
		ClientTO clientTwo = new ClientTO().builder().withFirstName("Igor").withLastName("Nowak")
				.withPhoneNumber("125463251").withAddress(address).build();
		ClientTO saveClientTwo = clientService.saveClient(clientTwo);
		ClientTO clientThree = new ClientTO().builder().withFirstName("Iza").withLastName("Bak")
				.withPhoneNumber("852321562").withAddress(address).build();
		ClientTO saveClientThree = clientService.saveClient(clientThree);

		// when
		clientService.removeByFirstNameAndLastName(clientOne.getFirstName(), clientOne.getLastName());
		ClientTO findClientOne = clientService.findById(saveClientOne.getId());
		ClientTO findClientTwo = clientService.findById(saveClientTwo.getId());
		ClientTO findClientThree = clientService.findById(saveClientThree.getId());

		// then
		Assert.assertTrue(findClientOne == null);
		assertEquals(clientTwo.getFirstName(), findClientTwo.getFirstName());
		assertEquals(clientTwo.getLastName(), findClientTwo.getLastName());
		assertEquals(clientTwo.getPhoneNumber(), findClientTwo.getPhoneNumber());
		assertEquals(clientThree.getFirstName(), findClientThree.getFirstName());
		assertEquals(clientThree.getLastName(), findClientThree.getLastName());
		assertEquals(clientThree.getPhoneNumber(), findClientThree.getPhoneNumber());
	}

	@Test
	public void shoulRemoveClientByFirstNameAndLastNameWhenRemoveClientWasInideSave() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);
		ClientTO clientTwo = new ClientTO().builder().withFirstName("Igor").withLastName("Nowak")
				.withPhoneNumber("125463251").withAddress(address).build();
		ClientTO saveClientTwo = clientService.saveClient(clientTwo);
		ClientTO clientThree = new ClientTO().builder().withFirstName("Iza").withLastName("Bak")
				.withPhoneNumber("852321562").withAddress(address).build();
		ClientTO saveClientThree = clientService.saveClient(clientThree);

		// when
		clientService.removeByFirstNameAndLastName(clientTwo.getFirstName(), clientTwo.getLastName());
		ClientTO findClientOne = clientService.findById(saveClientOne.getId());
		ClientTO findClientTwo = clientService.findById(saveClientTwo.getId());
		ClientTO findClientThree = clientService.findById(saveClientThree.getId());

		// then
		Assert.assertTrue(findClientTwo == null);
		assertEquals(clientOne.getFirstName(), findClientOne.getFirstName());
		assertEquals(clientOne.getLastName(), findClientOne.getLastName());
		assertEquals(clientOne.getPhoneNumber(), findClientOne.getPhoneNumber());
		assertEquals(clientThree.getFirstName(), findClientThree.getFirstName());
		assertEquals(clientThree.getLastName(), findClientThree.getLastName());
		assertEquals(clientThree.getPhoneNumber(), findClientThree.getPhoneNumber());
	}

	@Test
	public void shoulRemoveClientByFirstNameAndLastNameWhenRemoveClientWasLastSave() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);
		ClientTO clientTwo = new ClientTO().builder().withFirstName("Igor").withLastName("Nowak")
				.withPhoneNumber("125463251").withAddress(address).build();
		ClientTO saveClientTwo = clientService.saveClient(clientTwo);
		ClientTO clientThree = new ClientTO().builder().withFirstName("Iza").withLastName("Bak")
				.withPhoneNumber("852321562").withAddress(address).build();
		ClientTO saveClientThree = clientService.saveClient(clientThree);

		// when
		clientService.removeByFirstNameAndLastName(clientThree.getFirstName(), clientThree.getLastName());
		ClientTO findClientOne = clientService.findById(saveClientOne.getId());
		ClientTO findClientTwo = clientService.findById(saveClientTwo.getId());
		ClientTO findClientThree = clientService.findById(saveClientThree.getId());

		// then
		Assert.assertTrue(findClientThree == null);
		assertEquals(clientOne.getFirstName(), findClientOne.getFirstName());
		assertEquals(clientOne.getLastName(), findClientOne.getLastName());
		assertEquals(clientOne.getPhoneNumber(), findClientOne.getPhoneNumber());
		assertEquals(clientTwo.getFirstName(), findClientTwo.getFirstName());
		assertEquals(clientTwo.getLastName(), findClientTwo.getLastName());
		assertEquals(clientTwo.getPhoneNumber(), findClientTwo.getPhoneNumber());
	}

	@Test
	public void shoulRemoveTwoClientByFirstNameAndLastName() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);
		ClientTO clientTwo = new ClientTO().builder().withFirstName("Igor").withLastName("Nowak")
				.withPhoneNumber("125463251").withAddress(address).build();
		ClientTO saveClientTwo = clientService.saveClient(clientTwo);
		ClientTO clientThree = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("852321562").withAddress(address).build();
		ClientTO saveClientThree = clientService.saveClient(clientThree);

		// when
		clientService.removeByFirstNameAndLastName("Jan", "Kowal");
		ClientTO findClientOne = clientService.findById(saveClientOne.getId());
		ClientTO findClientTwo = clientService.findById(saveClientTwo.getId());
		ClientTO findClientThree = clientService.findById(saveClientThree.getId());

		// then
		Assert.assertTrue(findClientOne == null);
		Assert.assertTrue(findClientThree == null);
		assertEquals(clientTwo.getFirstName(), findClientTwo.getFirstName());
		assertEquals(clientTwo.getLastName(), findClientTwo.getLastName());
		assertEquals(clientTwo.getPhoneNumber(), findClientTwo.getPhoneNumber());
	}

	@Test
	public void shoulCantRemoveClientWithNullFirstNameAndLastName() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);
		ClientTO clientTwo = new ClientTO().builder().withFirstName("Igor").withLastName("Nowak")
				.withPhoneNumber("125463251").withAddress(address).build();
		ClientTO saveClientTwo = clientService.saveClient(clientTwo);
		ClientTO clientThree = new ClientTO().builder().withFirstName("Iza").withLastName("Bak")
				.withPhoneNumber("852321562").withAddress(address).build();
		ClientTO saveClientThree = clientService.saveClient(clientThree);

		// when
		clientService.removeByFirstNameAndLastName(null, null);
		ClientTO findClientOne = clientService.findById(saveClientOne.getId());
		ClientTO findClientTwo = clientService.findById(saveClientTwo.getId());
		ClientTO findClientThree = clientService.findById(saveClientThree.getId());

		// then
		assertEquals(clientOne.getFirstName(), findClientOne.getFirstName());
		assertEquals(clientOne.getLastName(), findClientOne.getLastName());
		assertEquals(clientOne.getPhoneNumber(), findClientOne.getPhoneNumber());
		assertEquals(clientTwo.getFirstName(), findClientTwo.getFirstName());
		assertEquals(clientTwo.getLastName(), findClientTwo.getLastName());
		assertEquals(clientTwo.getPhoneNumber(), findClientTwo.getPhoneNumber());
		assertEquals(clientThree.getFirstName(), findClientThree.getFirstName());
		assertEquals(clientThree.getLastName(), findClientThree.getLastName());
		assertEquals(clientThree.getPhoneNumber(), findClientThree.getPhoneNumber());
	}

	@Test
	public void shoulCantRemoveClientWithFirstNameNullAndLastName() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);
		ClientTO clientTwo = new ClientTO().builder().withFirstName("Igor").withLastName("Nowak")
				.withPhoneNumber("125463251").withAddress(address).build();
		ClientTO saveClientTwo = clientService.saveClient(clientTwo);
		ClientTO clientThree = new ClientTO().builder().withFirstName("Iza").withLastName("Bak")
				.withPhoneNumber("852321562").withAddress(address).build();
		ClientTO saveClientThree = clientService.saveClient(clientThree);

		// when
		clientService.removeByFirstNameAndLastName(null, clientOne.getLastName());
		ClientTO findClientOne = clientService.findById(saveClientOne.getId());
		ClientTO findClientTwo = clientService.findById(saveClientTwo.getId());
		ClientTO findClientThree = clientService.findById(saveClientThree.getId());

		// then
		assertEquals(clientOne.getFirstName(), findClientOne.getFirstName());
		assertEquals(clientOne.getLastName(), findClientOne.getLastName());
		assertEquals(clientOne.getPhoneNumber(), findClientOne.getPhoneNumber());
		assertEquals(clientTwo.getFirstName(), findClientTwo.getFirstName());
		assertEquals(clientTwo.getLastName(), findClientTwo.getLastName());
		assertEquals(clientTwo.getPhoneNumber(), findClientTwo.getPhoneNumber());
		assertEquals(clientThree.getFirstName(), findClientThree.getFirstName());
		assertEquals(clientThree.getLastName(), findClientThree.getLastName());
		assertEquals(clientThree.getPhoneNumber(), findClientThree.getPhoneNumber());
	}

	@Test
	public void shoulCantRemoveClientWithFirstNameAndLastNameNull() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);
		ClientTO clientTwo = new ClientTO().builder().withFirstName("Igor").withLastName("Nowak")
				.withPhoneNumber("125463251").withAddress(address).build();
		ClientTO saveClientTwo = clientService.saveClient(clientTwo);
		ClientTO clientThree = new ClientTO().builder().withFirstName("Iza").withLastName("Bak")
				.withPhoneNumber("852321562").withAddress(address).build();
		ClientTO saveClientThree = clientService.saveClient(clientThree);

		// when
		clientService.removeByFirstNameAndLastName(clientTwo.getFirstName(), null);
		ClientTO findClientOne = clientService.findById(saveClientOne.getId());
		ClientTO findClientTwo = clientService.findById(saveClientTwo.getId());
		ClientTO findClientThree = clientService.findById(saveClientThree.getId());

		// then
		assertEquals(clientOne.getFirstName(), findClientOne.getFirstName());
		assertEquals(clientOne.getLastName(), findClientOne.getLastName());
		assertEquals(clientOne.getPhoneNumber(), findClientOne.getPhoneNumber());
		assertEquals(clientTwo.getFirstName(), findClientTwo.getFirstName());
		assertEquals(clientTwo.getLastName(), findClientTwo.getLastName());
		assertEquals(clientTwo.getPhoneNumber(), findClientTwo.getPhoneNumber());
		assertEquals(clientThree.getFirstName(), findClientThree.getFirstName());
		assertEquals(clientThree.getLastName(), findClientThree.getLastName());
		assertEquals(clientThree.getPhoneNumber(), findClientThree.getPhoneNumber());
	}
}
