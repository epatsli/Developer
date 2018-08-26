package com.capgemini.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.capgemini.domain.FlatEntity;
import com.capgemini.domain.StatusEntity;
import com.capgemini.domain.StatusEntity.StatusEntityBuilder;
import com.capgemini.types.StatusTO;
import com.capgemini.types.StatusTO.StatusTOBuilder;

/**
 * tatus mapper
 *
 */
@Component
public class StatusMapper {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * This method change status entity to status TO.
	 * 
	 * @param statusEntity
	 *            status entity
	 * @return status TO
	 */
	public StatusTO toStatusTO(StatusEntity statusEntity) {
		if (statusEntity == null)
			return null;

		StatusTOBuilder newStatusTO = new StatusTOBuilder().withId(statusEntity.getId())
				.withStatusName(statusEntity.getStatusName()).withVersion(statusEntity.getVersion());

		if (statusEntity.getFlatsInStatus() != null) {
			newStatusTO.withFlats(
					statusEntity.getFlatsInStatus().stream().map(s -> s.getId()).collect(Collectors.toList()));
		}
		return newStatusTO.build();
	}

	/**
	 * This method change status TO to status entity.
	 * 
	 * @param statusTO
	 *            status TO
	 * @return status entity
	 */
	public StatusEntity toStatusEntity(StatusTO statusTO) {

		if (statusTO == null)
			return null;

		List<Long> flats = statusTO.getFlats();
		List<FlatEntity> newflats = new ArrayList<>();
		if (flats != null) {
			for (Long idFlat : flats) {
				newflats.add(entityManager.getReference(FlatEntity.class, idFlat));
			}
		}

		StatusEntityBuilder statusEntityBuilder = new StatusEntityBuilder().withId(statusTO.getId())
				.withStatusName(statusTO.getStatusName()).withFlatsInStatus(newflats)
				.withVersion(statusTO.getVersion());
		return statusEntityBuilder.build();

	}

	// public List<StatusTO> map2TOs(List<StatusEntity> statusEntitys) {
	// return
	// statusEntitys.stream().map(this::toStatusTO).collect(Collectors.toList());
	// }
	//
	// public List<StatusEntity> map2Entities(List<StatusTO> statusTOs) {
	// return
	// statusTOs.stream().map(this::toStatusEntity).collect(Collectors.toList());
	// }

	/**
	 * This method change index status to new status object with index.
	 * 
	 * @param statusLong
	 *            index status
	 * @return new status object with index
	 */
	public StatusEntity map2EntityLong(Long statusLong) {
		return entityManager.getReference(StatusEntity.class, statusLong);
	}
}
