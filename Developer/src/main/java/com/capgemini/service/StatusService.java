package com.capgemini.service;

import com.capgemini.types.StatusTO;

public interface StatusService {

	StatusTO saveStatus(StatusTO status);

	StatusTO updateStatus(StatusTO status);

	StatusTO findById(Long id);

	void removeById(Long id);

}
