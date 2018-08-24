package com.capgemini.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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

		// when
		new FlatTO().builder().withFlatStatus(saveStatus.getId()).withNumberRoom(new Integer(8)).build();

		// then

	}

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateFlatWithoutStatus() {

		// given

		// when
		new FlatTO().builder().withAreaFlat(34.25D).withNumberRoom(new Integer(8)).build();

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
		AddressMap address = new AddressMap().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();
		BuildingTO buildingOne = new BuildingTO().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withNumberFlat(new Integer(35)).withElevator(true).withAddress(address)
				.build();
		buildingService.saveBuilding(buildingOne);

		StatusTO status = new StatusTO().builder().withStatusName("Reserved").build();
		StatusTO saveStatus = statusService.saveStatus(status);
		FlatTO flat = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withNumberBalconie(new Integer(7))
				.withFloor(new Integer(4)).withNumberFlat(new Integer(40)).withPrice(470215D).withAreaFlat(35.75D)
				.withNumberRoom(new Integer(7)).withBuilding(buildingOne.getId()).build();

		// when
		FlatTO saveFlat = flatService.saveFlat(flat);

		// then
		assertEquals(saveStatus, saveFlat.getFlatStatus());
	}

	@Test
	public void shouldFindByIdWhereFlatWasFirstSave() {

		// given
		StatusTO status = new StatusTO().builder().withStatusName("Reserved").build();
		StatusTO saveStatus = statusService.saveStatus(status);

		FlatTO flatOne = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withNumberRoom(new Integer(8)).build();
		FlatTO saveFlatOne = flatService.saveFlat(flatOne);
		FlatTO flatTwo = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D).build();
		FlatTO saveFlatTwo = flatService.saveFlat(flatTwo);
		FlatTO flatThree = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D).build();
		FlatTO saveFlatThree = flatService.saveFlat(flatThree);

		// when
		FlatTO findFlat = flatService.findById(saveFlatOne.getId());

		// then
		assertNotNull(findFlat);
		assertEquals(saveFlatOne, findFlat);
	}

}
