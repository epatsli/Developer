package com.capgemini.dao;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.domain.Address;
import com.capgemini.domain.BuildingEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BuildingDaoTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private BuildingDao buildingDao;

	@Test
	public void shouldCreateBuilding() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		BuildingEntity building = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(35)).withElevator(true).withAddress(address).build();

		// when
		BuildingEntity saveBuilding = buildingDao.save(building);

		// then
		assertEquals(building, saveBuilding);
	}

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateBuildingWithoutNumberFloor() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		// when
		new BuildingEntity().builder().withDescription("The building is located on the river.")
				.withNumberFlat(new Integer(35)).withElevator(true).withAddress(address).build();

		// then
	}

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateBuildingWithoutNumberFlat() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		// when
		new BuildingEntity().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withElevator(true).withAddress(address).build();

		// then
	}

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateBuildingWithoutElevator() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		// when
		new BuildingEntity().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(13)).withAddress(address).build();

		// then
	}

	@Test
	public void shouldThrownException() {

		assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
			throw new RuntimeException("Incorrect parameter. This building can't be created.");
		}).withMessage("Incorrect parameter. This building can't be created.")
				.withStackTraceContaining("RuntimeException");
	}

	@Test
	public void shouldFindBuildingById() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withAddress(address).withNumberFlat(new Integer(27)).withElevator(true).withAddress(address).build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withAddress(address).withNumberFlat(new Integer(32)).withElevator(true)
				.withAddress(address).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withAddress(address).withNumberFlat(new Integer(64))
				.withElevator(true).withAddress(address).build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		BuildingEntity findBuilding = buildingDao.findById(saveBuildingOne.getId());

		// then
		Assert.assertNotNull(findBuilding);
		assertEquals(saveBuildingOne, findBuilding);
	}

}
