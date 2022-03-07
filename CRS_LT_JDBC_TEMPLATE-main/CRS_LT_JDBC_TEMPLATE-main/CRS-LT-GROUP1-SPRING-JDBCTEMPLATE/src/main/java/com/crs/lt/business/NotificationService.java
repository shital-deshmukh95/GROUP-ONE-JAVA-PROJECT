package com.crs.lt.business;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements NotificationInterface {
	private static Logger logger =Logger.getLogger(NotificationService.class);

	 public boolean sendNotification() {
		 //TODO 
		return true;
	}
}
