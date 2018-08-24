package com.capgemini.service;

import com.capgemini.types.FlatTO;

public interface FlatService {

	FlatTO saveFlat(FlatTO flat);

	FlatTO updateFlat(FlatTO flat);

	FlatTO findById(Long id);

	void removeById(Long id);

}
