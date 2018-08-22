package com.capgemini.listener;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.PreUpdate;

import com.capgemini.domain.AbstractListenerEntity;

public class UpdateListener {

	@PreUpdate
	protected void update(AbstractListenerEntity abstractListenerEntity) {
		Date date = new Date();
		abstractListenerEntity.setUpdateTime(new Timestamp(date.getTime()));
	}
}
