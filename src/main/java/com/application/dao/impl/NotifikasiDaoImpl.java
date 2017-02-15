package com.application.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.dao.NotifikasiDao;
import com.application.model.Notifikasi;

@Repository
public class NotifikasiDaoImpl implements NotifikasiDao
{
	private EntityManagerFactory emf;
	
	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory emf)
	{
		this.emf = emf;
	}

	@Override
	public Notifikasi saveOrUpdateNotification(Notifikasi notif) 
	{
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(notif);
		em.getTransaction().commit();
		Notifikasi nt = (Notifikasi)notif;
		return nt;
	}

	@Override
	public void deleteNotification(int idNotif) 
	{
		EntityManager em = emf.createEntityManager();
		Notifikasi nt = (Notifikasi)getNotificationById(idNotif);
		em.getTransaction().begin();
		em.remove(nt);
		em.getTransaction().commit();
	}

	@Override
	public Notifikasi getNotificationById(int idNotif) 
	{
		EntityManager em = emf.createEntityManager();
		return em.find(Notifikasi.class, idNotif);
	}

	@Override
	public List<Notifikasi> getAllNotification() 
	{
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from notifikasi" ,Notifikasi.class).getResultList();
	}

}
