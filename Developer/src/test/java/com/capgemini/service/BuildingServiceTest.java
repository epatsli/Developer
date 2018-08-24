package com.capgemini.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;

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

import com.capgemini.types.AddressMap;
import com.capgemini.types.BuildingTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BuildingServiceTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private BuildingService buildingService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private FlatService flatService;

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateBuildingWithoutNumberFlat() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		// when
		new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withElevator(true).withAddress(address).build();

		// then

	}

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateBuildingWithoutNumberFloor() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		// when
		new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFlat(new Integer(35)).withElevator(true).withAddress(address).build();

		// then
	}

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateBuildingWithoutElevator() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		// when
		new BuildingTO().builder().withDescription("The building is located on the river.")
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
	public void shouldCreateBuilding() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		BuildingTO building = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true).withAddress(address)
				.build();

		// when
		BuildingTO saveBuilding = buildingService.saveBuilding(building);

		// then
		assertEquals(building.getNumberFloor(), saveBuilding.getNumberFloor());
		assertEquals(building.getDescription(), saveBuilding.getDescription());
		assertEquals(building.getElevator(), saveBuilding.getElevator());
	}

	@Test
	public void shouldFindBuildingByIdWhenBuildingWasFirstSave() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true).withAddress(address)
				.build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true).withAddress(address)
				.build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(address).build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		BuildingTO findBuilding = buildingService.findById(saveBuildingOne.getId());

		// then
		Assert.assertNotNull(findBuilding);
		assertEquals(saveBuildingOne.getNumberFloor(), findBuilding.getNumberFloor());
		assertEquals(saveBuildingOne.getDescription(), findBuilding.getDescription());
		assertEquals(saveBuildingOne.getElevator(), findBuilding.getElevator());
	}

	@Test
	public void shouldFindBuildingByIdWhenBuildingWasInsideSave() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true).withAddress(address)
				.build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true).withAddress(address)
				.build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(address).build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		BuildingTO findBuilding = buildingService.findById(saveBuildingTwo.getId());

		// then
		Assert.assertNotNull(findBuilding);
		assertEquals(saveBuildingTwo.getNumberFloor(), findBuilding.getNumberFloor());
		assertEquals(saveBuildingTwo.getDescription(), findBuilding.getDescription());
		assertEquals(saveBuildingTwo.getElevator(), findBuilding.getElevator());
	}

	@Test
	public void shouldFindBuildingByIdWhenBuildingWasLastSave() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true).withAddress(address)
				.build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true).withAddress(address)
				.build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(address).build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		BuildingTO findBuilding = buildingService.findById(saveBuildingThree.getId());

		// then
		Assert.assertNotNull(findBuilding);
		assertEquals(saveBuildingThree.getNumberFloor(), findBuilding.getNumberFloor());
		assertEquals(saveBuildingThree.getDescription(), findBuilding.getDescription());
		assertEquals(saveBuildingThree.getElevator(), findBuilding.getElevator());
	}

	@Test
	public void shouldCantFindBuildingByIdNull() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true).withAddress(address)
				.build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true).withAddress(address)
				.build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(address).build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		BuildingTO findBuilding = buildingService.findById(null);

		// then
		Assert.assertNull(findBuilding);
	}

	@Test
	public void shouldFindBuildingByAddressWhenBuildingWasFirstSave() {

		// given
		AddressMap addressOne = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		AddressMap addressTwo = new AddressMap().builder().withStreet("Krotka").withHouseNumber("31/4")
				.withCity("Poznan").withPostCode("51-258").build();
		AddressMap addressThree = new AddressMap().builder().withStreet("Szeroka").withHouseNumber("52")
				.withCity("Krakow").withPostCode("45-212").build();

		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withAddress(addressOne).build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withAddress(addressTwo).build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(addressThree).build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		List<BuildingTO> findBuilding = buildingService.findByAddress(saveBuildingOne.getAddress());

		// then
		Assert.assertNotNull(findBuilding);
		assertEquals(saveBuildingOne.getNumberFloor(), findBuilding.get(0).getNumberFloor());
		assertEquals(saveBuildingOne.getDescription(), findBuilding.get(0).getDescription());
		assertEquals(saveBuildingOne.getElevator(), findBuilding.get(0).getElevator());
	}

	@Test
	public void shouldFindBuildingByAddressWhenBuildingWasInsideSave() {

		// given
		AddressMap addressOne = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		AddressMap addressTwo = new AddressMap().builder().withStreet("Krotka").withHouseNumber("31/4")
				.withCity("Poznan").withPostCode("51-258").build();
		AddressMap addressThree = new AddressMap().builder().withStreet("Szeroka").withHouseNumber("52")
				.withCity("Krakow").withPostCode("45-212").build();

		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withAddress(addressOne).build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withAddress(addressTwo).build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(addressThree).build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		List<BuildingTO> findBuilding = buildingService.findByAddress(saveBuildingTwo.getAddress());

		// then
		Assert.assertNotNull(findBuilding);
		assertEquals(saveBuildingTwo.getNumberFloor(), findBuilding.get(0).getNumberFloor());
		assertEquals(saveBuildingTwo.getDescription(), findBuilding.get(0).getDescription());
		assertEquals(saveBuildingTwo.getElevator(), findBuilding.get(0).getElevator());
	}

	@Test
	public void shouldFindBuildingByAddressWhenBuildingWasLastSave() {

		// given
		AddressMap addressOne = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		AddressMap addressTwo = new AddressMap().builder().withStreet("Krotka").withHouseNumber("31/4")
				.withCity("Poznan").withPostCode("51-258").build();
		AddressMap addressThree = new AddressMap().builder().withStreet("Szeroka").withHouseNumber("52")
				.withCity("Krakow").withPostCode("45-212").build();

		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withAddress(addressOne).build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withAddress(addressTwo).build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(addressThree).build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		List<BuildingTO> findBuilding = buildingService.findByAddress(saveBuildingThree.getAddress());

		// then
		Assert.assertNotNull(findBuilding);
		assertEquals(saveBuildingThree.getNumberFloor(), findBuilding.get(0).getNumberFloor());
		assertEquals(saveBuildingThree.getDescription(), findBuilding.get(0).getDescription());
		assertEquals(saveBuildingThree.getElevator(), findBuilding.get(0).getElevator());
	}

	@Test(expected = NullPointerException.class)
	public void shouldCantFindBuildingByAddressNull() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true).withAddress(address)
				.build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true).withAddress(address)
				.build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(address).build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		List<BuildingTO> findBuilding = buildingService.findByAddress(null);

		// then
	}

	@Test
	public void shouldThrownNullPointerException() {

		assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> {
			throw new NullPointerException("This address can not be empty.");
		}).withMessage("This address can not be empty.").withStackTraceContaining("NullPointerException");
	}

	@Test
	public void shouldUpdateBuilding() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		BuildingTO building = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true).withAddress(address)
				.build();

		BuildingTO saveBuilding = buildingService.saveBuilding(building);
		Integer numberFloor = new Integer(9);
		saveBuilding.setNumberFloor(numberFloor);

		// when
		BuildingTO updateBuilding = buildingService.updateBuilding(saveBuilding);

		// then
		assertEquals(new Integer(9), updateBuilding.getNumberFloor());
		assertEquals("The building is located on the river.", updateBuilding.getDescription());
		assertEquals(true, updateBuilding.getElevator());
	}

	@Test
	public void shouldRemoveBuildingByIdWhenBuildingWasFirstSave() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true).withAddress(address)
				.build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true).withAddress(address)
				.build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(address).build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		buildingService.removeById(saveBuildingOne.getId());

		// then
		Assert.assertNull(buildingService.findById(saveBuildingOne.getId()));
		assertEquals(buildingThree.getDescription(), saveBuildingThree.getDescription());
		assertEquals(buildingThree.getNumberFlat(), saveBuildingThree.getNumberFlat());
		assertEquals(buildingThree.getElevator(), saveBuildingThree.getElevator());
		assertEquals(buildingThree.getNumberFloor(), saveBuildingThree.getNumberFloor());
		assertEquals(buildingTwo.getDescription(), saveBuildingTwo.getDescription());
		assertEquals(buildingTwo.getNumberFlat(), saveBuildingTwo.getNumberFlat());
		assertEquals(buildingTwo.getElevator(), saveBuildingTwo.getElevator());
		assertEquals(buildingTwo.getNumberFloor(), saveBuildingTwo.getNumberFloor());
	}

	@Test
	public void shouldRemoveBuildingByIdWhenBuildingWasInsideSave() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true).withAddress(address)
				.build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true).withAddress(address)
				.build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(address).build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		buildingService.removeById(saveBuildingTwo.getId());

		// then
		Assert.assertNull(buildingService.findById(saveBuildingTwo.getId()));
		assertEquals(buildingOne.getDescription(), saveBuildingOne.getDescription());
		assertEquals(buildingOne.getNumberFlat(), saveBuildingOne.getNumberFlat());
		assertEquals(buildingOne.getElevator(), saveBuildingOne.getElevator());
		assertEquals(buildingOne.getNumberFloor(), saveBuildingOne.getNumberFloor());
		assertEquals(buildingThree.getDescription(), saveBuildingThree.getDescription());
		assertEquals(buildingThree.getNumberFlat(), saveBuildingThree.getNumberFlat());
		assertEquals(buildingThree.getElevator(), saveBuildingThree.getElevator());
		assertEquals(buildingThree.getNumberFloor(), saveBuildingThree.getNumberFloor());
	}

	@Test
	public void shouldRemoveBuildingByIdWhenBuildingWasLastSave() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true).withAddress(address)
				.build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true).withAddress(address)
				.build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(address).build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		buildingService.removeById(saveBuildingThree.getId());

		// then
		Assert.assertNull(buildingService.findById(saveBuildingThree.getId()));
		assertEquals(buildingOne.getDescription(), saveBuildingOne.getDescription());
		assertEquals(buildingOne.getNumberFlat(), saveBuildingOne.getNumberFlat());
		assertEquals(buildingOne.getElevator(), saveBuildingOne.getElevator());
		assertEquals(buildingOne.getNumberFloor(), saveBuildingOne.getNumberFloor());
		assertEquals(buildingTwo.getDescription(), saveBuildingTwo.getDescription());
		assertEquals(buildingTwo.getNumberFlat(), saveBuildingTwo.getNumberFlat());
		assertEquals(buildingTwo.getElevator(), saveBuildingTwo.getElevator());
		assertEquals(buildingTwo.getNumberFloor(), saveBuildingTwo.getNumberFloor());
	}

	@Test
	public void shouldCantRemoveBuildingByNullId() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true).withAddress(address)
				.build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true).withAddress(address)
				.build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(address).build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		buildingService.removeById(null);

		// then
		assertEquals(buildingOne.getDescription(), saveBuildingOne.getDescription());
		assertEquals(buildingOne.getNumberFlat(), saveBuildingOne.getNumberFlat());
		assertEquals(buildingOne.getElevator(), saveBuildingOne.getElevator());
		assertEquals(buildingOne.getNumberFloor(), saveBuildingOne.getNumberFloor());
		assertEquals(buildingTwo.getDescription(), saveBuildingTwo.getDescription());
		assertEquals(buildingTwo.getNumberFlat(), saveBuildingTwo.getNumberFlat());
		assertEquals(buildingTwo.getElevator(), saveBuildingTwo.getElevator());
		assertEquals(buildingTwo.getNumberFloor(), saveBuildingTwo.getNumberFloor());
		assertEquals(buildingThree.getDescription(), saveBuildingThree.getDescription());
		assertEquals(buildingThree.getNumberFlat(), saveBuildingThree.getNumberFlat());
		assertEquals(buildingThree.getElevator(), saveBuildingThree.getElevator());
		assertEquals(buildingThree.getNumberFloor(), saveBuildingThree.getNumberFloor());
	}

	@Test
	public void shouldRemoveBuildingByAddressWhenBuildingWasFirstSave() {

		// given
		AddressMap addressOne = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		AddressMap addressTwo = new AddressMap().builder().withStreet("Krotka").withHouseNumber("31/4")
				.withCity("Poznan").withPostCode("51-258").build();
		AddressMap addressThree = new AddressMap().builder().withStreet("Szeroka").withHouseNumber("52")
				.withCity("Krakow").withPostCode("45-212").build();

		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withAddress(addressOne).build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withAddress(addressTwo).build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(addressThree).build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		buildingService.removeByAddress(saveBuildingOne.getAddress());

		// then
		Assert.assertNull(buildingService.findById(saveBuildingOne.getId()));
		assertEquals(buildingThree.getDescription(), saveBuildingThree.getDescription());
		assertEquals(buildingThree.getNumberFlat(), saveBuildingThree.getNumberFlat());
		assertEquals(buildingThree.getElevator(), saveBuildingThree.getElevator());
		assertEquals(buildingThree.getNumberFloor(), saveBuildingThree.getNumberFloor());
		assertEquals(buildingTwo.getDescription(), saveBuildingTwo.getDescription());
		assertEquals(buildingTwo.getNumberFlat(), saveBuildingTwo.getNumberFlat());
		assertEquals(buildingTwo.getElevator(), saveBuildingTwo.getElevator());
		assertEquals(buildingTwo.getNumberFloor(), saveBuildingTwo.getNumberFloor());
	}

	@Test
	public void shouldRemoveBuildingByAddressWhenBuildingWasInsideSave() {

		// given
		AddressMap addressOne = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		AddressMap addressTwo = new AddressMap().builder().withStreet("Krotka").withHouseNumber("31/4")
				.withCity("Poznan").withPostCode("51-258").build();
		AddressMap addressThree = new AddressMap().builder().withStreet("Szeroka").withHouseNumber("52")
				.withCity("Krakow").withPostCode("45-212").build();

		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withAddress(addressOne).build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withAddress(addressTwo).build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(addressThree).build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		buildingService.removeByAddress(saveBuildingTwo.getAddress());

		// then
		Assert.assertNull(buildingService.findById(saveBuildingTwo.getId()));
		assertEquals(buildingThree.getDescription(), saveBuildingThree.getDescription());
		assertEquals(buildingThree.getNumberFlat(), saveBuildingThree.getNumberFlat());
		assertEquals(buildingThree.getElevator(), saveBuildingThree.getElevator());
		assertEquals(buildingThree.getNumberFloor(), saveBuildingThree.getNumberFloor());
		assertEquals(buildingOne.getDescription(), saveBuildingOne.getDescription());
		assertEquals(buildingOne.getNumberFlat(), saveBuildingOne.getNumberFlat());
		assertEquals(buildingOne.getElevator(), saveBuildingOne.getElevator());
		assertEquals(buildingOne.getNumberFloor(), saveBuildingOne.getNumberFloor());
	}

	@Test
	public void shouldRemoveBuildingByAddressWhenBuildingWasLastSave() {

		// given
		AddressMap addressOne = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		AddressMap addressTwo = new AddressMap().builder().withStreet("Krotka").withHouseNumber("31/4")
				.withCity("Poznan").withPostCode("51-258").build();
		AddressMap addressThree = new AddressMap().builder().withStreet("Szeroka").withHouseNumber("52")
				.withCity("Krakow").withPostCode("45-212").build();

		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withAddress(addressOne).build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withAddress(addressTwo).build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(addressThree).build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		buildingService.removeByAddress(saveBuildingThree.getAddress());

		// then
		Assert.assertNull(buildingService.findById(saveBuildingThree.getId()));
		assertEquals(buildingOne.getDescription(), saveBuildingOne.getDescription());
		assertEquals(buildingOne.getNumberFlat(), saveBuildingOne.getNumberFlat());
		assertEquals(buildingOne.getElevator(), saveBuildingOne.getElevator());
		assertEquals(buildingOne.getNumberFloor(), saveBuildingOne.getNumberFloor());
		assertEquals(buildingTwo.getDescription(), saveBuildingTwo.getDescription());
		assertEquals(buildingTwo.getNumberFlat(), saveBuildingTwo.getNumberFlat());
		assertEquals(buildingTwo.getElevator(), saveBuildingTwo.getElevator());
		assertEquals(buildingTwo.getNumberFloor(), saveBuildingTwo.getNumberFloor());
	}

	@Test(expected = NullPointerException.class)
	public void shouldCantRemoveBuildingByNullAddress() {

		// given
		AddressMap addressOne = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		AddressMap addressTwo = new AddressMap().builder().withStreet("Krotka").withHouseNumber("31/4")
				.withCity("Poznan").withPostCode("51-258").build();
		AddressMap addressThree = new AddressMap().builder().withStreet("Szeroka").withHouseNumber("52")
				.withCity("Krakow").withPostCode("45-212").build();

		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withAddress(addressOne).build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withAddress(addressTwo).build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withAddress(addressThree).build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		buildingService.removeByAddress(null);

		// then
	}

	@Test
	public void shouldTesVersion() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		BuildingTO building = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true).withAddress(address)
				.build();

		BuildingTO saveBuilding = buildingService.saveBuilding(building);
		Integer numberFloor = new Integer(9);
		Long versionTest = 1L;

		// when
		saveBuilding.setNumberFloor(numberFloor);
		Long version1 = buildingService.findById(saveBuilding.getId()).getVersion();
		buildingService.updateBuilding(saveBuilding);
		Long version2 = buildingService.findById(saveBuilding.getId()).getVersion();

		// then
		assertThat(version1).isNotEqualTo(version2);
		assertEquals(versionTest, version2);
	}
}
