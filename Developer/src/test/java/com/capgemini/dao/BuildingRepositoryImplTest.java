package com.capgemini.dao;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.impl.BuildingRepositoryImpl;
import com.capgemini.domain.Address;
import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.FlatEntity;
import com.capgemini.domain.StatusEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BuildingRepositoryImplTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private BuildingRepositoryImpl buildingRepositoryImpl;

	@Autowired
	private BuildingDao buildingDao;

	@Autowired
	private FlatDao flatDao;

	@Autowired
	private StatusDao statusDao;

	@Test
	public void shouldFindCountFlatsInStatusForOneBuilding() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		StatusEntity statusEmpty = new StatusEntity().builder().withStatusName("Empty").build();
		statusDao.save(statusEmpty);

		StatusEntity statusBook = new StatusEntity().builder().withStatusName("Book").build();
		StatusEntity saveStatusBook = statusDao.save(statusBook);

		StatusEntity statusBuy = new StatusEntity().builder().withStatusName("Buy").build();
		StatusEntity saveStatusBuy = statusDao.save(statusBuy);

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);

		FlatEntity flatOne = new FlatEntity().builder().withFlatStatus(saveStatusBuy).withAreaFlat(35.75D)
				.withPrice(100000D).withAddress(address).withBuilding(saveBuildingOne).build();
		flatDao.save(flatOne);
		FlatEntity flatTwo = new FlatEntity().builder().withFlatStatus(saveStatusBuy).withAreaFlat(35.75D)
				.withPrice(200000D).withAddress(address).withBuilding(saveBuildingOne).build();
		flatDao.save(flatTwo);
		FlatEntity flatThree = new FlatEntity().builder().withFlatStatus(saveStatusBook).withAreaFlat(35.75D)
				.withPrice(300000D).withAddress(address).withBuilding(saveBuildingOne).build();
		flatDao.save(flatThree);
		Long expected = 2L;

		// when
		Long count = buildingRepositoryImpl.findCountFlatsInStatusInBuilding(buildingOne, saveStatusBuy);

		// then
		assertEquals(expected, count);
	}

	@Test
	public void shouldFindCountFlatsInStatusForTwoBuilding() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		StatusEntity statusEmpty = new StatusEntity().builder().withStatusName("Empty").build();
		StatusEntity saveStatusEmpty = statusDao.save(statusEmpty);

		StatusEntity statusBook = new StatusEntity().builder().withStatusName("Book").build();
		StatusEntity saveStatusBook = statusDao.save(statusBook);

		StatusEntity statusBuy = new StatusEntity().builder().withStatusName("Buy").build();
		StatusEntity saveStatusBuy = statusDao.save(statusBuy);

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);

		FlatEntity flatOne = new FlatEntity().builder().withFlatStatus(saveStatusBuy).withAreaFlat(35.75D)
				.withPrice(100000D).withAddress(address).withBuilding(saveBuildingOne).build();
		flatDao.save(flatOne);
		FlatEntity flatTwo = new FlatEntity().builder().withFlatStatus(saveStatusBuy).withAreaFlat(35.75D)
				.withPrice(200000D).withAddress(address).withBuilding(saveBuildingOne).build();
		flatDao.save(flatTwo);
		FlatEntity flatThree = new FlatEntity().builder().withFlatStatus(saveStatusBook).withAreaFlat(35.75D)
				.withPrice(300000D).withAddress(address).withBuilding(saveBuildingOne).build();
		flatDao.save(flatThree);

		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the beach").withNumberFloor(new Integer(8))
				.withNumberFlat(new Integer(14)).withElevator(true).withLocation("Radom").build();
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);

		FlatEntity flatOneInBuildingTwo = new FlatEntity().builder().withFlatStatus(saveStatusBook).withAreaFlat(35.75D)
				.withPrice(100D).withAddress(address).withBuilding(saveBuildingTwo).withFloor(new Integer(1)).build();
		flatDao.save(flatOneInBuildingTwo);
		FlatEntity flatTwoInBuildingTwo = new FlatEntity().builder().withFlatStatus(saveStatusEmpty)
				.withAreaFlat(35.75D).withPrice(200D).withAddress(address).withBuilding(saveBuildingTwo)
				.withFloor(new Integer(0)).build();
		flatDao.save(flatTwoInBuildingTwo);
		FlatEntity flatThreeInBuildingTwo = new FlatEntity().builder().withFlatStatus(saveStatusBook)
				.withAreaFlat(35.75D).withPrice(300D).withAddress(address).withBuilding(saveBuildingTwo)
				.withFloor(new Integer(9)).build();
		flatDao.save(flatThreeInBuildingTwo);

		Long expected = 2L;

		// when
		Long count = buildingRepositoryImpl.findCountFlatsInStatusInBuilding(saveBuildingTwo, saveStatusBook);

		// then
		assertEquals(expected, count);
	}

	@Test
	public void shouldCantFindCountFlatsInNullStatusForTwoBuilding() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		StatusEntity statusEmpty = new StatusEntity().builder().withStatusName("Empty").build();
		StatusEntity saveStatusEmpty = statusDao.save(statusEmpty);

		StatusEntity statusBook = new StatusEntity().builder().withStatusName("Book").build();
		StatusEntity saveStatusBook = statusDao.save(statusBook);

		StatusEntity statusBuy = new StatusEntity().builder().withStatusName("Buy").build();
		StatusEntity saveStatusBuy = statusDao.save(statusBuy);

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);

		FlatEntity flatOne = new FlatEntity().builder().withFlatStatus(saveStatusBuy).withAreaFlat(35.75D)
				.withPrice(100000D).withAddress(address).withBuilding(saveBuildingOne).build();
		flatDao.save(flatOne);
		FlatEntity flatTwo = new FlatEntity().builder().withFlatStatus(saveStatusBuy).withAreaFlat(35.75D)
				.withPrice(200000D).withAddress(address).withBuilding(saveBuildingOne).build();
		flatDao.save(flatTwo);
		FlatEntity flatThree = new FlatEntity().builder().withFlatStatus(saveStatusBook).withAreaFlat(35.75D)
				.withPrice(300000D).withAddress(address).withBuilding(saveBuildingOne).build();
		flatDao.save(flatThree);

		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the beach").withNumberFloor(new Integer(8))
				.withNumberFlat(new Integer(14)).withElevator(true).withLocation("Radom").build();
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);

		FlatEntity flatOneInBuildingTwo = new FlatEntity().builder().withFlatStatus(saveStatusBook).withAreaFlat(35.75D)
				.withPrice(100D).withAddress(address).withBuilding(saveBuildingTwo).withFloor(new Integer(1)).build();
		flatDao.save(flatOneInBuildingTwo);
		FlatEntity flatTwoInBuildingTwo = new FlatEntity().builder().withFlatStatus(saveStatusEmpty)
				.withAreaFlat(35.75D).withPrice(200D).withAddress(address).withBuilding(saveBuildingTwo)
				.withFloor(new Integer(0)).build();
		flatDao.save(flatTwoInBuildingTwo);
		FlatEntity flatThreeInBuildingTwo = new FlatEntity().builder().withFlatStatus(saveStatusBook)
				.withAreaFlat(35.75D).withPrice(300D).withAddress(address).withBuilding(saveBuildingTwo)
				.withFloor(new Integer(9)).build();
		flatDao.save(flatThreeInBuildingTwo);

		Long expected = 0L;

		// when
		Long count = buildingRepositoryImpl.findCountFlatsInStatusInBuilding(saveBuildingTwo, null);

		// then
		assertEquals(expected, count);
	}

	@Test
	public void shouldCantFindCountFlatsInStatusForNullBuilding() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		StatusEntity statusEmpty = new StatusEntity().builder().withStatusName("Empty").build();
		StatusEntity saveStatusEmpty = statusDao.save(statusEmpty);

		StatusEntity statusBook = new StatusEntity().builder().withStatusName("Book").build();
		StatusEntity saveStatusBook = statusDao.save(statusBook);

		StatusEntity statusBuy = new StatusEntity().builder().withStatusName("Buy").build();
		StatusEntity saveStatusBuy = statusDao.save(statusBuy);

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);

		FlatEntity flatOne = new FlatEntity().builder().withFlatStatus(saveStatusBuy).withAreaFlat(35.75D)
				.withPrice(100000D).withAddress(address).withBuilding(saveBuildingOne).build();
		flatDao.save(flatOne);
		FlatEntity flatTwo = new FlatEntity().builder().withFlatStatus(saveStatusBuy).withAreaFlat(35.75D)
				.withPrice(200000D).withAddress(address).withBuilding(saveBuildingOne).build();
		flatDao.save(flatTwo);
		FlatEntity flatThree = new FlatEntity().builder().withFlatStatus(saveStatusBook).withAreaFlat(35.75D)
				.withPrice(300000D).withAddress(address).withBuilding(saveBuildingOne).build();
		flatDao.save(flatThree);

		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the beach").withNumberFloor(new Integer(8))
				.withNumberFlat(new Integer(14)).withElevator(true).withLocation("Radom").build();
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);

		FlatEntity flatOneInBuildingTwo = new FlatEntity().builder().withFlatStatus(saveStatusBook).withAreaFlat(35.75D)
				.withPrice(100D).withAddress(address).withBuilding(saveBuildingTwo).withFloor(new Integer(1)).build();
		flatDao.save(flatOneInBuildingTwo);
		FlatEntity flatTwoInBuildingTwo = new FlatEntity().builder().withFlatStatus(saveStatusEmpty)
				.withAreaFlat(35.75D).withPrice(200D).withAddress(address).withBuilding(saveBuildingTwo)
				.withFloor(new Integer(0)).build();
		flatDao.save(flatTwoInBuildingTwo);
		FlatEntity flatThreeInBuildingTwo = new FlatEntity().builder().withFlatStatus(saveStatusBook)
				.withAreaFlat(35.75D).withPrice(300D).withAddress(address).withBuilding(saveBuildingTwo)
				.withFloor(new Integer(9)).build();
		flatDao.save(flatThreeInBuildingTwo);

		Long expected = 0L;

		// when
		Long count = buildingRepositoryImpl.findCountFlatsInStatusInBuilding(null, saveStatusEmpty);

		// then
		assertEquals(expected, count);
	}

	@Test
	public void shouldCantFindCountFlatsInNullStatusForNullBuilding() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		StatusEntity statusEmpty = new StatusEntity().builder().withStatusName("Empty").build();
		StatusEntity saveStatusEmpty = statusDao.save(statusEmpty);

		StatusEntity statusBook = new StatusEntity().builder().withStatusName("Book").build();
		StatusEntity saveStatusBook = statusDao.save(statusBook);

		StatusEntity statusBuy = new StatusEntity().builder().withStatusName("Buy").build();
		StatusEntity saveStatusBuy = statusDao.save(statusBuy);

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);

		FlatEntity flatOne = new FlatEntity().builder().withFlatStatus(saveStatusBuy).withAreaFlat(35.75D)
				.withPrice(100000D).withAddress(address).withBuilding(saveBuildingOne).build();
		flatDao.save(flatOne);
		FlatEntity flatTwo = new FlatEntity().builder().withFlatStatus(saveStatusBuy).withAreaFlat(35.75D)
				.withPrice(200000D).withAddress(address).withBuilding(saveBuildingOne).build();
		flatDao.save(flatTwo);
		FlatEntity flatThree = new FlatEntity().builder().withFlatStatus(saveStatusBook).withAreaFlat(35.75D)
				.withPrice(300000D).withAddress(address).withBuilding(saveBuildingOne).build();
		flatDao.save(flatThree);

		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the beach").withNumberFloor(new Integer(8))
				.withNumberFlat(new Integer(14)).withElevator(true).withLocation("Radom").build();
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);

		FlatEntity flatOneInBuildingTwo = new FlatEntity().builder().withFlatStatus(saveStatusBook).withAreaFlat(35.75D)
				.withPrice(100D).withAddress(address).withBuilding(saveBuildingTwo).withFloor(new Integer(1)).build();
		flatDao.save(flatOneInBuildingTwo);
		FlatEntity flatTwoInBuildingTwo = new FlatEntity().builder().withFlatStatus(saveStatusEmpty)
				.withAreaFlat(35.75D).withPrice(200D).withAddress(address).withBuilding(saveBuildingTwo)
				.withFloor(new Integer(0)).build();
		flatDao.save(flatTwoInBuildingTwo);
		FlatEntity flatThreeInBuildingTwo = new FlatEntity().builder().withFlatStatus(saveStatusBook)
				.withAreaFlat(35.75D).withPrice(300D).withAddress(address).withBuilding(saveBuildingTwo)
				.withFloor(new Integer(9)).build();
		flatDao.save(flatThreeInBuildingTwo);

		Long expected = 0L;

		// when
		Long count = buildingRepositoryImpl.findCountFlatsInStatusInBuilding(null, null);

		// then
		assertEquals(expected, count);
	}

}
