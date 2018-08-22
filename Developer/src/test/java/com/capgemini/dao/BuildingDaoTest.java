package com.capgemini.dao;

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
	public void shouldAddBuildingByCRUD() {

		Address address = new Address().builder().withStreet("Dluga").withHouseNumber("12").withCity("Wroclaw")
				.withPostCode("64-254").build();

		BuildingEntity building = new BuildingEntity().builder().withDescription("sasa").withNumberFloor(new Integer(4))
				.withAddress(address).withNumberFlat(new Integer(7)).withElevator(true).build();

		buildingDao.save(building);
		BuildingEntity find = buildingDao.findByIdBuilding(1L);
		Assert.assertNotNull(find);
	}
}
