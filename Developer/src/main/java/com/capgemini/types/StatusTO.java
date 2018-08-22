package com.capgemini.types;

import java.util.List;

import com.capgemini.exception.IncorrectParameterException;

public class StatusTO {

	private Long idStatus;
	private String statusName;
	private List<Long> listFlat;
	private Long version;

	public StatusTO() {
	}

	public StatusTO(StatusTOBuilder builder) {
		this.idStatus = builder.idStatus;
		this.statusName = builder.statusName;
		this.listFlat = builder.listFlat;
	}

	public StatusTOBuilder builder() {
		return new StatusTOBuilder();
	}

	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public List<Long> getListFlat() {
		return listFlat;
	}

	public void setListFlat(List<Long> listFlat) {
		this.listFlat = listFlat;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public static class StatusTOBuilder {
		private Long idStatus;
		private String statusName;
		private List<Long> listFlat;
		private Long version;

		public StatusTOBuilder() {
		}

		public StatusTOBuilder withIdStatus(Long idStatus) {
			this.idStatus = idStatus;
			return this;
		}

		public StatusTOBuilder withStatusName(String statusName) {
			this.statusName = statusName;
			return this;
		}

		public StatusTOBuilder withListFlat(List<Long> listFlat) {
			this.listFlat = listFlat;
			return this;
		}

		public StatusTOBuilder withVersion(Long version) {
			this.version = version;
			return this;
		}

		public StatusTO build() {
			checkBeforeBuild();
			return new StatusTO(this);
		}

		private void checkBeforeBuild() {

			if (statusName == null) {
				throw new IncorrectParameterException("This status can't be created.");
			}

		}

	}

}
