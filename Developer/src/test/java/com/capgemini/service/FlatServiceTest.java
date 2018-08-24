package com.capgemini.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import com.capgemini.types.FlatTO;
import com.capgemini.types.StatusTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FlatServiceTest {
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private FlatService flatService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private BuildingService buildingService;

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateFlatWithoutArea() {

		// given
		StatusTO status = new StatusTO().builder().withStatusName("Reserved").build();
		StatusTO saveStatus = statusService.saveStatus(status);
		AddressMap address = new AddressMap().builder().withCity("Poznan").withStreet("Warszawska")
				.withHouseNumber("16/12").withPostCode("84-225").build();

		// when
		new FlatTO().builder().withFlatStatus(saveStatus.getId()).withNumberRoom(new Integer(8)).withAddress(address)
				.build();

		// then

	}

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateFlatWithoutStatus() {

		// given
		AddressMap address = new AddressMap().builder().withCity("Poznan").withStreet("Warszawska")
				.withHouseNumber("16/12").withPostCode("84-225").build();

		// when
		new FlatTO().builder().withAreaFlat(34.25D).withNumberRoom(new Integer(8)).withAddress(address).build();

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
		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true)
				.withLocation("Rataje").build();
		buildingService.saveBuilding(buildingOne);

		StatusTO status = new StatusTO().builder().withStatusName("Reserved").build();
		StatusTO saveStatus = statusService.saveStatus(status);
		AddressMap address = new AddressMap().builder().withCity("Poznan").withStreet("Warszawska")
				.withHouseNumber("16/12").withPostCode("84-225").build();
		FlatTO flat = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withNumberBalconie(new Integer(7))
				.withFloor(new Integer(4)).withPrice(470215D).withAreaFlat(35.75D).withNumberRoom(new Integer(7))
				.withAddress(address).build();

		// when
		FlatTO saveFlat = flatService.saveFlat(flat);

		// then
		assertEquals(flat.getAreaFlat(), saveFlat.getAreaFlat());
		assertEquals(flat.getFlatStatus(), saveFlat.getFlatStatus());
		assertEquals(flat.getNumberBalconie(), saveFlat.getNumberBalconie());
		assertEquals(flat.getNumberRoom(), saveFlat.getNumberRoom());
	}

	@Test
	public void shouldFindByIdWhereFlatWasFirstSave() {

		// given
		StatusTO status = new StatusTO().builder().withStatusName("Reserved").build();
		StatusTO saveStatus = statusService.saveStatus(status);

		AddressMap address = new AddressMap().builder().withCity("Poznan").withStreet("Warszawska")
				.withHouseNumber("16/12").withPostCode("84-225").build();

		FlatTO flatOne = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withNumberRoom(new Integer(8)).withAddress(address).build();
		FlatTO saveFlatOne = flatService.saveFlat(flatOne);
		FlatTO flatTwo = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatTwo = flatService.saveFlat(flatTwo);
		FlatTO flatThree = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatThree = flatService.saveFlat(flatThree);

		// when
		FlatTO findFlat = flatService.findById(saveFlatOne.getId());

		// then
		assertNotNull(findFlat);
		assertEquals(saveFlatOne.getFlatStatus(), findFlat.getFlatStatus());
		assertEquals(saveFlatOne.getAreaFlat(), findFlat.getAreaFlat());
	}

	@Test
	public void shouldFindByIdWhereFlatWasInsideSave() {

		// given
		StatusTO status = new StatusTO().builder().withStatusName("Reserved").build();
		StatusTO saveStatus = statusService.saveStatus(status);

		AddressMap address = new AddressMap().builder().withCity("Poznan").withStreet("Warszawska")
				.withHouseNumber("16/12").withPostCode("84-225").build();

		FlatTO flatOne = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withNumberRoom(new Integer(8)).withAddress(address).build();
		FlatTO saveFlatOne = flatService.saveFlat(flatOne);
		FlatTO flatTwo = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatTwo = flatService.saveFlat(flatTwo);
		FlatTO flatThree = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatThree = flatService.saveFlat(flatThree);

		// when
		FlatTO findFlat = flatService.findById(saveFlatTwo.getId());

		// then
		assertNotNull(findFlat);
		assertEquals(saveFlatTwo.getFlatStatus(), findFlat.getFlatStatus());
		assertEquals(saveFlatTwo.getAreaFlat(), findFlat.getAreaFlat());
	}

	@Test
	public void shouldFindByIdWhereFlatWasLastSave() {

		// given
		StatusTO status = new StatusTO().builder().withStatusName("Reserved").build();
		StatusTO saveStatus = statusService.saveStatus(status);

		AddressMap address = new AddressMap().builder().withCity("Poznan").withStreet("Warszawska")
				.withHouseNumber("16/12").withPostCode("84-225").build();

		FlatTO flatOne = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withNumberRoom(new Integer(8)).withAddress(address).build();
		FlatTO saveFlatOne = flatService.saveFlat(flatOne);
		FlatTO flatTwo = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatTwo = flatService.saveFlat(flatTwo);
		FlatTO flatThree = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatThree = flatService.saveFlat(flatThree);

		// when
		FlatTO findFlat = flatService.findById(saveFlatThree.getId());

		// then
		assertNotNull(findFlat);
		assertEquals(saveFlatThree.getFlatStatus(), findFlat.getFlatStatus());
		assertEquals(saveFlatThree.getAreaFlat(), findFlat.getAreaFlat());
	}

	@Test
	public void shouldCantFindByNullId() {

		// given
		StatusTO status = new StatusTO().builder().withStatusName("Reserved").build();
		StatusTO saveStatus = statusService.saveStatus(status);

		AddressMap address = new AddressMap().builder().withCity("Poznan").withStreet("Warszawska")
				.withHouseNumber("16/12").withPostCode("84-225").build();

		FlatTO flatOne = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withNumberRoom(new Integer(8)).withAddress(address).build();
		FlatTO saveFlatOne = flatService.saveFlat(flatOne);
		FlatTO flatTwo = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatTwo = flatService.saveFlat(flatTwo);
		FlatTO flatThree = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatThree = flatService.saveFlat(flatThree);

		// when
		FlatTO findFlat = flatService.findById(null);

		// then
		assertNull(findFlat);
	}

	@Test
	public void shouldRemoveByIdWhereFlatWasFirstSave() {

		// given
		StatusTO status = new StatusTO().builder().withStatusName("Reserved").build();
		StatusTO saveStatus = statusService.saveStatus(status);

		AddressMap address = new AddressMap().builder().withCity("Poznan").withStreet("Warszawska")
				.withHouseNumber("16/12").withPostCode("84-225").build();

		FlatTO flatOne = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withNumberRoom(new Integer(8)).withAddress(address).build();
		FlatTO saveFlatOne = flatService.saveFlat(flatOne);
		FlatTO flatTwo = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatTwo = flatService.saveFlat(flatTwo);
		FlatTO flatThree = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatThree = flatService.saveFlat(flatThree);

		// when
		flatService.removeById(saveFlatOne.getId());
		FlatTO findFlatOne = flatService.findById(saveFlatOne.getId());
		FlatTO findFlatTwo = flatService.findById(saveFlatTwo.getId());
		FlatTO findFlatThree = flatService.findById(saveFlatThree.getId());

		// then
		Assert.assertTrue(findFlatOne == null);
		assertEquals(saveFlatTwo.getFlatStatus(), findFlatTwo.getFlatStatus());
		assertEquals(saveFlatTwo.getAreaFlat(), findFlatTwo.getAreaFlat());
		assertEquals(saveFlatThree.getFlatStatus(), findFlatThree.getFlatStatus());
		assertEquals(saveFlatThree.getAreaFlat(), findFlatThree.getAreaFlat());
	}

	@Test
	public void shouldRemoveByIdWhereFlatWasInsideSave() {

		// given
		StatusTO status = new StatusTO().builder().withStatusName("Reserved").build();
		StatusTO saveStatus = statusService.saveStatus(status);

		AddressMap address = new AddressMap().builder().withCity("Poznan").withStreet("Warszawska")
				.withHouseNumber("16/12").withPostCode("84-225").build();

		FlatTO flatOne = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withNumberRoom(new Integer(8)).withAddress(address).build();
		FlatTO saveFlatOne = flatService.saveFlat(flatOne);
		FlatTO flatTwo = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatTwo = flatService.saveFlat(flatTwo);
		FlatTO flatThree = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatThree = flatService.saveFlat(flatThree);

		// when
		flatService.removeById(saveFlatTwo.getId());
		FlatTO findFlatOne = flatService.findById(saveFlatOne.getId());
		FlatTO findFlatTwo = flatService.findById(saveFlatTwo.getId());
		FlatTO findFlatThree = flatService.findById(saveFlatThree.getId());

		// then
		Assert.assertTrue(findFlatTwo == null);
		assertEquals(saveFlatOne.getFlatStatus(), findFlatOne.getFlatStatus());
		assertEquals(saveFlatOne.getAreaFlat(), findFlatOne.getAreaFlat());
		assertEquals(saveFlatThree.getFlatStatus(), findFlatThree.getFlatStatus());
		assertEquals(saveFlatThree.getAreaFlat(), findFlatThree.getAreaFlat());
	}

	@Test
	public void shouldRemoveByIdWhereFlatWasLastSave() {

		// given
		StatusTO status = new StatusTO().builder().withStatusName("Reserved").build();
		StatusTO saveStatus = statusService.saveStatus(status);

		AddressMap address = new AddressMap().builder().withCity("Poznan").withStreet("Warszawska")
				.withHouseNumber("16/12").withPostCode("84-225").build();

		FlatTO flatOne = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withNumberRoom(new Integer(8)).withAddress(address).build();
		FlatTO saveFlatOne = flatService.saveFlat(flatOne);
		FlatTO flatTwo = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatTwo = flatService.saveFlat(flatTwo);
		FlatTO flatThree = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatThree = flatService.saveFlat(flatThree);

		// when
		flatService.removeById(saveFlatThree.getId());
		FlatTO findFlatOne = flatService.findById(saveFlatOne.getId());
		FlatTO findFlatTwo = flatService.findById(saveFlatTwo.getId());
		FlatTO findFlatThree = flatService.findById(saveFlatThree.getId());

		// then
		Assert.assertTrue(findFlatThree == null);
		assertEquals(saveFlatOne.getFlatStatus(), findFlatOne.getFlatStatus());
		assertEquals(saveFlatOne.getAreaFlat(), findFlatOne.getAreaFlat());
		assertEquals(saveFlatTwo.getFlatStatus(), findFlatTwo.getFlatStatus());
		assertEquals(saveFlatTwo.getAreaFlat(), findFlatTwo.getAreaFlat());
	}

	@Test
	public void shouldCantRemoveByNullId() {

		// given
		StatusTO status = new StatusTO().builder().withStatusName("Reserved").build();
		StatusTO saveStatus = statusService.saveStatus(status);

		AddressMap address = new AddressMap().builder().withCity("Poznan").withStreet("Warszawska")
				.withHouseNumber("16/12").withPostCode("84-225").build();

		FlatTO flatOne = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withNumberRoom(new Integer(8)).withAddress(address).build();
		FlatTO saveFlatOne = flatService.saveFlat(flatOne);
		FlatTO flatTwo = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatTwo = flatService.saveFlat(flatTwo);
		FlatTO flatThree = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withAddress(address).build();
		FlatTO saveFlatThree = flatService.saveFlat(flatThree);

		// when
		flatService.removeById(null);
		FlatTO findFlatOne = flatService.findById(saveFlatOne.getId());
		FlatTO findFlatTwo = flatService.findById(saveFlatTwo.getId());
		FlatTO findFlatThree = flatService.findById(saveFlatThree.getId());

		// then;
		assertEquals(saveFlatOne.getFlatStatus(), findFlatOne.getFlatStatus());
		assertEquals(saveFlatOne.getAreaFlat(), findFlatOne.getAreaFlat());
		assertEquals(saveFlatTwo.getFlatStatus(), findFlatTwo.getFlatStatus());
		assertEquals(saveFlatTwo.getAreaFlat(), findFlatTwo.getAreaFlat());
		assertEquals(saveFlatThree.getFlatStatus(), findFlatThree.getFlatStatus());
		assertEquals(saveFlatThree.getAreaFlat(), findFlatThree.getAreaFlat());
	}

	@Test
	public void shouldUpdateFlat() {

		// given
		StatusTO status = new StatusTO().builder().withStatusName("Reserved").build();
		StatusTO saveStatus = statusService.saveStatus(status);

		AddressMap address = new AddressMap().builder().withCity("Poznan").withStreet("Warszawska")
				.withHouseNumber("16/12").withPostCode("84-225").build();

		FlatTO flat = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withNumberRoom(new Integer(8)).withAddress(address).build();
		FlatTO saveFlat = flatService.saveFlat(flat);
		Double areaFlat = 45.89D;
		saveFlat.setAreaFlat(areaFlat);
		// when
		FlatTO updateFlat = flatService.updateFlat(saveFlat);

		// then;
		assertEquals(saveFlat.getFlatStatus(), updateFlat.getFlatStatus());
		assertEquals(saveFlat.getAreaFlat(), updateFlat.getAreaFlat());

	}

	@Test
	public void shouldTesVersion() {

		// given
		StatusTO status = new StatusTO().builder().withStatusName("Reserved").build();
		StatusTO saveStatus = statusService.saveStatus(status);

		AddressMap address = new AddressMap().builder().withCity("Poznan").withStreet("Warszawska")
				.withHouseNumber("16/12").withPostCode("84-225").build();

		FlatTO flat = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withNumberRoom(new Integer(8)).withAddress(address).build();
		FlatTO saveFlat = flatService.saveFlat(flat);
		Double areaFlat = 45.89D;
		saveFlat.setAreaFlat(areaFlat);

		Long versionTest = 1L;

		// when
		Long version1 = flatService.findById(saveFlat.getId()).getVersion();
		flatService.updateFlat(saveFlat);
		Long version2 = flatService.findById(saveFlat.getId()).getVersion();

		// then
		assertThat(version1).isNotEqualTo(version2);
		assertEquals(versionTest, version2);
	}

}
