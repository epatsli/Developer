package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.FlatEntity;
import com.capgemini.domain.SearchCriteriaEntity;

public interface SearchCriteriaDao extends Dao<FlatEntity, Long> {

	List<FlatEntity> findFlatByArea(Double areaFlatFrom, Double areaFlatTo);

	List<FlatEntity> findFlatByNumberRoom(Integer numberRoomFrom, Integer numberRoomTo);

	List<FlatEntity> findFlatByNumberBalconies(Integer numberBalconiesFrom, Integer numberBalconiesTo);

	List<FlatEntity> findFlatByCriteria(SearchCriteriaEntity searchCriteria);
}
