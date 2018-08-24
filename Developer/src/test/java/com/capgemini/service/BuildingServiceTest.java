package com.capgemini.service;

import static org.assertj.core.api.Assertions.assertThat;
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

		// when
		new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withElevator(true).withLocation("Rataje").build();

		// then

	}

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateBuildingWithoutNumberFloor() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		// when
		new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFlat(new Integer(35)).withElevator(true).withLocation("Rataje").build();

		// then
	}

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateBuildingWithoutElevator() {

		// given
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		// when
		new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(13)).withLocation("Rataje").build();

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
		BuildingTO building = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withLocation("Rataje").build();

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
		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Rataje").build();
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
		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Rataje").build();
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
		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Rataje").build();
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
		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		BuildingTO findBuilding = buildingService.findById(null);

		// then
		Assert.assertNull(findBuilding);
	}

	@Test
	public void shouldFindBuildingByLocationWhenBuildingWasFirstSave() {

		// given
		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withLocation("Grunwald").build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		List<BuildingTO> findBuilding = buildingService.findByLocation(saveBuildingOne.getLocation());

		// then
		Assert.assertNotNull(findBuilding);
		assertEquals(saveBuildingOne.getNumberFloor(), findBuilding.get(0).getNumberFloor());
		assertEquals(saveBuildingOne.getDescription(), findBuilding.get(0).getDescription());
		assertEquals(saveBuildingOne.getElevator(), findBuilding.get(0).getElevator());
	}

	@Test
	public void shouldFindBuildingByLocationWhenBuildingWasInsideSave() {

		// given
		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withLocation("Grunwald").build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		List<BuildingTO> findBuilding = buildingService.findByLocation(saveBuildingTwo.getLocation());

		// then
		Assert.assertNotNull(findBuilding);
		assertEquals(saveBuildingTwo.getNumberFloor(), findBuilding.get(0).getNumberFloor());
		assertEquals(saveBuildingTwo.getDescription(), findBuilding.get(0).getDescription());
		assertEquals(saveBuildingTwo.getElevator(), findBuilding.get(0).getElevator());
	}

	@Test
	public void shouldFindBuildingByLocationWhenBuildingWasLastSave() {

		// given
		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withLocation("Grunwald").build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		List<BuildingTO> findBuilding = buildingService.findByLocation(saveBuildingThree.getLocation());

		// then
		Assert.assertNotNull(findBuilding);
		assertEquals(saveBuildingThree.getNumberFloor(), findBuilding.get(0).getNumberFloor());
		assertEquals(saveBuildingThree.getDescription(), findBuilding.get(0).getDescription());
		assertEquals(saveBuildingThree.getElevator(), findBuilding.get(0).getElevator());
	}

	@Test
	public void shouldCantFindBuildingByLocationNull() {

		// given
		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withLocation("Grunwald").build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		List<BuildingTO> findBuilding = buildingService.findByLocation(null);

		// then
		assertTrue(findBuilding.isEmpty());
	}

	@Test
	public void shouldUpdateBuilding() {

		// given
		BuildingTO building = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withLocation("Rataje").build();

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
		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withLocation("Grunwald").build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
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
		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withLocation("Grunwald").build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
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
		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withLocation("Grunwald").build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
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
		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withLocation("Grunwald").build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
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
	public void shouldRemoveBuildingByLocationWhenBuildingWasFirstSave() {

		// given
		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withLocation("Grunwald").build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		buildingService.removeByLocation(saveBuildingOne.getLocation());

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
	public void shouldRemoveBuildingByLocationWhenBuildingWasInsideSave() {

		// given
		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withLocation("Grunwald").build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		buildingService.removeByLocation(saveBuildingTwo.getLocation());

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
	public void shouldRemoveBuildingByLocationWhenBuildingWasLastSave() {

		// given
		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withLocation("Grunwald").build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		buildingService.removeByLocation(saveBuildingThree.getLocation());

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
	public void shouldCantRemoveBuildingByNullLocation() {

		// given
		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingTO buildingTwo = new BuildingTO().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withLocation("Grunwald").build();
		BuildingTO buildingThree = new BuildingTO().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
		BuildingTO saveBuildingOne = buildingService.saveBuilding(buildingOne);
		BuildingTO saveBuildingTwo = buildingService.saveBuilding(buildingTwo);
		BuildingTO saveBuildingThree = buildingService.saveBuilding(buildingThree);

		// when
		buildingService.removeByLocation(null);

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
	public void shouldTesVersion() {

		// given
		BuildingTO building = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withLocation("Rataje").build();

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
