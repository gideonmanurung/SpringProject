package com.application.dao;

import java.util.List;

import com.application.model.Notifikasi;;

public interface NotifikasiDao
{
	public Notifikasi saveOrUpdateNotification(Notifikasi notif);
	
	public void deleteNotification(int idNotif);
	
	public Notifikasi getNotificationById(int idNotif);
	
	public List<Notifikasi> getAllNotification();
}
