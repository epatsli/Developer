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

import com.capgemini.dao.impl.FlatRepositoryImpl;
import com.capgemini.domain.Address;
import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.FlatEntity;
import com.capgemini.domain.StatusEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FlatRepositoryImplTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private FlatRepositoryImpl flatRepositoryImpl;

	@Autowired
	private BuildingDao buildingDao;

	@Autowired
	private FlatDao flatDao;

	@Autowired
	private StatusDao statusDao;

	@Test
	public void shouldFindAverageFlatPriceInBuilding() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		StatusEntity status = new StatusEntity().builder().withStatusName("Book").build();
		StatusEntity saveStatus = statusDao.save(status);

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);

		FlatEntity flatOne = new FlatEntity().builder().withFlatStatus(status).withAreaFlat(35.75D).withPrice(100000D)
				.withAddress(address).withBuilding(saveBuildingOne).build();
		FlatEntity saveFlatOne = flatDao.save(flatOne);
		FlatEntity flatTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(200000D).withAddress(address).withBuilding(saveBuildingOne).build();
		FlatEntity saveFlatTwo = flatDao.save(flatTwo);
		FlatEntity flatThree = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(300000D).withAddress(address).withBuilding(saveBuildingOne).build();
		FlatEntity saveFlatThree = flatDao.save(flatThree);
		Double avgPriceBuilding = (saveFlatOne.getPrice() + saveFlatTwo.getPrice() + saveFlatThree.getPrice()) / 3;

		// when
		Double avgPrice = flatRepositoryImpl.findAverageFlatPriceInBuilding(saveBuildingOne);

		// then
		assertEquals(avgPriceBuilding, avgPrice);
	}

	@Test
	public void shouldCannotAvarangeForNullBuilding() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		StatusEntity status = new StatusEntity().builder().withStatusName("Book").build();
		StatusEntity saveStatus = statusDao.save(status);

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);

		FlatEntity flatOne = new FlatEntity().builder().withFlatStatus(status).withAreaFlat(35.75D).withPrice(100000D)
				.withAddress(address).withBuilding(saveBuildingOne).build();
		flatDao.save(flatOne);
		FlatEntity flatTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(200000D).withAddress(address).withBuilding(saveBuildingOne).build();
		flatDao.save(flatTwo);
		FlatEntity flatThree = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(300000D).withAddress(address).withBuilding(saveBuildingOne).build();
		flatDao.save(flatThree);

		// when
		Double avgPrice = flatRepositoryImpl.findAverageFlatPriceInBuilding(null);

		// then
		assertEquals(null, avgPrice);
	}

	@Test
	public void shouldFindAverageFlatPriceInBuildingWhenHaveTwoBuilding() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		StatusEntity status = new StatusEntity().builder().withStatusName("Book").build();
		StatusEntity saveStatus = statusDao.save(status);

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);

		FlatEntity flatOne = new FlatEntity().builder().withFlatStatus(status).withAreaFlat(35.75D).withPrice(100000D)
				.withAddress(address).withBuilding(saveBuildingOne).build();
		flatDao.save(flatOne);
		FlatEntity flatTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(200000D).withAddress(address).withBuilding(saveBuildingOne).build();
		flatDao.save(flatTwo);
		FlatEntity flatThree = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(300000D).withAddress(address).withBuilding(saveBuildingOne).build();
		flatDao.save(flatThree);

		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the beach").withNumberFloor(new Integer(8))
				.withNumberFlat(new Integer(14)).withElevator(true).withLocation("Radom").build();
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);

		FlatEntity flatOneInBuildingTwo = new FlatEntity().builder().withFlatStatus(status).withAreaFlat(35.75D)
				.withPrice(100D).withAddress(address).withBuilding(saveBuildingTwo).build();
		FlatEntity saveFlatOneInBuildingTwo = flatDao.save(flatOneInBuildingTwo);
		FlatEntity flatTwoInBuildingTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(200D).withAddress(address).withBuilding(saveBuildingTwo).build();
		FlatEntity saveFlatTwoInBuildingTwo = flatDao.save(flatTwoInBuildingTwo);
		FlatEntity flatThreeInBuildingTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(300D).withAddress(address).withBuilding(saveBuildingTwo).build();
		FlatEntity saveFlatThreeInBuildingTwo = flatDao.save(flatThreeInBuildingTwo);
		Double avgPriceBuildingTwo = (saveFlatOneInBuildingTwo.getPrice() + saveFlatTwoInBuildingTwo.getPrice()
				+ saveFlatThreeInBuildingTwo.getPrice()) / 3;

		// when
		Double avgPrice = flatRepositoryImpl.findAverageFlatPriceInBuilding(saveBuildingTwo);

		// then
		assertEquals(avgPriceBuildingTwo, avgPrice);
	}

	@Test
	public void shouldFindAllFlatsForDisablePeopleForBuildingWithElevator() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		StatusEntity status = new StatusEntity().builder().withStatusName("Book").build();
		StatusEntity saveStatus = statusDao.save(status);

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(false).withLocation("Rataje").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);

		FlatEntity flatOne = new FlatEntity().builder().withFlatStatus(status).withAreaFlat(35.75D).withPrice(100000D)
				.withAddress(address).withBuilding(saveBuildingOne).withFloor(new Integer(1)).build();
		flatDao.save(flatOne);
		FlatEntity flatTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(200000D).withAddress(address).withBuilding(saveBuildingOne).withFloor(new Integer(2))
				.build();
		flatDao.save(flatTwo);
		FlatEntity flatThree = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(300000D).withAddress(address).withBuilding(saveBuildingOne).withFloor(new Integer(4))
				.build();
		flatDao.save(flatThree);

		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the beach").withNumberFloor(new Integer(8))
				.withNumberFlat(new Integer(14)).withElevator(true).withLocation("Radom").build();
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);

		FlatEntity flatOneInBuildingTwo = new FlatEntity().builder().withFlatStatus(status).withAreaFlat(35.75D)
				.withPrice(100D).withAddress(address).withBuilding(saveBuildingTwo).withFloor(new Integer(1)).build();
		FlatEntity saveFlatOneInBuildingTwo = flatDao.save(flatOneInBuildingTwo);
		FlatEntity flatTwoInBuildingTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(200D).withAddress(address).withBuilding(saveBuildingTwo).withFloor(new Integer(0)).build();
		FlatEntity saveFlatTwoInBuildingTwo = flatDao.save(flatTwoInBuildingTwo);
		FlatEntity flatThreeInBuildingTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(300D).withAddress(address).withBuilding(saveBuildingTwo).withFloor(new Integer(9)).build();
		FlatEntity saveFlatThreeInBuildingTwo = flatDao.save(flatThreeInBuildingTwo);

		List<FlatEntity> goodFlats = new ArrayList<>();
		goodFlats.add(saveFlatOneInBuildingTwo);
		goodFlats.add(saveFlatTwoInBuildingTwo);
		goodFlats.add(saveFlatThreeInBuildingTwo);

		// when
		List<FlatEntity> flats = flatRepositoryImpl.findAllFlatSuitableForDisabilitiesPeople();

		// then
		assertEquals(goodFlats.size(), flats.size());
		assertEquals(goodFlats.get(0), flats.get(0));
		assertEquals(goodFlats.get(1), flats.get(1));
		assertEquals(goodFlats.get(2), flats.get(2));
	}

	@Test
	public void shouldFindAllFlatsForDisablePeopleForFlatOnGroundFloor() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		StatusEntity status = new StatusEntity().builder().withStatusName("Book").build();
		StatusEntity saveStatus = statusDao.save(status);

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(false).withLocation("Rataje").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);

		FlatEntity flatOne = new FlatEntity().builder().withFlatStatus(status).withAreaFlat(35.75D).withPrice(100000D)
				.withAddress(address).withBuilding(saveBuildingOne).withFloor(new Integer(1)).build();
		flatDao.save(flatOne);
		FlatEntity flatTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(200000D).withAddress(address).withBuilding(saveBuildingOne).withFloor(new Integer(0))
				.build();
		FlatEntity saveFlatTwo = flatDao.save(flatTwo);
		FlatEntity flatThree = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(300000D).withAddress(address).withBuilding(saveBuildingOne).withFloor(new Integer(4))
				.build();
		flatDao.save(flatThree);

		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the beach").withNumberFloor(new Integer(8))
				.withNumberFlat(new Integer(14)).withElevator(false).withLocation("Radom").build();
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);

		FlatEntity flatOneInBuildingTwo = new FlatEntity().builder().withFlatStatus(status).withAreaFlat(35.75D)
				.withPrice(100D).withAddress(address).withBuilding(saveBuildingTwo).withFloor(new Integer(1)).build();
		flatDao.save(flatOneInBuildingTwo);
		FlatEntity flatTwoInBuildingTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(200D).withAddress(address).withBuilding(saveBuildingTwo).withFloor(new Integer(7)).build();
		flatDao.save(flatTwoInBuildingTwo);
		FlatEntity flatThreeInBuildingTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(300D).withAddress(address).withBuilding(saveBuildingTwo).withFloor(new Integer(0)).build();
		FlatEntity saveFlatThreeInBuildingTwo = flatDao.save(flatThreeInBuildingTwo);

		List<FlatEntity> goodFlats = new ArrayList<>();
		goodFlats.add(saveFlatTwo);
		goodFlats.add(saveFlatThreeInBuildingTwo);

		// when
		List<FlatEntity> flats = flatRepositoryImpl.findAllFlatSuitableForDisabilitiesPeople();

		// then
		assertEquals(goodFlats.size(), flats.size());
		assertEquals(goodFlats.get(0), flats.get(0));
		assertEquals(goodFlats.get(1), flats.get(1));
	}

	@Test
	public void shouldFindAllFlatsForDisablePeople() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		StatusEntity status = new StatusEntity().builder().withStatusName("Book").build();
		StatusEntity saveStatus = statusDao.save(status);

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(false).withLocation("Rataje").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);

		FlatEntity flatOne = new FlatEntity().builder().withFlatStatus(status).withAreaFlat(35.75D).withPrice(100000D)
				.withAddress(address).withBuilding(saveBuildingOne).withFloor(new Integer(0)).build();
		FlatEntity saveFlatOne = flatDao.save(flatOne);
		FlatEntity flatTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(200000D).withAddress(address).withBuilding(saveBuildingOne).withFloor(new Integer(1))
				.build();
		flatDao.save(flatTwo);
		FlatEntity flatThree = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(300000D).withAddress(address).withBuilding(saveBuildingOne).withFloor(new Integer(4))
				.build();
		flatDao.save(flatThree);

		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the beach").withNumberFloor(new Integer(8))
				.withNumberFlat(new Integer(14)).withElevator(true).withLocation("Radom").build();
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);

		FlatEntity flatOneInBuildingTwo = new FlatEntity().builder().withFlatStatus(status).withAreaFlat(35.75D)
				.withPrice(100D).withAddress(address).withBuilding(saveBuildingTwo).withFloor(new Integer(1)).build();
		FlatEntity saveFlatOneInBuildingTwo = flatDao.save(flatOneInBuildingTwo);
		FlatEntity flatTwoInBuildingTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(200D).withAddress(address).withBuilding(saveBuildingTwo).withFloor(new Integer(0)).build();
		FlatEntity saveFlatTwoInBuildingTwo = flatDao.save(flatTwoInBuildingTwo);
		FlatEntity flatThreeInBuildingTwo = new FlatEntity().builder().withFlatStatus(saveStatus).withAreaFlat(35.75D)
				.withPrice(300D).withAddress(address).withBuilding(saveBuildingTwo).withFloor(new Integer(9)).build();
		FlatEntity saveFlatThreeInBuildingTwo = flatDao.save(flatThreeInBuildingTwo);

		List<FlatEntity> goodFlats = new ArrayList<>();
		goodFlats.add(saveFlatOne);
		goodFlats.add(saveFlatOneInBuildingTwo);
		goodFlats.add(saveFlatTwoInBuildingTwo);
		goodFlats.add(saveFlatThreeInBuildingTwo);

		// when
		List<FlatEntity> flats = flatRepositoryImpl.findAllFlatSuitableForDisabilitiesPeople();

		// then
		assertEquals(goodFlats.size(), flats.size());
		assertEquals(goodFlats.get(0), flats.get(0));
		assertEquals(goodFlats.get(1), flats.get(1));
		assertEquals(goodFlats.get(2), flats.get(2));
		assertEquals(goodFlats.get(3), flats.get(3));
	}

}
