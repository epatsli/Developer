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

@Component
public class StatusMapper {

	@PersistenceContext
	private EntityManager entityManager;

	public StatusTO toStatusTO(StatusEntity statusEntity) {
		if (statusEntity == null)
			return null;

		StatusTOBuilder newStatusTO = new StatusTOBuilder().withIdStatus(statusEntity.getIdStatus())
				.withStatusName(statusEntity.getStatusName());

		if (statusEntity.getListFlat() != null) {
			newStatusTO.withListFlat(
					statusEntity.getListFlat().stream().map(s -> s.getIdFlat()).collect(Collectors.toList()));
		}
		return newStatusTO.build();
	}

	public StatusEntity toStatusEntity(StatusTO statusTO) {

		if (statusTO == null)
			return null;

		List<Long> listFlat = statusTO.getListFlat();
		List<FlatEntity> newListFlat = new ArrayList<>();
		if (listFlat != null) {
			for (Long idFlat : listFlat) {
				newListFlat.add(entityManager.getReference(FlatEntity.class, idFlat));
			}
		}

		StatusEntityBuilder statusEntityBuilder = new StatusEntityBuilder().withIdStatus(statusTO.getIdStatus())
				.withStatusName(statusTO.getStatusName()).withListFlat(newListFlat);
		return statusEntityBuilder.build();

	}

	public List<StatusTO> map2TOs(List<StatusEntity> statusEntitys) {
		return statusEntitys.stream().map(this::toStatusTO).collect(Collectors.toList());
	}

	public List<StatusEntity> map2Entities(List<StatusTO> statusTOs) {
		return statusTOs.stream().map(this::toStatusEntity).collect(Collectors.toList());
	}

}
