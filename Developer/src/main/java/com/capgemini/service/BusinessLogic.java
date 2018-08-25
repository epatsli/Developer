package com.capgemini.service;

import java.util.List;

import com.capgemini.types.ClientTO;
import com.capgemini.types.FlatTO;

public interface BusinessLogic {

	List<ClientTO> ffindClientWhoBuyOrBookFlat(FlatTO flat);

	Boolean bookFlatByClient(ClientTO client, FlatTO flat);

}
