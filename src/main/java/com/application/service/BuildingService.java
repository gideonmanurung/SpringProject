package com.application.service;

import java.util.List;

import com.application.model.Building;

public interface BuildingService 
{
	public void saveOrUpdateBuilding(Building building);
	
	public void deleteBuilding(int idBuilding);
	
	public Building getBuildingById(int idBuilding);
	
	public List<Building> getAllBuilding();
}
