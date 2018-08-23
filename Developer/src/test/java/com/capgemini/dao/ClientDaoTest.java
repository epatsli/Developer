package com.capgemini.dao;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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
import com.capgemini.domain.ClientEntity;

//import org.assertj.core.api.Assertions;
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ClientDaoTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ClientDao clientDao;

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateClientWithoutFirstName() {

		// given

		// when
		ClientEntity client = new ClientEntity().builder().withLastName("Kowal").withPhoneNumber("748785478").build();

		// then

	}

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateClientWithoutLastName() {

		// given

		// when
		ClientEntity client = new ClientEntity().builder().withFirstName("Jan").withPhoneNumber("445145214").build();

		// then

	}

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateClientWithoutPhoneNumber() {

		// given

		// when
		ClientEntity client = new ClientEntity().builder().withFirstName("Jan").withLastName("Kowal").build();

		// then

	}

	@Test
	public void shouldThrownException() {

		assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
			throw new RuntimeException("Incorrect parameter. This client can't be created.");
		}).withMessage("Incorrect parameter. This client can't be created.")
				.withStackTraceContaining("RuntimeException");
	}

	@Test
	public void shouldFindClientByIdClient() {

		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		// List<FlatEntity> l1 = new ArrayList<>();
		// List<FlatEntity> l2 = new ArrayList<>();
		// List<FlatEntity> l3 = new ArrayList<>();

		ClientEntity client = new ClientEntity().builder().withPhoneNumber("74547454").withFirstName("Jan")
				.withLastName("Kowal").build();
		// .withPhoneNumber("74547454").withAddress(address).withVersion(0L).withListBookFlat(l1)
		// .withListBuyFlat(l2).withListOwnerFlat(l3).build();
		ClientEntity saveClient = clientDao.save(client);
		ClientEntity find = clientDao.findByIdClient(1L);
		Assert.assertNotNull(find);

	}

}
