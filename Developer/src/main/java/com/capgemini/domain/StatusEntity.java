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

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ Listener.class })
@Table(name = "STATUS")
public class StatusEntity implements Serializable {
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

	public StatusEntity() {
	}

	public StatusEntity(StatusEntityBuilder builder) {
		this.id = builder.id;
		this.statusName = builder.statusName;
		this.flatsInStatus = builder.flatsInStatus;
		this.version = builder.version;
	}

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

	public static class StatusEntityBuilder {
		private Long id;
		private String statusName;
		private List<FlatEntity> flatsInStatus;
		private Long version;

		public StatusEntityBuilder() {
		}

		public StatusEntityBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		public StatusEntityBuilder withStatusName(String statusName) {
			this.statusName = statusName;
			return this;
		}

		public StatusEntityBuilder withFlatsInStatus(List<FlatEntity> flatsInStatus) {
			this.flatsInStatus = flatsInStatus;
			return this;
		}

		public StatusEntityBuilder withVersion(Long version) {
			this.version = version;
			return this;
		}

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
