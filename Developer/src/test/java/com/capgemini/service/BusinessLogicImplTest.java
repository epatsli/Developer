package com.capgemini.service;

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

import com.capgemini.exception.ToMuchBookFlats;
import com.capgemini.service.impl.BusinessLogicImpl;
import com.capgemini.types.AddressMap;
import com.capgemini.types.ClientTO;
import com.capgemini.types.FlatTO;
import com.capgemini.types.StatusTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BusinessLogicImplTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private BuildingService buildingService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private FlatService flatService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private BusinessLogicImpl businessLogicImpl;

	@Test(expected = ToMuchBookFlats.class)
	public void shouldCantBookFlatByClient() {

		// given
		AddressMap address = new AddressMap().builder().withCity("Poznan").withStreet("Warszawska")
				.withHouseNumber("16/12").withPostCode("84-225").build();

		StatusTO status = new StatusTO().builder().withStatusName("Buy").build();
		StatusTO saveStatus = statusService.saveStatus(status);

		FlatTO flatOne = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatOne = flatService.saveFlat(flatOne);
		FlatTO flatTwo = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatTwo = flatService.saveFlat(flatTwo);
		FlatTO flatThree = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatThree = flatService.saveFlat(flatThree);

		List<Long> bookFlatsClientOne = new ArrayList<>();
		bookFlatsClientOne.add(saveFlatOne.getId());
		bookFlatsClientOne.add(saveFlatTwo.getId());
		bookFlatsClientOne.add(saveFlatThree.getId());

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).withBookFlats(bookFlatsClientOne).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);

		// when
		businessLogicImpl.bookFlatByClient(saveClientOne, saveFlatThree);

		// then

	}

	@Test
	public void shouldBookFlatByClientWhenHeDontHaveBookFlats() {

		// given
		AddressMap address = new AddressMap().builder().withCity("Poznan").withStreet("Warszawska")
				.withHouseNumber("16/12").withPostCode("84-225").build();

		StatusTO status = new StatusTO().builder().withStatusName("Buy").build();
		StatusTO saveStatus = statusService.saveStatus(status);

		FlatTO flatOne = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatOne = flatService.saveFlat(flatOne);
		FlatTO flatTwo = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatTwo = flatService.saveFlat(flatTwo);
		FlatTO flatThree = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatThree = flatService.saveFlat(flatThree);

		List<Long> bookFlatsClientOne = new ArrayList<>();

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).withBookFlats(bookFlatsClientOne).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);

		// when
		ClientTO findClient = businessLogicImpl.bookFlatByClient(saveClientOne, saveFlatOne);

		// then

		assertEquals(1, findClient.getBookFlats().size());
		assertEquals(saveFlatOne.getId(), findClient.getBookFlats().get(0));

	}

	@Test
	public void shouldBookFlatByClient() {

		// given
		AddressMap address = new AddressMap().builder().withCity("Poznan").withStreet("Warszawska")
				.withHouseNumber("16/12").withPostCode("84-225").build();

		StatusTO status = new StatusTO().builder().withStatusName("Buy").build();
		StatusTO saveStatus = statusService.saveStatus(status);

		FlatTO flatOne = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatOne = flatService.saveFlat(flatOne);
		FlatTO flatTwo = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatTwo = flatService.saveFlat(flatTwo);
		FlatTO flatThree = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatThree = flatService.saveFlat(flatThree);

		List<Long> bookFlatsClientOne = new ArrayList<>();
		bookFlatsClientOne.add(saveFlatOne.getId());
		;

		ClientTO clientOne = new ClientTO().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).withBookFlats(bookFlatsClientOne).build();
		ClientTO saveClientOne = clientService.saveClient(clientOne);

		// when
		ClientTO findClient = businessLogicImpl.bookFlatByClient(saveClientOne, saveFlatThree);

		// then

		assertEquals(2, findClient.getBookFlats().size());
		assertEquals(saveFlatOne.getId(), findClient.getBookFlats().get(0));
		assertEquals(saveFlatThree.getId(), findClient.getBookFlats().get(1));

	}

}
