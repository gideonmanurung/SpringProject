package com.application.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.dao.BuildingDao;
import com.application.model.Building;
import com.application.service.BuildingService;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService
{
	@Autowired
	private BuildingDao bDao;

	@Override
	public void saveOrUpdateBuilding(Building building) 
	{
		bDao.saveOrUpdateBuilding(building);
	}

	@Override
	public void deleteBuilding(int idBuilding) 
	{
		bDao.deleteBuilding(idBuilding);
	}

	@Override
	public Building getBuildingById(int idBuilding) 
	{
		return bDao.getBuildingById(idBuilding);
	}

	@Override
	public List<Building> getAllBuilding() 
	{
		return bDao.getAllBuilding();
	}
}
