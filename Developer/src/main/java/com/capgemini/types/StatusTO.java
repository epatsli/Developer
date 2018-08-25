package com.capgemini.types;

import java.util.List;

import com.capgemini.exception.IncorrectParameterException;

/**
 * StatusService
 *
 */
public class StatusTO {

	private Long id;
	private String statusName;
	private List<Long> flats;
	private Long version;

	/**
	 * No-argument constructor
	 */
	public StatusTO() {
	}

	/**
	 * This method build new status object.
	 * 
	 * @param builder
	 *            object StatusTOBuilder
	 */
	public StatusTO(StatusTOBuilder builder) {
		this.id = builder.id;
		this.statusName = builder.statusName;
		this.flats = builder.flats;
	}

	/**
	 * This method build new object.
	 * 
	 * @return new status object
	 */
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

	/**
	 * Status class
	 *
	 */
	public static class StatusTOBuilder {
		private Long id;
		private String statusName;
		private List<Long> flats;
		private Long version;

		/**
		 * No-argument constructor
		 */
		public StatusTOBuilder() {
		}

		/**
		 * This method return status with id.
		 * 
		 * @param id
		 *            index status
		 * @return new status object with id
		 */
		public StatusTOBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		/**
		 * This method return status with status name.
		 * 
		 * @param statusName
		 *            name status
		 * @return new status object with name
		 */
		public StatusTOBuilder withStatusName(String statusName) {
			this.statusName = statusName;
			return this;
		}

		/**
		 * This method return status with list flats which have this status.
		 * 
		 * @param flatsInStatus
		 *            list flats which have this status
		 * @return new status object with list flats with this status
		 */
		public StatusTOBuilder withFlats(List<Long> flats) {
			this.flats = flats;
			return this;
		}

		/**
		 * This method return status with version.
		 * 
		 * @param version
		 *            version status
		 * @return new status object with version
		 */
		public StatusTOBuilder withVersion(Long version) {
			this.version = version;
			return this;
		}

		/**
		 * This method build new status object.
		 * 
		 * @return new status object
		 */
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
