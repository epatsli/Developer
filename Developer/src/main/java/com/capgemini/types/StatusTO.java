package com.capgemini.types;

import java.util.List;

import com.capgemini.exception.IncorrectParameterException;

public class StatusTO {

	private Long id;
	private String statusName;
	private List<Long> flats;
	private Long version;

	public StatusTO() {
	}

	public StatusTO(StatusTOBuilder builder) {
		this.id = builder.id;
		this.statusName = builder.statusName;
		this.flats = builder.flats;
	}

	public StatusTOBuilder builder() {
		return new StatusTOBuilder();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public List<Long> getFlats() {
		return flats;
	}

	public void setFlats(List<Long> flats) {
		this.flats = flats;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public static class StatusTOBuilder {
		private Long id;
		private String statusName;
		private List<Long> flats;
		private Long version;

		public StatusTOBuilder() {
		}

		public StatusTOBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		public StatusTOBuilder withStatusName(String statusName) {
			this.statusName = statusName;
			return this;
		}

		public StatusTOBuilder withFlats(List<Long> flats) {
			this.flats = flats;
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
