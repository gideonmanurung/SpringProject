package com.application.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.dao.BuildingDao;
import com.application.model.Building;

@Repository
public class BuildingDaoImpl implements BuildingDao
{
	private EntityManagerFactory emf;
	
	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory emf)
	{
		this.emf = emf;
	}

	@Override
	public Building saveOrUpdateBuilding(Building building) 
	{
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(building);
		em.getTransaction().commit();
		return null;
	}

	@Override
	public void deleteBuilding(int idBuilding) 
	{
		EntityManager em = emf.createEntityManager();
		Building building = (Building) getBuildingById(idBuilding);
		em.getTransaction().begin();
		em.remove(building);
		em.getTransaction().commit();
	}

	@Override
	public Building getBuildingById(int idBuilding) 
	{
		EntityManager em = emf.createEntityManager();
		return em.find(Building.class, idBuilding);
	}

	@Override
	public List<Building> getAllBuilding() 
	{
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Building" , Building.class).getResultList();
	}
}
