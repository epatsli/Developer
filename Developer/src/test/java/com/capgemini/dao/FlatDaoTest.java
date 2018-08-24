package com.capgemini.dao;

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

import com.capgemini.domain.Address;
import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.FlatEntity;
import com.capgemini.domain.StatusEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FlatDaoTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private FlatDao flatDao;

	@Autowired
	private StatusDao statusDao;

	@Autowired
	private BuildingDao buildingDao;

	@Autowired
	private ClientDao clientDao;

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateFlatWithoutArea() {

		// given
		StatusEntity status = new StatusEntity().builder().withStatusName("Reserved").build();
		StatusEntity saveStatus = statusDao.save(status);

		// when
		new FlatEntity().builder().withFlatStatus(saveStatus).build();

		// then

	}

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateFlatWithoutStatus() {

		// given

		// when
		new FlatEntity().builder().withAreaFlat(34.25D).build();

		// then

	}

	@Test
	public void shouldThrownException() {

		assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
			throw new RuntimeException("This flat can't be created.");
		}).withMessage("This flat can't be created.").withStackTraceContaining("RuntimeException");
	}

	@Test
	public void shouldCreateFlat() {

		// given
		Address address = new Address().builder().withCity("Poznan").withStreet("Warszawska").withHouseNumber("16/12")
				.withPostCode("84-225").build();
		StatusEntity status = new StatusEntity().builder().withStatusName("Reserved").build();
		StatusEntity saveStatus = statusDao.save(status);
		FlatEntity flat = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withAddress(address).build();

		// when
		flatDao.save(flat);

		// then
		assertEquals(1, flatDao.count());
	}

	@Test
	public void shouldFindByIdWhereFlatWasFirstSave() {

		// given
		StatusEntity status = new StatusEntity().builder().withStatusName("Reserved").build();
		StatusEntity saveStatus = statusDao.save(status);

		Address address = new Address().builder().withCity("Poznan").withStreet("Warszawska").withHouseNumber("16/12")
				.withPostCode("84-225").build();
		FlatEntity flatOne = new FlatEntity().builder().withFlatStatus(status).withAreaFlat(35.75D).withAddress(address)
				.build();
		FlatEntity saveFlatOne = flatDao.save(flatOne);
		FlatEntity flatTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatEntity saveFlatTwo = flatDao.save(flatTwo);
		FlatEntity flatThree = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatEntity saveFlatThree = flatDao.save(flatThree);

		// when
		FlatEntity findFlat = flatDao.findById(flatOne.getId());

		// then
		assertEquals(3, flatDao.count());
		assertEquals(saveFlatOne, findFlat);
	}

	@Test
	public void shouldFindByIdWhereFlatWasInsertSave() {

		// given
		Address address = new Address().builder().withCity("Poznan").withStreet("Warszawska").withHouseNumber("16/12")
				.withPostCode("84-225").build();
		StatusEntity status = new StatusEntity().builder().withStatusName("Reserved").build();
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

		// when
		FlatEntity findFlat = flatDao.findById(flatTwo.getId());

		// then
		assertEquals(3, flatDao.count());
		assertEquals(saveFlatTwo, findFlat);
	}

	@Test
	public void shouldFindByIdWhereFlatWasLastSave() {

		// given
		Address address = new Address().builder().withCity("Poznan").withStreet("Warszawska").withHouseNumber("16/12")
				.withPostCode("84-225").build();
		StatusEntity status = new StatusEntity().builder().withStatusName("Reserved").build();
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

		// when
		FlatEntity findFlat = flatDao.findById(flatThree.getId());

		// then
		assertEquals(3, flatDao.count());
		assertEquals(saveFlatThree, findFlat);
	}

	public void shouldCantFindByNullId() {

		// given
		Address address = new Address().builder().withCity("Poznan").withStreet("Warszawska").withHouseNumber("16/12")
				.withPostCode("84-225").build();
		StatusEntity status = new StatusEntity().builder().withStatusName("Reserved").build();
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

		// when
		FlatEntity findFlat = flatDao.findById(null);

		// then
		assertEquals(3, flatDao.count());
		assertEquals(null, findFlat);
	}

	@Test
	public void shouldRemoveByIdWhereFlatWasFirstSave() {

		// given
		Address address = new Address().builder().withCity("Poznan").withStreet("Warszawska").withHouseNumber("16/12")
				.withPostCode("84-225").build();
		StatusEntity status = new StatusEntity().builder().withStatusName("Reserved").build();
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

		// when
		flatDao.removeById(flatOne.getId());

		// then
		assertEquals(2, flatDao.count());
		assertEquals(flatTwo, flatDao.findById(saveFlatTwo.getId()));
		assertEquals(flatThree, flatDao.findById(saveFlatThree.getId()));
	}

	@Test
	public void shouldRemoveByIdWhereFlatWasInsertSave() {

		// given
		Address address = new Address().builder().withCity("Poznan").withStreet("Warszawska").withHouseNumber("16/12")
				.withPostCode("84-225").build();
		StatusEntity status = new StatusEntity().builder().withStatusName("Reserved").build();
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

		// when
		flatDao.removeById(flatTwo.getId());

		// then
		assertEquals(2, flatDao.count());
		assertEquals(flatOne, flatDao.findById(saveFlatOne.getId()));
		assertEquals(flatThree, flatDao.findById(saveFlatThree.getId()));
	}

	@Test
	public void shouldRemoveByIdWhereFlatWasLastSave() {

		// given
		Address address = new Address().builder().withCity("Poznan").withStreet("Warszawska").withHouseNumber("16/12")
				.withPostCode("84-225").build();
		StatusEntity status = new StatusEntity().builder().withStatusName("Reserved").build();
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

		// when
		flatDao.removeById(flatThree.getId());

		// then
		assertEquals(2, flatDao.count());
		assertEquals(flatOne, flatDao.findById(saveFlatOne.getId()));
		assertEquals(flatTwo, flatDao.findById(saveFlatTwo.getId()));
	}

	@Test
	public void shouldCantRemoveByNullId() {

		// given
		StatusEntity status = new StatusEntity().builder().withStatusName("Reserved").build();
		StatusEntity saveStatus = statusDao.save(status);

		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);

		ClientEntity clientOne = new ClientEntity().builder().withFirstName("Jan").withLastName("Kowal")
				.withPhoneNumber("74547454").withAddress(address).build();
		ClientEntity clientTwo = new ClientEntity().builder().withFirstName("Edward").withLastName("Bak")
				.withPhoneNumber("625451474").withAddress(address).build();
		ClientEntity clientThree = new ClientEntity().builder().withFirstName("Ola").withLastName("Ssak")
				.withPhoneNumber("785474547").withAddress(address).build();
		clientDao.save(clientOne);
		clientDao.save(clientTwo);
		clientDao.save(clientThree);
		List<ClientEntity> bookFlats = new ArrayList<>();
		bookFlats.add(clientTwo);
		List<ClientEntity> buyFlats = new ArrayList<>();
		buyFlats.add(clientThree);

		FlatEntity flatOne = new FlatEntity().builder().withFlatStatus(status).withAreaFlat(35.75D)
				.withNumberBalconie(new Integer(8)).withBuilding(saveBuildingOne).withPrice(178514D)
				.withOwner(clientOne).withClientBook(bookFlats).withClientBuy(buyFlats).withAddress(address).build();
		FlatEntity saveFlatOne = flatDao.save(flatOne);
		FlatEntity flatTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatEntity saveFlatTwo = flatDao.save(flatTwo);
		FlatEntity flatThree = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatEntity saveFlatThree = flatDao.save(flatThree);

		// when
		flatDao.removeById(null);

		// then
		assertEquals(3, flatDao.count());
		assertEquals(flatOne, flatDao.findById(saveFlatOne.getId()));
		assertEquals(flatTwo, flatDao.findById(saveFlatTwo.getId()));
		assertEquals(flatThree, flatDao.findById(saveFlatThree.getId()));
	}
}
