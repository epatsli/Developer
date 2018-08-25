package com.capgemini.listener;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.capgemini.domain.AbstractListenerEntity;

/**
 * Listener class
 *
 */
public class Listener {

	/**
	 * This method set create time.
	 * 
	 * @param abstractListenerEntity
	 */
	@PrePersist
	protected void create(AbstractListenerEntity abstractListenerEntity) {
		Date date = new Date();
		abstractListenerEntity.setCreateTime(new Timestamp(date.getTime()));
	}

	/**
	 * This method set update time.
	 * 
	 * @param abstractListenerEntity
	 */
	@PreUpdate
	protected void update(AbstractListenerEntity abstractListenerEntity) {
		Date date = new Date();
		abstractListenerEntity.setUpdateTime(new Timestamp(date.getTime()));
	}

}
