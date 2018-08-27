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

	@Test
	public void shouldFindSumPriceFlatsForOneClient() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		ClientEntity clientOne = new ClientEntity();
		ClientEntity clientTwo = new ClientEntity();
		ClientEntity clientThree = new ClientEntity();

		StatusEntity status = new StatusEntity().builder().withStatusName("Book").build();
		StatusEntity saveStatus = statusDao.save(status);
		FlatEntity flatOne = new FlatEntity().builder().withFlatStatus(status).withAreaFlat(35.75D).withPrice(100000D)
				.withAddress(address).withOwner(clientOne).build();
		FlatEntity saveFlatOne = flatDao.save(flatOne);
		FlatEntity flatTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(200000D).withAddress(address).build();
		FlatEntity saveFlatTwo = flatDao.save(flatTwo);
		FlatEntity flatThree = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(300000D).withAddress(address).withOwner(clientOne).build();
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
		flatOne.setOwner(saveClientOne);
		flatTwo.setOwner(saveClientTwo);
		flatThree.setOwner(saveClientThree);

		List<ClientEntity> buyFlatsOne = new ArrayList<>();
		buyFlatsOne.add(saveClientOne);
		buyFlatsOne.add(saveClientThree);
		flatOne.setClientBuy(buyFlatsOne);

		List<ClientEntity> buyFlatsTwo = new ArrayList<>();
		buyFlatsTwo.add(saveClientTwo);
		flatTwo.setClientBuy(buyFlatsTwo);

		List<ClientEntity> buyFlatsThree = new ArrayList<>();
		buyFlatsThree.add(saveClientOne);
		buyFlatsThree.add(saveClientThree);
		flatOne.setClientBuy(buyFlatsThree);

		// when
		Double findPrice = clientRepositoryImpl.findSumPriceFlatsBuyByOneClient(saveClientTwo);

		// then
		assertEquals(saveFlatTwo.getPrice(), findPrice);
	}

	@Test
	public void shouldFindClientWhoBuyMoreThanOneFlat() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		ClientEntity clientOne = new ClientEntity();
		ClientEntity clientTwo = new ClientEntity();
		ClientEntity clientThree = new ClientEntity();

		StatusEntity status = new StatusEntity().builder().withStatusName("Book").build();
		StatusEntity saveStatus = statusDao.save(status);
		FlatEntity flatOne = new FlatEntity().builder().withFlatStatus(status).withAreaFlat(35.75D).withPrice(100000D)
				.withAddress(address).withOwner(clientOne).build();
		FlatEntity saveFlatOne = flatDao.save(flatOne);
		FlatEntity flatTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(200000D).withAddress(address).build();
		FlatEntity saveFlatTwo = flatDao.save(flatTwo);
		FlatEntity flatThree = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(300000D).withAddress(address).withOwner(clientOne).build();
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
		List<ClientEntity> clientBuy = new ArrayList<>();
		clientBuy.add(saveClientOne);
		clientBuy.add(saveClientThree);
		List<ClientEntity> clientBuys = new ArrayList<>();
		clientBuys.add(saveClientTwo);
		saveFlatOne.setClientBuy(clientBuy);
		saveFlatTwo.setClientBuy(clientBuys);
		saveFlatThree.setClientBuy(clientBuy);

		// when
		List<ClientEntity> findClient = clientRepositoryImpl.findAllClientWhoBuyMoreThanOneFlat();

		// then
		assertEquals(2, findClient.size());
		assertEquals(saveClientOne, findClient.get(0));
		assertEquals(saveClientThree, findClient.get(1));
	}
}
