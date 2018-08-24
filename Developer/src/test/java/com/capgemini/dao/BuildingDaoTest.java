package com.capgemini.dao;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
	public void shouldFindBuildingByIdWhenBuildingWasFirstSave() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withAddress(address).build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withAddress(address).withNumberFlat(new Integer(32)).withElevator(true)
				.build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withAddress(address).withNumberFlat(new Integer(64))
				.withElevator(true).build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		BuildingEntity findBuilding = buildingDao.findById(saveBuildingOne.getId());

		// then
		Assert.assertNotNull(findBuilding);
		assertEquals(saveBuildingOne, findBuilding);
	}

	@Test
	public void shouldFindBuildingByIdWhenBuildingWasInsideSave() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withAddress(address).build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true).withAddress(address)
				.build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(address).build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		BuildingEntity findBuilding = buildingDao.findById(saveBuildingTwo.getId());

		// then
		Assert.assertNotNull(findBuilding);
		assertEquals(saveBuildingTwo, findBuilding);
	}

	@Test
	public void shouldFindBuildingByIdWhenBuildingWasLastSave() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withAddress(address).build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withAddress(address).withNumberFlat(new Integer(32)).withElevator(true)
				.withAddress(address).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(address).build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		BuildingEntity findBuilding = buildingDao.findById(saveBuildingThree.getId());

		// then
		Assert.assertNotNull(findBuilding);
		assertEquals(saveBuildingThree, findBuilding);
	}

	@Test
	public void shouldFindBuildingByIdWhenIdIsNull() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withAddress(address).build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true).withAddress(address)
				.build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(address).build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		BuildingEntity findBuilding = buildingDao.findById(null);

		// then
		Assert.assertNull(findBuilding);
	}

	@Test
	public void shouldFindBuildingByAddressWhenBuildingWasFirstSave() {

		// given
		Address addressOne = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		Address addressTwo = new Address().builder().withStreet("Krotka").withHouseNumber("31/4").withCity("Poznan")
				.withPostCode("51-258").build();
		Address addressThree = new Address().builder().withStreet("Szeroka").withHouseNumber("52").withCity("Krakow")
				.withPostCode("45-212").build();

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withAddress(addressOne).build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withAddress(addressTwo).withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(addressThree).build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		List<BuildingEntity> findBuilding = buildingDao.findByAddress(saveBuildingOne.getAddress());

		// then
		Assert.assertNotNull(findBuilding);
		Assert.assertEquals(1, findBuilding.size());
		assertEquals(saveBuildingOne.getAddress(), findBuilding.get(0).getAddress());
		assertEquals(saveBuildingOne.getDescription(), findBuilding.get(0).getDescription());
		assertEquals(saveBuildingOne.getNumberFlat(), findBuilding.get(0).getNumberFlat());
		assertEquals(saveBuildingOne.getElevator(), findBuilding.get(0).getElevator());
		assertEquals(saveBuildingOne.getNumberFloor(), findBuilding.get(0).getNumberFloor());
	}

	@Test
	public void shouldFindBuildingByAddressWhenBuildingWasInsideSave() {

		// given
		Address addressOne = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		Address addressTwo = new Address().builder().withStreet("Krotka").withHouseNumber("31/4").withCity("Poznan")
				.withPostCode("51-258").build();
		Address addressThree = new Address().builder().withStreet("Szeroka").withHouseNumber("52").withCity("Krakow")
				.withPostCode("45-212").build();

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withAddress(addressOne).build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withAddress(addressTwo).withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(addressThree).build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		List<BuildingEntity> findBuilding = buildingDao.findByAddress(saveBuildingTwo.getAddress());

		// then
		Assert.assertNotNull(findBuilding);
		Assert.assertEquals(1, findBuilding.size());
		assertEquals(saveBuildingTwo.getAddress(), findBuilding.get(0).getAddress());
		assertEquals(saveBuildingTwo.getDescription(), findBuilding.get(0).getDescription());
		assertEquals(saveBuildingTwo.getNumberFlat(), findBuilding.get(0).getNumberFlat());
		assertEquals(saveBuildingTwo.getElevator(), findBuilding.get(0).getElevator());
		assertEquals(saveBuildingTwo.getNumberFloor(), findBuilding.get(0).getNumberFloor());
	}

	@Test
	public void shouldFindBuildingByAddressWhenBuildingWasLastSave() {

		// given
		Address addressOne = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		Address addressTwo = new Address().builder().withStreet("Krotka").withHouseNumber("31/4").withCity("Poznan")
				.withPostCode("51-258").build();
		Address addressThree = new Address().builder().withStreet("Szeroka").withHouseNumber("52").withCity("Krakow")
				.withPostCode("45-212").build();

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withAddress(addressOne).build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withAddress(addressTwo).withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(addressThree).build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		List<BuildingEntity> findBuilding = buildingDao.findByAddress(saveBuildingThree.getAddress());

		// then
		Assert.assertNotNull(findBuilding);
		Assert.assertEquals(1, findBuilding.size());
		assertEquals(saveBuildingThree.getAddress(), findBuilding.get(0).getAddress());
		assertEquals(saveBuildingThree.getDescription(), findBuilding.get(0).getDescription());
		assertEquals(saveBuildingThree.getNumberFlat(), findBuilding.get(0).getNumberFlat());
		assertEquals(saveBuildingThree.getElevator(), findBuilding.get(0).getElevator());
		assertEquals(saveBuildingThree.getNumberFloor(), findBuilding.get(0).getNumberFloor());
	}

	@Test
	public void shouldFindBuildingByNullAddress() {

		// given
		Address addressOne = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		Address addressTwo = new Address().builder().withStreet("Krotka").withHouseNumber("31/4").withCity("Poznan")
				.withPostCode("51-258").build();
		Address addressThree = new Address().builder().withStreet("Szeroka").withHouseNumber("52").withCity("Krakow")
				.withPostCode("45-212").build();

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withAddress(addressOne).build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withAddress(addressTwo).withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(addressThree).build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		List<BuildingEntity> findBuilding = buildingDao.findByAddress(null);

		// then
		assertTrue(findBuilding.isEmpty());
	}

	@Test
	public void shouldFindBuildingByAddress() {

		// given
		Address addressOne = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		Address addressTwo = new Address().builder().withStreet("Krotka").withHouseNumber("31/4").withCity("Poznan")
				.withPostCode("51-258").build();
		Address addressThree = new Address().builder().withStreet("Szeroka").withHouseNumber("52").withCity("Krakow")
				.withPostCode("45-212").build();

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withAddress(addressOne).build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withAddress(addressTwo).withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(addressOne).build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		List<BuildingEntity> findBuilding = buildingDao.findByAddress(addressOne);

		// then
		Assert.assertNotNull(findBuilding);
		Assert.assertEquals(2, findBuilding.size());
		assertEquals(addressOne, findBuilding.get(0).getAddress());
		assertEquals(saveBuildingOne.getDescription(), findBuilding.get(0).getDescription());
		assertEquals(saveBuildingOne.getNumberFlat(), findBuilding.get(0).getNumberFlat());
		assertEquals(saveBuildingOne.getElevator(), findBuilding.get(0).getElevator());
		assertEquals(saveBuildingOne.getNumberFloor(), findBuilding.get(0).getNumberFloor());
		assertEquals(addressOne, findBuilding.get(1).getAddress());
		assertEquals(saveBuildingThree.getDescription(), findBuilding.get(1).getDescription());
		assertEquals(saveBuildingThree.getNumberFlat(), findBuilding.get(1).getNumberFlat());
		assertEquals(saveBuildingThree.getElevator(), findBuilding.get(1).getElevator());
		assertEquals(saveBuildingThree.getNumberFloor(), findBuilding.get(1).getNumberFloor());
	}

	@Test
	public void shouldRemoveBuildingByIdWhenBuildingWasFirstSave() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withAddress(address).build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withAddress(address).withNumberFlat(new Integer(32)).withElevator(true)
				.build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withAddress(address).withNumberFlat(new Integer(64))
				.withElevator(true).build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		buildingDao.removeById(saveBuildingOne.getId());

		// then
		Assert.assertNull(buildingDao.findById(saveBuildingOne.getId()));
		Assert.assertEquals(2, buildingDao.count());
		assertEquals(buildingTwo.getAddress(), saveBuildingTwo.getAddress());
		assertEquals(buildingTwo.getDescription(), saveBuildingTwo.getDescription());
		assertEquals(buildingTwo.getNumberFlat(), saveBuildingTwo.getNumberFlat());
		assertEquals(buildingTwo.getElevator(), saveBuildingTwo.getElevator());
		assertEquals(buildingTwo.getNumberFloor(), saveBuildingTwo.getNumberFloor());
		assertEquals(buildingThree.getAddress(), saveBuildingThree.getAddress());
		assertEquals(buildingThree.getDescription(), saveBuildingThree.getDescription());
		assertEquals(buildingThree.getNumberFlat(), saveBuildingThree.getNumberFlat());
		assertEquals(buildingThree.getElevator(), saveBuildingThree.getElevator());
		assertEquals(buildingThree.getNumberFloor(), saveBuildingThree.getNumberFloor());
	}

	@Test
	public void shouldRemoveBuildingByIdWhenBuildingWasInsideSave() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withAddress(address).build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withAddress(address).withNumberFlat(new Integer(32)).withElevator(true)
				.build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withAddress(address).withNumberFlat(new Integer(64))
				.withElevator(true).build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		buildingDao.removeById(saveBuildingTwo.getId());

		// then
		Assert.assertNull(buildingDao.findById(saveBuildingTwo.getId()));
		Assert.assertEquals(2, buildingDao.count());
		assertEquals(buildingOne.getAddress(), saveBuildingOne.getAddress());
		assertEquals(buildingOne.getDescription(), saveBuildingOne.getDescription());
		assertEquals(buildingOne.getNumberFlat(), saveBuildingOne.getNumberFlat());
		assertEquals(buildingOne.getElevator(), saveBuildingOne.getElevator());
		assertEquals(buildingOne.getNumberFloor(), saveBuildingOne.getNumberFloor());
		assertEquals(buildingThree.getAddress(), saveBuildingThree.getAddress());
		assertEquals(buildingThree.getDescription(), saveBuildingThree.getDescription());
		assertEquals(buildingThree.getNumberFlat(), saveBuildingThree.getNumberFlat());
		assertEquals(buildingThree.getElevator(), saveBuildingThree.getElevator());
		assertEquals(buildingThree.getNumberFloor(), saveBuildingThree.getNumberFloor());
	}

	@Test
	public void shouldRemoveBuildingByIdWhenBuildingWasLastSave() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withAddress(address).build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withAddress(address).withNumberFlat(new Integer(32)).withElevator(true)
				.build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withAddress(address).withNumberFlat(new Integer(64))
				.withElevator(true).build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		buildingDao.removeById(saveBuildingThree.getId());

		// then
		Assert.assertNull(buildingDao.findById(saveBuildingThree.getId()));
		Assert.assertEquals(2, buildingDao.count());
		assertEquals(buildingOne.getAddress(), saveBuildingOne.getAddress());
		assertEquals(buildingOne.getDescription(), saveBuildingOne.getDescription());
		assertEquals(buildingOne.getNumberFlat(), saveBuildingOne.getNumberFlat());
		assertEquals(buildingOne.getElevator(), saveBuildingOne.getElevator());
		assertEquals(buildingOne.getNumberFloor(), saveBuildingOne.getNumberFloor());
		assertEquals(buildingTwo.getAddress(), saveBuildingTwo.getAddress());
		assertEquals(buildingTwo.getDescription(), saveBuildingTwo.getDescription());
		assertEquals(buildingTwo.getNumberFlat(), saveBuildingTwo.getNumberFlat());
		assertEquals(buildingTwo.getElevator(), saveBuildingTwo.getElevator());
		assertEquals(buildingTwo.getNumberFloor(), saveBuildingTwo.getNumberFloor());
	}

	@Test
	public void shouldRemoveBuildingByNullId() {

		// given
		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withAddress(address).build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withAddress(address).withNumberFlat(new Integer(32)).withElevator(true)
				.build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withAddress(address).withNumberFlat(new Integer(64))
				.withElevator(true).build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		buildingDao.removeById(null);

		// then

		Assert.assertEquals(3, buildingDao.count());
		assertEquals(buildingOne.getAddress(), saveBuildingOne.getAddress());
		assertEquals(buildingOne.getDescription(), saveBuildingOne.getDescription());
		assertEquals(buildingOne.getNumberFlat(), saveBuildingOne.getNumberFlat());
		assertEquals(buildingOne.getElevator(), saveBuildingOne.getElevator());
		assertEquals(buildingOne.getNumberFloor(), saveBuildingOne.getNumberFloor());
		assertEquals(buildingTwo.getAddress(), saveBuildingTwo.getAddress());
		assertEquals(buildingTwo.getDescription(), saveBuildingTwo.getDescription());
		assertEquals(buildingTwo.getNumberFlat(), saveBuildingTwo.getNumberFlat());
		assertEquals(buildingTwo.getElevator(), saveBuildingTwo.getElevator());
		assertEquals(buildingTwo.getNumberFloor(), saveBuildingTwo.getNumberFloor());
		assertEquals(buildingThree.getAddress(), saveBuildingThree.getAddress());
		assertEquals(buildingThree.getDescription(), saveBuildingThree.getDescription());
		assertEquals(buildingThree.getNumberFlat(), saveBuildingThree.getNumberFlat());
		assertEquals(buildingThree.getElevator(), saveBuildingThree.getElevator());
		assertEquals(buildingThree.getNumberFloor(), saveBuildingThree.getNumberFloor());
	}

	@Test
	public void shouldRemoveBuildingByAddressWhenBuildingWasFirstSave() {

		// given
		Address addressOne = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		Address addressTwo = new Address().builder().withStreet("Krotka").withHouseNumber("31/4").withCity("Poznan")
				.withPostCode("51-258").build();
		Address addressThree = new Address().builder().withStreet("Szeroka").withHouseNumber("52").withCity("Krakow")
				.withPostCode("45-212").build();

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withAddress(addressOne).build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withAddress(addressTwo).withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(addressThree).build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		buildingDao.removeByAddress(addressOne);

		// then
		Assert.assertNull(buildingDao.findById(saveBuildingOne.getId()));
		Assert.assertEquals(2, buildingDao.count());
		assertEquals(buildingTwo.getAddress(), saveBuildingTwo.getAddress());
		assertEquals(buildingTwo.getDescription(), saveBuildingTwo.getDescription());
		assertEquals(buildingTwo.getNumberFlat(), saveBuildingTwo.getNumberFlat());
		assertEquals(buildingTwo.getElevator(), saveBuildingTwo.getElevator());
		assertEquals(buildingTwo.getNumberFloor(), saveBuildingTwo.getNumberFloor());
		assertEquals(buildingThree.getAddress(), saveBuildingThree.getAddress());
		assertEquals(buildingThree.getDescription(), saveBuildingThree.getDescription());
		assertEquals(buildingThree.getNumberFlat(), saveBuildingThree.getNumberFlat());
		assertEquals(buildingThree.getElevator(), saveBuildingThree.getElevator());
		assertEquals(buildingThree.getNumberFloor(), saveBuildingThree.getNumberFloor());
	}

	@Test
	public void shouldRemoveBuildingByAddressWhenBuildingWasInsideSave() {

		// given
		Address addressOne = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		Address addressTwo = new Address().builder().withStreet("Krotka").withHouseNumber("31/4").withCity("Poznan")
				.withPostCode("51-258").build();
		Address addressThree = new Address().builder().withStreet("Szeroka").withHouseNumber("52").withCity("Krakow")
				.withPostCode("45-212").build();

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withAddress(addressOne).build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withAddress(addressTwo).withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(addressThree).build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		buildingDao.removeByAddress(addressTwo);

		// then
		Assert.assertNull(buildingDao.findById(saveBuildingTwo.getId()));
		Assert.assertEquals(2, buildingDao.count());
		assertEquals(buildingOne.getAddress(), saveBuildingOne.getAddress());
		assertEquals(buildingOne.getDescription(), saveBuildingOne.getDescription());
		assertEquals(buildingOne.getNumberFlat(), saveBuildingOne.getNumberFlat());
		assertEquals(buildingOne.getElevator(), saveBuildingOne.getElevator());
		assertEquals(buildingOne.getNumberFloor(), saveBuildingOne.getNumberFloor());
		assertEquals(buildingThree.getAddress(), saveBuildingThree.getAddress());
		assertEquals(buildingThree.getDescription(), saveBuildingThree.getDescription());
		assertEquals(buildingThree.getNumberFlat(), saveBuildingThree.getNumberFlat());
		assertEquals(buildingThree.getElevator(), saveBuildingThree.getElevator());
		assertEquals(buildingThree.getNumberFloor(), saveBuildingThree.getNumberFloor());
	}

	@Test
	public void shouldRemoveBuildingByAddressWhenBuildingWasLastSave() {

		// given
		Address addressOne = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		Address addressTwo = new Address().builder().withStreet("Krotka").withHouseNumber("31/4").withCity("Poznan")
				.withPostCode("51-258").build();
		Address addressThree = new Address().builder().withStreet("Szeroka").withHouseNumber("52").withCity("Krakow")
				.withPostCode("45-212").build();

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withAddress(addressOne).build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withAddress(addressTwo).withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(addressThree).build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		buildingDao.removeByAddress(addressThree);

		// then
		Assert.assertNull(buildingDao.findById(saveBuildingThree.getId()));
		Assert.assertEquals(2, buildingDao.count());
		assertEquals(buildingOne.getAddress(), saveBuildingOne.getAddress());
		assertEquals(buildingOne.getDescription(), saveBuildingOne.getDescription());
		assertEquals(buildingOne.getNumberFlat(), saveBuildingOne.getNumberFlat());
		assertEquals(buildingOne.getElevator(), saveBuildingOne.getElevator());
		assertEquals(buildingOne.getNumberFloor(), saveBuildingOne.getNumberFloor());
		assertEquals(buildingTwo.getAddress(), saveBuildingTwo.getAddress());
		assertEquals(buildingTwo.getDescription(), saveBuildingTwo.getDescription());
		assertEquals(buildingTwo.getNumberFlat(), saveBuildingTwo.getNumberFlat());
		assertEquals(buildingTwo.getElevator(), saveBuildingTwo.getElevator());
		assertEquals(buildingTwo.getNumberFloor(), saveBuildingTwo.getNumberFloor());
	}

	@Test
	public void shouldRemoveBuildingByAddressWhenTwoBuildingHaveTheSameAddress() {

		// given
		Address addressOne = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		Address addressTwo = new Address().builder().withStreet("Krotka").withHouseNumber("31/4").withCity("Poznan")
				.withPostCode("51-258").build();

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withAddress(addressOne).build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withAddress(addressTwo).withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(addressOne).build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		buildingDao.removeByAddress(addressOne);

		// then
		Assert.assertNull(buildingDao.findById(saveBuildingOne.getId()));
		Assert.assertNull(buildingDao.findById(saveBuildingThree.getId()));
		Assert.assertEquals(1, buildingDao.count());
		assertEquals(buildingTwo.getAddress(), saveBuildingTwo.getAddress());
		assertEquals(buildingTwo.getDescription(), saveBuildingTwo.getDescription());
		assertEquals(buildingTwo.getNumberFlat(), saveBuildingTwo.getNumberFlat());
		assertEquals(buildingTwo.getElevator(), saveBuildingTwo.getElevator());
		assertEquals(buildingTwo.getNumberFloor(), saveBuildingTwo.getNumberFloor());
	}

	@Test
	public void shouldCantRemoveBuildingByNullAddress() {

		// given
		Address addressOne = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		Address addressTwo = new Address().builder().withStreet("Krotka").withHouseNumber("31/4").withCity("Poznan")
				.withPostCode("51-258").build();
		Address addressThree = new Address().builder().withStreet("Szeroka").withHouseNumber("52").withCity("Krakow")
				.withPostCode("45-212").build();

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withAddress(addressOne).build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withAddress(addressTwo).withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(addressThree).build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		buildingDao.removeByAddress(null);

		// then
		Assert.assertEquals(3, buildingDao.count());
		assertEquals(buildingOne, saveBuildingOne);
		assertEquals(buildingTwo, saveBuildingTwo);
		assertEquals(buildingThree, saveBuildingThree);
	}

}
