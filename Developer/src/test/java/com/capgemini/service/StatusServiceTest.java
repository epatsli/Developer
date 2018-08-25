package com.capgemini.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
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
import com.capgemini.types.FlatTO;
import com.capgemini.types.StatusTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StatusServiceTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private StatusService statusService;

	@Autowired
	private FlatService flatService;

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateStatusWithoutName() {

		// given

		// when
		new StatusTO().builder().build();

		// then
	}

	@Test
	public void shouldThrownException() {

		assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
			throw new RuntimeException("This status can't be created.");
		}).withMessage("This status can't be created.").withStackTraceContaining("RuntimeException");
	}

	@Test
	public void shouldCreateStatus() {

		// given
		StatusTO status = new StatusTO().builder().withStatusName("Empty").build();

		// when
		StatusTO saveStatus = statusService.saveStatus(status);

		// then
		assertEquals(status.getStatusName(), saveStatus.getStatusName());
	}

	@Test
	public void shouldFindStatusByIdWhenStatusWasFirstSave() {

		// given
		StatusTO statusOne = new StatusTO().builder().withStatusName("Empty").build();
		StatusTO saveStatusOne = statusService.saveStatus(statusOne);
		StatusTO statusTwo = new StatusTO().builder().withStatusName("Reservation").build();
		StatusTO saveStatusTwo = statusService.saveStatus(statusTwo);
		StatusTO statusThree = new StatusTO().builder().withStatusName("Sold").build();
		StatusTO saveStatusThree = statusService.saveStatus(statusThree);

		// when
		StatusTO findStatus = statusService.findById(saveStatusOne.getId());

		// then
		Assert.assertNotNull(findStatus);
		assertEquals(saveStatusOne.getStatusName(), findStatus.getStatusName());
	}

	@Test
	public void shouldFindStatusByIdWhenStatusWasInsideSave() {

		// given
		StatusTO statusOne = new StatusTO().builder().withStatusName("Empty").build();
		StatusTO saveStatusOne = statusService.saveStatus(statusOne);
		StatusTO statusTwo = new StatusTO().builder().withStatusName("Reservation").build();
		StatusTO saveStatusTwo = statusService.saveStatus(statusTwo);
		StatusTO statusThree = new StatusTO().builder().withStatusName("Sold").build();
		StatusTO saveStatusThree = statusService.saveStatus(statusThree);

		// when
		StatusTO findStatus = statusService.findById(saveStatusTwo.getId());

		// then
		Assert.assertNotNull(findStatus);
		assertEquals(saveStatusTwo.getStatusName(), findStatus.getStatusName());
	}

	@Test
	public void shouldFindStatusByIdWhenStatusWasLastSave() {

		// given
		StatusTO statusOne = new StatusTO().builder().withStatusName("Empty").build();
		StatusTO saveStatusOne = statusService.saveStatus(statusOne);
		StatusTO statusTwo = new StatusTO().builder().withStatusName("Reservation").build();
		StatusTO saveStatusTwo = statusService.saveStatus(statusTwo);
		StatusTO statusThree = new StatusTO().builder().withStatusName("Sold").build();
		StatusTO saveStatusThree = statusService.saveStatus(statusThree);

		// when
		StatusTO findStatus = statusService.findById(saveStatusThree.getId());

		// then
		Assert.assertNotNull(findStatus);
		assertEquals(statusThree.getStatusName(), findStatus.getStatusName());
	}

	@Test
	public void shouldCantFindStatusByNullId() {

		// given
		StatusTO statusOne = new StatusTO().builder().withStatusName("Empty").build();
		StatusTO saveStatusOne = statusService.saveStatus(statusOne);
		StatusTO statusTwo = new StatusTO().builder().withStatusName("Reservation").build();
		StatusTO saveStatusTwo = statusService.saveStatus(statusTwo);
		StatusTO statusThree = new StatusTO().builder().withStatusName("Sold").build();
		StatusTO saveStatusThree = statusService.saveStatus(statusThree);

		// when
		StatusTO findStatus = statusService.findById(null);

		// then
		Assert.assertNull(findStatus);
	}

	@Test
	public void shouldRemoveStatusByIdWhenStatusWasFirstSave() {

		// given
		StatusTO statusOne = new StatusTO().builder().withStatusName("Empty").build();
		StatusTO saveStatusOne = statusService.saveStatus(statusOne);
		StatusTO statusTwo = new StatusTO().builder().withStatusName("Reservation").build();
		StatusTO saveStatusTwo = statusService.saveStatus(statusTwo);
		StatusTO statusThree = new StatusTO().builder().withStatusName("Sold").build();
		StatusTO saveStatusThree = statusService.saveStatus(statusThree);

		// when
		statusService.removeById(saveStatusOne.getId());

		// then
		Assert.assertNull(statusService.findById(saveStatusOne.getId()));
		assertEquals(statusTwo.getStatusName(), statusService.findById(saveStatusTwo.getId()).getStatusName());
		assertEquals(statusThree.getStatusName(), statusService.findById(saveStatusThree.getId()).getStatusName());
	}

	@Test
	public void shouldRemoveStatusByIdWhenStatusWasInsideSave() {

		// given
		StatusTO statusOne = new StatusTO().builder().withStatusName("Empty").build();
		StatusTO saveStatusOne = statusService.saveStatus(statusOne);
		StatusTO statusTwo = new StatusTO().builder().withStatusName("Reservation").build();
		StatusTO saveStatusTwo = statusService.saveStatus(statusTwo);
		StatusTO statusThree = new StatusTO().builder().withStatusName("Sold").build();
		StatusTO saveStatusThree = statusService.saveStatus(statusThree);

		// when
		statusService.removeById(saveStatusTwo.getId());

		// then
		Assert.assertNull(statusService.findById(saveStatusTwo.getId()));
		assertEquals(statusOne.getStatusName(), statusService.findById(saveStatusOne.getId()).getStatusName());
		assertEquals(statusThree.getStatusName(), statusService.findById(saveStatusThree.getId()).getStatusName());
	}

	@Test
	public void shouldRemoveStatusByIdWhenStatusWasLastSave() {

		// given
		StatusTO statusOne = new StatusTO().builder().withStatusName("Empty").build();
		StatusTO saveStatusOne = statusService.saveStatus(statusOne);
		StatusTO statusTwo = new StatusTO().builder().withStatusName("Reservation").build();
		StatusTO saveStatusTwo = statusService.saveStatus(statusTwo);
		StatusTO statusThree = new StatusTO().builder().withStatusName("Sold").build();
		StatusTO saveStatusThree = statusService.saveStatus(statusThree);

		// when
		statusService.removeById(saveStatusThree.getId());

		// then
		Assert.assertNull(statusService.findById(saveStatusThree.getId()));
		assertEquals(statusOne.getStatusName(), statusService.findById(saveStatusOne.getId()).getStatusName());
		assertEquals(statusTwo.getStatusName(), statusService.findById(saveStatusTwo.getId()).getStatusName());
	}

	@Test
	public void shouldCantRemoveStatusByNullId() {

		// given
		StatusTO statusOne = new StatusTO().builder().withStatusName("Empty").build();
		StatusTO saveStatusOne = statusService.saveStatus(statusOne);
		StatusTO statusTwo = new StatusTO().builder().withStatusName("Reservation").build();
		StatusTO saveStatusTwo = statusService.saveStatus(statusTwo);
		StatusTO statusThree = new StatusTO().builder().withStatusName("Sold").build();
		StatusTO saveStatusThree = statusService.saveStatus(statusThree);

		// when
		statusService.removeById(null);

		// then
		assertEquals(statusOne.getStatusName(), statusService.findById(saveStatusOne.getId()).getStatusName());
		assertEquals(statusTwo.getStatusName(), statusService.findById(saveStatusTwo.getId()).getStatusName());
		assertEquals(statusThree.getStatusName(), statusService.findById(saveStatusThree.getId()).getStatusName());
	}

	@Test
	public void shouldUpdateStatus() {

		// given
		StatusTO status = new StatusTO().builder().withStatusName("Empty").build();
		StatusTO saveStatus = statusService.saveStatus(status);
		String newStatus = "Is free";
		saveStatus.setStatusName(newStatus);

		// when
		StatusTO updateStatus = statusService.updateStatus(saveStatus);

		// then
		assertEquals("Is free", updateStatus.getStatusName());
	}

	@Test
	public void shouldUpdateAllParametersStatus() {

		// given
		StatusTO status = new StatusTO().builder().withStatusName("Empty").build();
		StatusTO saveStatus = statusService.saveStatus(status);
		String newStatus = "Is free";
		saveStatus.setStatusName(newStatus);
		AddressMap address = new AddressMap().builder().withCity("Poznan").withStreet("Warszawska")
				.withHouseNumber("16/12").withPostCode("84-225").build();

		FlatTO flatOne = new FlatTO().builder().withFlatStatus(saveStatus.getId()).withAreaFlat(35.75D)
				.withNumberRoom(new Integer(8)).withAddress(address).build();
		FlatTO saveFlatOne = flatService.saveFlat(flatOne);
		List<Long> flats = new ArrayList<>();
		flats.add(saveFlatOne.getId());
		saveStatus.setFlats(flats);

		// when
		StatusTO updateStatus = statusService.updateStatus(saveStatus);

		// then
		assertEquals("Is free", updateStatus.getStatusName());
	}

}
