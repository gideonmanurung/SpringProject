package com.application.service;

import java.util.List;

import com.application.model.Notifikasi;

public interface NotificationService 
{
	public void saveOrUpdateNotification(Notifikasi notif);
	
	public void deleteNotification(int idNotif);
	
	public Notifikasi getNotificationById(int idNotif);
	
	public List<Notifikasi> getAllNotification();
}
