package com.capgemini.dao;

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

import com.capgemini.dao.impl.ClientRepositoryImpl;
import com.capgemini.domain.Address;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.FlatEntity;
import com.capgemini.domain.StatusEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ClientRepositoryImplTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ClientRepositoryImpl clientRepositoryImpl;

	@Autowired
	private FlatDao flatDao;

	@Autowired
	private StatusDao statusDao;

	@Autowired
	private BuildingDao buildingDao;

	@Autowired
	private ClientDao clientDao;

	@Test // (expected = RuntimeException.class)
	public void shouldCantCreateClientWithoutFirstName() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		ClientEntity clientOne = new ClientEntity();
		ClientEntity clientTwo = new ClientEntity();
		ClientEntity clientThree = new ClientEntity();

		StatusEntity status = new StatusEntity().builder().withStatusName("Book").build();
		StatusEntity saveStatus = statusDao.save(status);
		FlatEntity flatOne = new FlatEntity().builder().withFlatStatus(status).withAreaFlat(35.75D).withPrice(470254D)
				.withAddress(address).withOwner(clientOne).build();
		FlatEntity saveFlatOne = flatDao.save(flatOne);
		FlatEntity flatTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(214251D).withAddress(address).withOwner(clientTwo).build();
		FlatEntity saveFlatTwo = flatDao.save(flatTwo);
		FlatEntity flatThree = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(124152D).withAddress(address).withOwner(clientOne).build();
		FlatEntity saveFlatThree = flatDao.save(flatThree);

		List<FlatEntity> flats = new ArrayList<>();
		flats.add(saveFlatOne);
		flats.add(saveFlatThree);

		List<FlatEntity> flat = new ArrayList<>();
		flat.add(saveFlatOne);

		clientOne = new ClientEntity().builder().withFirstName("Jan").withLastName("Kowal").withPhoneNumber("74547454")
				.withAddress(address).withBuyFlats(flats).build();
		clientTwo = new ClientEntity().builder().withFirstName("Edward").withLastName("Bak")
				.withPhoneNumber("625451474").withAddress(address).withBuyFlats(flat).build();
		clientThree = new ClientEntity().builder().withFirstName("Ola").withLastName("Ssak")
				.withPhoneNumber("785474547").withAddress(address).withBuyFlats(flats).build();

		ClientEntity saveClientOne = clientRepositoryImpl.save(clientOne);
		ClientEntity saveClientTwo = clientRepositoryImpl.save(clientTwo);
		ClientEntity saveClientThree = clientRepositoryImpl.save(clientThree);

		// when
		Double findClient = clientRepositoryImpl.findSumPriceFlatsBuyByOneClient(saveClientTwo);

		// then
		Double sumPrice = saveFlatOne.getPrice() + saveFlatThree.getPrice();
		assertEquals(sumPrice, findClient);
	}

}
