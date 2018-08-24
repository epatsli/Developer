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

import com.capgemini.domain.StatusEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StatusDaoTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private StatusDao statusDao;

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateStatusWithoutName() {

		// given

		// when
		new StatusEntity().builder().build();

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
		StatusEntity status = new StatusEntity().builder().withStatusName("Empty").build();

		// when
		statusDao.save(status);

		// then
		Assert.assertEquals(1, statusDao.count());
	}

	@Test
	public void shouldFindStatusByIdWhenStatusWasFirstSave() {

		// given
		StatusEntity statusOne = new StatusEntity().builder().withStatusName("Empty").build();
		StatusEntity saveStatusOne = statusDao.save(statusOne);
		StatusEntity statusTwo = new StatusEntity().builder().withStatusName("Reservation").build();
		StatusEntity saveStatusTwo = statusDao.save(statusTwo);
		StatusEntity statusThree = new StatusEntity().builder().withStatusName("Sold").build();
		StatusEntity saveStatusThree = statusDao.save(statusThree);

		// when
		StatusEntity findStatus = statusDao.findById(saveStatusOne.getId());

		// then
		Assert.assertEquals(3, statusDao.count());
		assertEquals(statusOne, findStatus);
	}

	@Test
	public void shouldFindStatusByIdWhenStatusWasInsideSave() {

		// given
		StatusEntity statusOne = new StatusEntity().builder().withStatusName("Empty").build();
		StatusEntity saveStatusOne = statusDao.save(statusOne);
		StatusEntity statusTwo = new StatusEntity().builder().withStatusName("Reservation").build();
		StatusEntity saveStatusTwo = statusDao.save(statusTwo);
		StatusEntity statusThree = new StatusEntity().builder().withStatusName("Sold").build();
		StatusEntity saveStatusThree = statusDao.save(statusThree);

		// when
		StatusEntity findStatus = statusDao.findById(saveStatusTwo.getId());

		// then
		Assert.assertEquals(3, statusDao.count());
		assertEquals(statusTwo, findStatus);
	}

	@Test
	public void shouldFindStatusByIdWhenStatusWasLastSave() {

		// given
		StatusEntity statusOne = new StatusEntity().builder().withStatusName("Empty").build();
		StatusEntity saveStatusOne = statusDao.save(statusOne);
		StatusEntity statusTwo = new StatusEntity().builder().withStatusName("Reservation").build();
		StatusEntity saveStatusTwo = statusDao.save(statusTwo);
		StatusEntity statusThree = new StatusEntity().builder().withStatusName("Sold").build();
		StatusEntity saveStatusThree = statusDao.save(statusThree);

		// when
		StatusEntity findStatus = statusDao.findById(saveStatusThree.getId());

		// then
		Assert.assertEquals(3, statusDao.count());
		assertEquals(statusThree, findStatus);
	}

	@Test
	public void shouldCantFindStatusByNullId() {

		// given
		StatusEntity statusOne = new StatusEntity().builder().withStatusName("Empty").build();
		StatusEntity saveStatusOne = statusDao.save(statusOne);
		StatusEntity statusTwo = new StatusEntity().builder().withStatusName("Reservation").build();
		StatusEntity saveStatusTwo = statusDao.save(statusTwo);
		StatusEntity statusThree = new StatusEntity().builder().withStatusName("Sold").build();
		StatusEntity saveStatusThree = statusDao.save(statusThree);

		// when
		StatusEntity findStatus = statusDao.findById(null);

		// then
		Assert.assertEquals(3, statusDao.count());
		Assert.assertNull(findStatus);
	}

	@Test
	public void shouldRemoveStatusByIdWhenStatusWasFirstSave() {

		// given
		StatusEntity statusOne = new StatusEntity().builder().withStatusName("Empty").build();
		StatusEntity saveStatusOne = statusDao.save(statusOne);
		StatusEntity statusTwo = new StatusEntity().builder().withStatusName("Reservation").build();
		StatusEntity saveStatusTwo = statusDao.save(statusTwo);
		StatusEntity statusThree = new StatusEntity().builder().withStatusName("Sold").build();
		StatusEntity saveStatusThree = statusDao.save(statusThree);

		// when
		statusDao.removeById(saveStatusOne.getId());

		// then
		Assert.assertEquals(2, statusDao.count());
		Assert.assertNull(statusDao.findById(saveStatusOne.getId()));
		assertEquals(statusTwo, statusDao.findById(saveStatusTwo.getId()));
		assertEquals(statusThree, statusDao.findById(saveStatusThree.getId()));
	}

	@Test
	public void shouldRemoveStatusByIdWhenStatusWasInsideSave() {

		// given
		StatusEntity statusOne = new StatusEntity().builder().withStatusName("Empty").build();
		StatusEntity saveStatusOne = statusDao.save(statusOne);
		StatusEntity statusTwo = new StatusEntity().builder().withStatusName("Reservation").build();
		StatusEntity saveStatusTwo = statusDao.save(statusTwo);
		StatusEntity statusThree = new StatusEntity().builder().withStatusName("Sold").build();
		StatusEntity saveStatusThree = statusDao.save(statusThree);

		// when
		statusDao.removeById(saveStatusTwo.getId());

		// then
		Assert.assertEquals(2, statusDao.count());
		Assert.assertNull(statusDao.findById(saveStatusTwo.getId()));
		assertEquals(statusOne, statusDao.findById(saveStatusOne.getId()));
		assertEquals(statusThree, statusDao.findById(saveStatusThree.getId()));
	}

	@Test
	public void shouldRemoveStatusByIdWhenStatusWasLastSave() {

		// given
		StatusEntity statusOne = new StatusEntity().builder().withStatusName("Empty").build();
		StatusEntity saveStatusOne = statusDao.save(statusOne);
		StatusEntity statusTwo = new StatusEntity().builder().withStatusName("Reservation").build();
		StatusEntity saveStatusTwo = statusDao.save(statusTwo);
		StatusEntity statusThree = new StatusEntity().builder().withStatusName("Sold").build();
		StatusEntity saveStatusThree = statusDao.save(statusThree);

		// when
		statusDao.removeById(saveStatusThree.getId());

		// then
		Assert.assertEquals(2, statusDao.count());
		Assert.assertNull(statusDao.findById(saveStatusThree.getId()));
		assertEquals(statusOne, statusDao.findById(saveStatusOne.getId()));
		assertEquals(statusTwo, statusDao.findById(saveStatusTwo.getId()));
	}

	@Test
	public void shouldCantRemoveStatusByNullId() {

		// given
		StatusEntity statusOne = new StatusEntity().builder().withStatusName("Empty").build();
		StatusEntity saveStatusOne = statusDao.save(statusOne);
		StatusEntity statusTwo = new StatusEntity().builder().withStatusName("Reservation").build();
		StatusEntity saveStatusTwo = statusDao.save(statusTwo);
		StatusEntity statusThree = new StatusEntity().builder().withStatusName("Sold").build();
		StatusEntity saveStatusThree = statusDao.save(statusThree);

		// when
		statusDao.removeById(null);

		// then
		Assert.assertEquals(3, statusDao.count());
		assertEquals(statusOne, statusDao.findById(saveStatusOne.getId()));
		assertEquals(statusTwo, statusDao.findById(saveStatusTwo.getId()));
		assertEquals(statusThree, statusDao.findById(saveStatusThree.getId()));
	}

}
