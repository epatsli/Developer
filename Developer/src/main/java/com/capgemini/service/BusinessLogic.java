package com.capgemini.service;

import com.capgemini.types.ClientTO;

public interface BusinessLogic {

	ClientTO findClientWhoBuyFlat(Long id);

	ClientTO findClientWhoBookFlat(Long id);

	Boolean bookFlatByClient(Long idClient);

}
