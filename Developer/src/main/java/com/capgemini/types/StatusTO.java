package com.capgemini.types;

import java.util.List;

public class StatusTO {

	private Long idStatus;
	private String statusName;
	private List<Long> listFlat;

	public StatusTO() {
	}

	public StatusTO(String statusName) {
		this.statusName = statusName;
	}

	public StatusTO(Long idStatus, String statusName) {
		this.idStatus = idStatus;
		this.statusName = statusName;
	}

}
