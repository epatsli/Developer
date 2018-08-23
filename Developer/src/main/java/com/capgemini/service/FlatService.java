package com.capgemini.service;

import java.util.List;

import com.capgemini.types.FlatTO;

public interface FlatService {

	FlatTO saveFlat(FlatTO flat);

	FlatTO updateFlat(FlatTO flat);

	FlatTO findById(Long id);

	List<FlatTO> findByNumberRooms(Integer numberRooms);

	void removeById(Long id);

}
