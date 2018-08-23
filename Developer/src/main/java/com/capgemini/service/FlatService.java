package com.capgemini.service;

import java.util.List;

import com.capgemini.types.FlatTO;

public interface FlatService {

	FlatTO saveClient(FlatTO flat);

	FlatTO updateClient(FlatTO flat);

	FlatTO findById(Long id);

	List<FlatTO> findByNumberRooms(Integer numberRooms);

	void removeById(Long id);

}
