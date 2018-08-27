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
		BuildingEntity building = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(35)).withElevator(true).withLocation("Rataje").build();

		// when
		BuildingEntity saveBuilding = buildingDao.save(building);

		// then
		assertEquals(building, saveBuilding);
	}

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateBuildingWithoutNumberFloor() {

		// given

		// when
		new BuildingEntity().builder().withDescription("The building is located on the river.")
				.withNumberFlat(new Integer(35)).withElevator(true).withLocation("Rataje").build();

		// then
	}

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateBuildingWithoutNumberFlat() {

		// given

		// when
		new BuildingEntity().builder().withDescription("The building is located on the river.")
				.withNumberFloor(new Integer(4)).withElevator(true).withLocation("Rataje").build();

		// then
	}

	@Test(expected = RuntimeException.class)
	public void shouldCantCreateBuildingWithoutElevator() {

		// given

		// when
		new BuildingEntity().builder().withDescription("The building is located on the river.")
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
	public void shouldFindBuildingByIdWhenBuildingWasFirstSave() {

		// given

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withLocation("Rataje").withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withLocation("Rataje").withNumberFlat(new Integer(64))
				.withElevator(true).build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		buildingDao.save(buildingTwo);
		buildingDao.save(buildingThree);

		// when
		BuildingEntity findBuilding = buildingDao.findById(saveBuildingOne.getId());

		// then
		Assert.assertNotNull(findBuilding);
		assertEquals(saveBuildingOne, findBuilding);
	}

	@Test
	public void shouldFindBuildingByIdWhenBuildingWasInsideSave() {

		// given

		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Rataje").build();
		buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		buildingDao.save(buildingThree);

		// when
		BuildingEntity findBuilding = buildingDao.findById(saveBuildingTwo.getId());

		// then
		Assert.assertNotNull(findBuilding);
		assertEquals(saveBuildingTwo, findBuilding);
	}

	@Test
	public void shouldFindBuildingByIdWhenBuildingWasLastSave() {

		// given
		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Rataje").build();
		buildingDao.save(buildingOne);
		buildingDao.save(buildingTwo);
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
		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withNumberFlat(new Integer(32)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Rataje").build();
		buildingDao.save(buildingOne);
		buildingDao.save(buildingTwo);
		buildingDao.save(buildingThree);

		// when
		BuildingEntity findBuilding = buildingDao.findById(null);

		// then
		Assert.assertNull(findBuilding);
	}

	@Test
	public void shouldFindBuildingByLocationWhenBuildingWasFirstSave() {

		// given
		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withLocation("Os. Rusa").withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		buildingDao.save(buildingTwo);
		buildingDao.save(buildingThree);

		// when
		List<BuildingEntity> findBuilding = buildingDao.findByLocation(saveBuildingOne.getLocation());

		// then
		Assert.assertNotNull(findBuilding);
		Assert.assertEquals(1, findBuilding.size());
		assertEquals(saveBuildingOne.getLocation(), findBuilding.get(0).getLocation());
		assertEquals(saveBuildingOne.getDescription(), findBuilding.get(0).getDescription());
		assertEquals(saveBuildingOne.getNumberFlat(), findBuilding.get(0).getNumberFlat());
		assertEquals(saveBuildingOne.getElevator(), findBuilding.get(0).getElevator());
		assertEquals(saveBuildingOne.getNumberFloor(), findBuilding.get(0).getNumberFloor());
	}

	@Test
	public void shouldFindBuildingByLocationWhenBuildingWasInsideSave() {

		// given
		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withLocation("Os. Rusa").withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
		buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		buildingDao.save(buildingThree);

		// when
		List<BuildingEntity> findBuilding = buildingDao.findByLocation(saveBuildingTwo.getLocation());

		// then
		Assert.assertNotNull(findBuilding);
		Assert.assertEquals(1, findBuilding.size());
		assertEquals(saveBuildingTwo.getLocation(), findBuilding.get(0).getLocation());
		assertEquals(saveBuildingTwo.getDescription(), findBuilding.get(0).getDescription());
		assertEquals(saveBuildingTwo.getNumberFlat(), findBuilding.get(0).getNumberFlat());
		assertEquals(saveBuildingTwo.getElevator(), findBuilding.get(0).getElevator());
		assertEquals(saveBuildingTwo.getNumberFloor(), findBuilding.get(0).getNumberFloor());
	}

	@Test
	public void shouldFindBuildingByLocationWhenBuildingWasLastSave() {

		// given
		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withLocation("Os. Rusa").withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
		buildingDao.save(buildingOne);
		buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		List<BuildingEntity> findBuilding = buildingDao.findByLocation(saveBuildingThree.getLocation());

		// then
		Assert.assertNotNull(findBuilding);
		Assert.assertEquals(1, findBuilding.size());
		assertEquals(saveBuildingThree.getLocation(), findBuilding.get(0).getLocation());
		assertEquals(saveBuildingThree.getDescription(), findBuilding.get(0).getDescription());
		assertEquals(saveBuildingThree.getNumberFlat(), findBuilding.get(0).getNumberFlat());
		assertEquals(saveBuildingThree.getElevator(), findBuilding.get(0).getElevator());
		assertEquals(saveBuildingThree.getNumberFloor(), findBuilding.get(0).getNumberFloor());
	}

	@Test
	public void shouldFindBuildingByNullLocation() {

		// given
		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withLocation("Os. Rusa").withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
		buildingDao.save(buildingOne);
		buildingDao.save(buildingTwo);
		buildingDao.save(buildingThree);

		// when
		List<BuildingEntity> findBuilding = buildingDao.findByLocation(null);

		// then
		assertTrue(findBuilding.isEmpty());
	}

	@Test
	public void shouldFindBuildingByLocation() {

		// given
		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withLocation("Os. Rusa").withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		List<BuildingEntity> findBuilding = buildingDao.findByLocation("Rataje");

		// then
		Assert.assertNotNull(findBuilding);
		Assert.assertEquals(2, findBuilding.size());
		assertEquals("Rataje", findBuilding.get(0).getLocation());
		assertEquals(saveBuildingOne.getDescription(), findBuilding.get(0).getDescription());
		assertEquals(saveBuildingOne.getNumberFlat(), findBuilding.get(0).getNumberFlat());
		assertEquals(saveBuildingOne.getElevator(), findBuilding.get(0).getElevator());
		assertEquals(saveBuildingOne.getNumberFloor(), findBuilding.get(0).getNumberFloor());
		assertEquals("Rataje", findBuilding.get(1).getLocation());
		assertEquals(saveBuildingThree.getDescription(), findBuilding.get(1).getDescription());
		assertEquals(saveBuildingThree.getNumberFlat(), findBuilding.get(1).getNumberFlat());
		assertEquals(saveBuildingThree.getElevator(), findBuilding.get(1).getElevator());
		assertEquals(saveBuildingThree.getNumberFloor(), findBuilding.get(1).getNumberFloor());
	}

	@Test
	public void shouldRemoveBuildingByIdWhenBuildingWasFirstSave() {

		// given
		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withLocation("Os. Rusa").withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		buildingDao.removeById(saveBuildingOne.getId());

		// then
		Assert.assertNull(buildingDao.findById(saveBuildingOne.getId()));
		Assert.assertEquals(2, buildingDao.count());
		assertEquals(buildingTwo.getLocation(), saveBuildingTwo.getLocation());
		assertEquals(buildingTwo.getDescription(), saveBuildingTwo.getDescription());
		assertEquals(buildingTwo.getNumberFlat(), saveBuildingTwo.getNumberFlat());
		assertEquals(buildingTwo.getElevator(), saveBuildingTwo.getElevator());
		assertEquals(buildingTwo.getNumberFloor(), saveBuildingTwo.getNumberFloor());
		assertEquals(buildingThree.getLocation(), saveBuildingThree.getLocation());
		assertEquals(buildingThree.getDescription(), saveBuildingThree.getDescription());
		assertEquals(buildingThree.getNumberFlat(), saveBuildingThree.getNumberFlat());
		assertEquals(buildingThree.getElevator(), saveBuildingThree.getElevator());
		assertEquals(buildingThree.getNumberFloor(), saveBuildingThree.getNumberFloor());
	}

	@Test
	public void shouldRemoveBuildingByIdWhenBuildingWasInsideSave() {

		// given
		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withLocation("Os. Rusa").withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		buildingDao.removeById(saveBuildingTwo.getId());

		// then
		Assert.assertNull(buildingDao.findById(saveBuildingTwo.getId()));
		Assert.assertEquals(2, buildingDao.count());
		assertEquals(buildingOne.getLocation(), saveBuildingOne.getLocation());
		assertEquals(buildingOne.getDescription(), saveBuildingOne.getDescription());
		assertEquals(buildingOne.getNumberFlat(), saveBuildingOne.getNumberFlat());
		assertEquals(buildingOne.getElevator(), saveBuildingOne.getElevator());
		assertEquals(buildingOne.getNumberFloor(), saveBuildingOne.getNumberFloor());
		assertEquals(buildingThree.getLocation(), saveBuildingThree.getLocation());
		assertEquals(buildingThree.getDescription(), saveBuildingThree.getDescription());
		assertEquals(buildingThree.getNumberFlat(), saveBuildingThree.getNumberFlat());
		assertEquals(buildingThree.getElevator(), saveBuildingThree.getElevator());
		assertEquals(buildingThree.getNumberFloor(), saveBuildingThree.getNumberFloor());
	}

	@Test
	public void shouldRemoveBuildingByIdWhenBuildingWasLastSave() {

		// given
		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withLocation("Os. Rusa").withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		buildingDao.removeById(saveBuildingThree.getId());

		// then
		Assert.assertNull(buildingDao.findById(saveBuildingThree.getId()));
		Assert.assertEquals(2, buildingDao.count());
		assertEquals(buildingOne.getLocation(), saveBuildingOne.getLocation());
		assertEquals(buildingOne.getDescription(), saveBuildingOne.getDescription());
		assertEquals(buildingOne.getNumberFlat(), saveBuildingOne.getNumberFlat());
		assertEquals(buildingOne.getElevator(), saveBuildingOne.getElevator());
		assertEquals(buildingOne.getNumberFloor(), saveBuildingOne.getNumberFloor());
		assertEquals(buildingTwo.getLocation(), saveBuildingTwo.getLocation());
		assertEquals(buildingTwo.getDescription(), saveBuildingTwo.getDescription());
		assertEquals(buildingTwo.getNumberFlat(), saveBuildingTwo.getNumberFlat());
		assertEquals(buildingTwo.getElevator(), saveBuildingTwo.getElevator());
		assertEquals(buildingTwo.getNumberFloor(), saveBuildingTwo.getNumberFloor());
	}

	@Test
	public void shouldRemoveBuildingByNullId() {

		// given
		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withLocation("Os. Rusa").withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		buildingDao.removeById(null);

		// then

		Assert.assertEquals(3, buildingDao.count());
		assertEquals(buildingOne.getLocation(), saveBuildingOne.getLocation());
		assertEquals(buildingOne.getDescription(), saveBuildingOne.getDescription());
		assertEquals(buildingOne.getNumberFlat(), saveBuildingOne.getNumberFlat());
		assertEquals(buildingOne.getElevator(), saveBuildingOne.getElevator());
		assertEquals(buildingOne.getNumberFloor(), saveBuildingOne.getNumberFloor());
		assertEquals(buildingTwo.getLocation(), saveBuildingTwo.getLocation());
		assertEquals(buildingTwo.getDescription(), saveBuildingTwo.getDescription());
		assertEquals(buildingTwo.getNumberFlat(), saveBuildingTwo.getNumberFlat());
		assertEquals(buildingTwo.getElevator(), saveBuildingTwo.getElevator());
		assertEquals(buildingTwo.getNumberFloor(), saveBuildingTwo.getNumberFloor());
		assertEquals(buildingThree.getLocation(), saveBuildingThree.getLocation());
		assertEquals(buildingThree.getDescription(), saveBuildingThree.getDescription());
		assertEquals(buildingThree.getNumberFlat(), saveBuildingThree.getNumberFlat());
		assertEquals(buildingThree.getElevator(), saveBuildingThree.getElevator());
		assertEquals(buildingThree.getNumberFloor(), saveBuildingThree.getNumberFloor());
	}

	@Test
	public void shouldRemoveBuildingByLocationWhenBuildingWasFirstSave() {

		// given
		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withLocation("Os. Rusa").withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		buildingDao.removeByLocation("Rataje");

		// then
		Assert.assertNull(buildingDao.findById(saveBuildingOne.getId()));
		Assert.assertEquals(2, buildingDao.count());
		assertEquals(buildingTwo.getLocation(), saveBuildingTwo.getLocation());
		assertEquals(buildingTwo.getDescription(), saveBuildingTwo.getDescription());
		assertEquals(buildingTwo.getNumberFlat(), saveBuildingTwo.getNumberFlat());
		assertEquals(buildingTwo.getElevator(), saveBuildingTwo.getElevator());
		assertEquals(buildingTwo.getNumberFloor(), saveBuildingTwo.getNumberFloor());
		assertEquals(buildingThree.getLocation(), saveBuildingThree.getLocation());
		assertEquals(buildingThree.getDescription(), saveBuildingThree.getDescription());
		assertEquals(buildingThree.getNumberFlat(), saveBuildingThree.getNumberFlat());
		assertEquals(buildingThree.getElevator(), saveBuildingThree.getElevator());
		assertEquals(buildingThree.getNumberFloor(), saveBuildingThree.getNumberFloor());
	}

	@Test
	public void shouldRemoveBuildingByLocationWhenBuildingWasInsideSave() {

		// given
		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withLocation("Os. Rusa").withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		buildingDao.removeByLocation("Os. Rusa");

		// then
		Assert.assertNull(buildingDao.findById(saveBuildingTwo.getId()));
		Assert.assertEquals(2, buildingDao.count());
		assertEquals(buildingOne.getLocation(), saveBuildingOne.getLocation());
		assertEquals(buildingOne.getDescription(), saveBuildingOne.getDescription());
		assertEquals(buildingOne.getNumberFlat(), saveBuildingOne.getNumberFlat());
		assertEquals(buildingOne.getElevator(), saveBuildingOne.getElevator());
		assertEquals(buildingOne.getNumberFloor(), saveBuildingOne.getNumberFloor());
		assertEquals(buildingThree.getLocation(), saveBuildingThree.getLocation());
		assertEquals(buildingThree.getDescription(), saveBuildingThree.getDescription());
		assertEquals(buildingThree.getNumberFlat(), saveBuildingThree.getNumberFlat());
		assertEquals(buildingThree.getElevator(), saveBuildingThree.getElevator());
		assertEquals(buildingThree.getNumberFloor(), saveBuildingThree.getNumberFloor());
	}

	@Test
	public void shouldRemoveBuildingByLocationWhenBuildingWasLastSave() {

		// given
		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withLocation("Os. Rusa").withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		buildingDao.removeByLocation("Staroleka");

		// then
		Assert.assertNull(buildingDao.findById(saveBuildingThree.getId()));
		Assert.assertEquals(2, buildingDao.count());
		assertEquals(buildingOne.getLocation(), saveBuildingOne.getLocation());
		assertEquals(buildingOne.getDescription(), saveBuildingOne.getDescription());
		assertEquals(buildingOne.getNumberFlat(), saveBuildingOne.getNumberFlat());
		assertEquals(buildingOne.getElevator(), saveBuildingOne.getElevator());
		assertEquals(buildingOne.getNumberFloor(), saveBuildingOne.getNumberFloor());
		assertEquals(buildingTwo.getLocation(), saveBuildingTwo.getLocation());
		assertEquals(buildingTwo.getDescription(), saveBuildingTwo.getDescription());
		assertEquals(buildingTwo.getNumberFlat(), saveBuildingTwo.getNumberFlat());
		assertEquals(buildingTwo.getElevator(), saveBuildingTwo.getElevator());
		assertEquals(buildingTwo.getNumberFloor(), saveBuildingTwo.getNumberFloor());
	}

	@Test
	public void shouldRemoveBuildingByAddressWhenTwoBuildingHaveTheSameLocation() {

		// given
		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withLocation("Os. Rusa").withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Rataje").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		buildingDao.removeByLocation("Rataje");

		// then
		Assert.assertNull(buildingDao.findById(saveBuildingOne.getId()));
		Assert.assertNull(buildingDao.findById(saveBuildingThree.getId()));
		Assert.assertEquals(1, buildingDao.count());
		assertEquals(buildingTwo.getLocation(), saveBuildingTwo.getLocation());
		assertEquals(buildingTwo.getDescription(), saveBuildingTwo.getDescription());
		assertEquals(buildingTwo.getNumberFlat(), saveBuildingTwo.getNumberFlat());
		assertEquals(buildingTwo.getElevator(), saveBuildingTwo.getElevator());
		assertEquals(buildingTwo.getNumberFloor(), saveBuildingTwo.getNumberFloor());
	}

	@Test
	public void shouldCantRemoveBuildingByNullLocation() {

		// given
		BuildingEntity buildingOne = new BuildingEntity().builder()
				.withDescription("The building is located on the river.").withNumberFloor(new Integer(4))
				.withNumberFlat(new Integer(27)).withElevator(true).withLocation("Rataje").build();
		BuildingEntity buildingTwo = new BuildingEntity().builder()
				.withDescription("The building is located on the outskirts of the city.")
				.withNumberFloor(new Integer(6)).withLocation("Os. Rusa").withNumberFlat(new Integer(32))
				.withElevator(true).build();
		BuildingEntity buildingThree = new BuildingEntity().builder()
				.withDescription("The building is located in the city center opposite the cathedral.")
				.withNumberFloor(new Integer(10)).withNumberFlat(new Integer(64)).withElevator(true)
				.withLocation("Staroleka").build();
		BuildingEntity saveBuildingOne = buildingDao.save(buildingOne);
		BuildingEntity saveBuildingTwo = buildingDao.save(buildingTwo);
		BuildingEntity saveBuildingThree = buildingDao.save(buildingThree);

		// when
		buildingDao.removeByLocation(null);

		// then
		Assert.assertEquals(3, buildingDao.count());
		assertEquals(buildingOne, saveBuildingOne);
		assertEquals(buildingTwo, saveBuildingTwo);
		assertEquals(buildingThree, saveBuildingThree);
	}

}
