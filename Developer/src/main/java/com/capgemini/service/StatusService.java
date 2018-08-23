package com.capgemini.service;

import com.capgemini.types.StatusTO;

public interface StatusService {

	StatusTO savetSatus(StatusTO status);

	StatusTO updateStatus(StatusTO status);

	StatusTO findById(Long id);

	void removeById(Long id);

}
