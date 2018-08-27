package com.capgemini.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import com.capgemini.dao.impl.BusinessLogicDaoImpl;
import com.capgemini.domain.Address;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.FlatEntity;
import com.capgemini.domain.StatusEntity;
import com.capgemini.exception.ToMuchBookFlats;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BusinessLogicDaoTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private FlatDao flatDao;

	@Autowired
	private StatusDao statusDao;

	@Autowired
	private ClientDao clientDao;

	@Autowired
	private BusinessLogicDaoImpl businessLogicDao;

	@Test
	public void shouldFindClientWhoBuyOrBookFlat() {

		// given
		Address address = new Address().builder().withCity("Poznan").withStreet("Warszawska").withHouseNumber("16/12")
				.withPostCode("84-225").build();

		StatusEntity status = new StatusEntity().builder().withStatusName("Buy").build();
		StatusEntity saveStatus = statusDao.save(status);

		FlatEntity flatOne = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatEntity saveFlatOne = flatDao.save(flatOne);
		FlatEntity flatTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatEntity saveFlatTwo = flatDao.save(flatTwo);
		FlatEntity flatThree = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatEntity saveFlatThree = flatDao.save(flatThree);

		List<FlatEntity> buyFlatsClientOne = new ArrayList<>();
		buyFlatsClientOne.add(saveFlatOne);
		buyFlatsClientOne.add(saveFlatTwo);
		List<FlatEntity> buyFlatsClientTwo = new ArrayList<>();
		buyFlatsClientTwo.add(saveFlatTwo);
		List<FlatEntity> buyFlatsClientThree = new ArrayList<>();
		buyFlatsClientThree.add(saveFlatThree);

		ClientEntity clientOne = new ClientEntity().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).withBuyFlats(buyFlatsClientOne).build();
		ClientEntity clientTwo = new ClientEntity().builder().withFirstName("Edward").withLastName("Bak")
				.withPhoneNumber("625451474").withAddress(address).withBuyFlats(buyFlatsClientTwo).build();
		ClientEntity clientThree = new ClientEntity().builder().withFirstName("Ola").withLastName("Ssak")
				.withPhoneNumber("785474547").withAddress(address).withBuyFlats(buyFlatsClientThree).build();
		clientDao.save(clientOne);
		clientDao.save(clientTwo);
		clientDao.save(clientThree);

		// when
		List<ClientEntity> findClient = businessLogicDao.findClientWhoBuyOrBookFlat(saveFlatOne);

		// then
		assertNotNull(findClient);
		// assertEquals(1, findClient.size());
	}

	@Test(expected = ToMuchBookFlats.class)
	public void shouldCantBookFlatByClient() {

		// given
		Address address = new Address().builder().withCity("Poznan").withStreet("Warszawska").withHouseNumber("16/12")
				.withPostCode("84-225").build();

		StatusEntity status = new StatusEntity().builder().withStatusName("Buy").build();
		StatusEntity saveStatus = statusDao.save(status);

		FlatEntity flatOne = new FlatEntity().builder().withFlatStatus(status).withAreaFlat(35.75D).withAddress(address)
				.build();
		FlatEntity saveFlatOne = flatDao.save(flatOne);
		FlatEntity flatTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatEntity saveFlatTwo = flatDao.save(flatTwo);
		FlatEntity flatThree = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatEntity saveFlatThree = flatDao.save(flatThree);

		List<FlatEntity> buyFlatsClientOne = new ArrayList<>();
		buyFlatsClientOne.add(saveFlatOne);
		buyFlatsClientOne.add(saveFlatTwo);
		buyFlatsClientOne.add(saveFlatThree);

		ClientEntity clientOne = new ClientEntity().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).withBookFlats(buyFlatsClientOne).build();
		ClientEntity saveClientOne = clientDao.save(clientOne);

		// when
		businessLogicDao.bookFlatByClient(saveClientOne, saveFlatThree);

		// then

	}

	@Test
	public void shouldBookFlatByClientWhenHeDontHaveBookFlats() {

		// given
		Address address = new Address().builder().withCity("Poznan").withStreet("Warszawska").withHouseNumber("16/12")
				.withPostCode("84-225").build();

		StatusEntity status = new StatusEntity().builder().withStatusName("Buy").build();
		StatusEntity saveStatus = statusDao.save(status);

		FlatEntity flatOne = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatEntity saveFlatOne = flatDao.save(flatOne);
		FlatEntity flatTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withAddress(address).build();
		flatDao.save(flatTwo);
		FlatEntity flatThree = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withAddress(address).build();
		flatDao.save(flatThree);

		List<FlatEntity> bookFlatsClientOne = new ArrayList<>();

		ClientEntity clientOne = new ClientEntity().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).withBookFlats(bookFlatsClientOne).build();
		ClientEntity saveClientOne = clientDao.save(clientOne);

		// when
		ClientEntity findClient = businessLogicDao.bookFlatByClient(saveClientOne, saveFlatOne);

		// then

		assertEquals(1, findClient.getBookFlats().size());
		assertEquals(saveFlatOne, findClient.getBookFlats().get(0));

	}

	@Test
	public void shouldBookFlatByClient() {

		// given
		Address address = new Address().builder().withCity("Poznan").withStreet("Warszawska").withHouseNumber("16/12")
				.withPostCode("84-225").build();

		StatusEntity status = new StatusEntity().builder().withStatusName("Buy").build();
		StatusEntity saveStatus = statusDao.save(status);

		FlatEntity flatOne = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatEntity saveFlatOne = flatDao.save(flatOne);
		FlatEntity flatTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withAddress(address).build();
		flatDao.save(flatTwo);
		FlatEntity flatThree = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatEntity saveFlatThree = flatDao.save(flatThree);

		List<FlatEntity> bookFlatsClientOne = new ArrayList<>();
		bookFlatsClientOne.add(saveFlatOne);

		ClientEntity clientOne = new ClientEntity().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).withBookFlats(bookFlatsClientOne).build();
		ClientEntity saveClientOne = clientDao.save(clientOne);

		// when
		ClientEntity findClient = businessLogicDao.bookFlatByClient(saveClientOne, saveFlatThree);

		// then

		assertEquals(2, findClient.getBookFlats().size());
		assertEquals(saveFlatOne, findClient.getBookFlats().get(0));
		assertEquals(saveFlatThree, findClient.getBookFlats().get(1));

	}

}
