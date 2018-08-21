package com.capgemini.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.capgemini.exception.IncorrectParameterException;

@Entity
@Table(name = "STATUS")
public class StatusEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Version
	public Long version;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idStatus;

	@Column(name = "statusName", length = 16, nullable = false)
	private String statusName;

	@OneToMany(mappedBy = "flatStatus")
	private List<FlatEntity> listFlat = new ArrayList<>();

	public StatusEntity() {
	}

	public StatusEntity(StatusEntityBuilder builder) {
		this.idStatus = builder.idStatus;
		this.statusName = builder.statusName;
		this.listFlat = builder.listFlat;
	}

	public StatusEntityBuilder builder() {
		return new StatusEntityBuilder();
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

	public List<FlatEntity> getListFlat() {
		return listFlat;
	}

	public void setListFlat(List<FlatEntity> listFlat) {
		this.listFlat = listFlat;
	}

	public static class StatusEntityBuilder {
		private Long idStatus;
		private String statusName;
		private List<FlatEntity> listFlat;

		public StatusEntityBuilder() {
		}

		public StatusEntityBuilder withIdStatus(Long idStatus) {
			this.idStatus = idStatus;
			return this;
		}

		public StatusEntityBuilder withStatusName(String statusName) {
			this.statusName = statusName;
			return this;
		}

		public StatusEntityBuilder withListFlat(List<FlatEntity> listFlat) {
			this.listFlat = listFlat;
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
