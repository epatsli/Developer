package com.capgemini.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.capgemini.exception.IncorrectParameterException;
import com.capgemini.listener.Listener;

/**
 * StatusEntity
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ Listener.class })
@Table(name = "STATUS")
public class StatusEntity extends AbstractListenerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Version
	private Long version;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "statusName", length = 16, nullable = false)
	private String statusName;

	@OneToMany(mappedBy = "flatStatus")
	private List<FlatEntity> flatsInStatus = new ArrayList<>();

	/**
	 * No-argument constructor
	 */
	public StatusEntity() {
	}

	/**
	 * This method build new status object.
	 * 
	 * @param builder
	 *            object StatusEntityBuilder
	 */
	public StatusEntity(StatusEntityBuilder builder) {
		this.id = builder.id;
		this.statusName = builder.statusName;
		this.flatsInStatus = builder.flatsInStatus;
		this.version = builder.version;
	}

	/**
	 * This method build new object.
	 * 
	 * @return new status object
	 */
	public StatusEntityBuilder builder() {
		return new StatusEntityBuilder();
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
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

	public List<FlatEntity> getFlatsInStatus() {
		return flatsInStatus;
	}

	public void setFlatsInStatus(List<FlatEntity> flatsInStatus) {
		this.flatsInStatus = flatsInStatus;
	}

	/**
	 * Status class
	 *
	 */
	public static class StatusEntityBuilder {
		private Long id;
		private String statusName;
		private List<FlatEntity> flatsInStatus;
		private Long version;

		/**
		 * No-argument constructor
		 */
		public StatusEntityBuilder() {
		}

		/**
		 * This method return status with id.
		 * 
		 * @param id
		 *            index status
		 * @return new status object with id
		 */
		public StatusEntityBuilder withId(Long id) {
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
		public StatusEntityBuilder withStatusName(String statusName) {
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
		public StatusEntityBuilder withFlatsInStatus(List<FlatEntity> flatsInStatus) {
			this.flatsInStatus = flatsInStatus;
			return this;
		}

		/**
		 * This method return status with version.
		 * 
		 * @param version
		 *            version status
		 * @return new status object with version
		 */
		public StatusEntityBuilder withVersion(Long version) {
			this.version = version;
			return this;
		}

		/**
		 * This method build new status object.
		 * 
		 * @return new status object
		 */
		public StatusEntity build() {
			checkBeforeBuild();
			return new StatusEntity(this);
		}

		private void checkBeforeBuild() {

			if (statusName == null) {
				throw new IncorrectParameterException("This status can't be created.");
			}

		}

	}
}
