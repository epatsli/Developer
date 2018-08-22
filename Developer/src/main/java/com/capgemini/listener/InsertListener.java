package com.capgemini.listener;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.PrePersist;

import com.capgemini.domain.AbstractListenerEntity;

public class InsertListener {

	@PrePersist
	protected void create(AbstractListenerEntity abstractListenerEntity) {
		Date date = new Date();
		abstractListenerEntity.setCreateTime(new Timestamp(date.getTime()));
	}
}
