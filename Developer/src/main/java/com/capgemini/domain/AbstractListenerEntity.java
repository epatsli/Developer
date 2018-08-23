package com.capgemini.domain;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capgemini.listener.Listener;

@MappedSuperclass
@EntityListeners({ Listener.class })
public class AbstractListenerEntity {

	@Column(name = "createTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@Column(name = "updateTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	public void setCreateTime(final Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}
