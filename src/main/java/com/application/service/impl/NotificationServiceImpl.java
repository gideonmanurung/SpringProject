package com.application.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.dao.NotifikasiDao;
import com.application.model.Notifikasi;
import com.application.service.NotificationService;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService
{
	@Autowired
	private NotifikasiDao nDao;

	@Override
	public void saveOrUpdateNotification(Notifikasi notif) 
	{
		nDao.saveOrUpdateNotification(notif);
	}

	@Override
	public void deleteNotification(int idNotif) 
	{
		nDao.deleteNotification(idNotif);
	}

	@Override
	public Notifikasi getNotificationById(int idNotif) 
	{
		return nDao.getNotificationById(idNotif);
	}

	@Override
	public List<Notifikasi> getAllNotification() 
	{
		return nDao.getAllNotification();
	}

}
