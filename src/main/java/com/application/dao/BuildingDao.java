package com.application.dao;

import java.util.List;

import com.application.model.Building;;

public interface BuildingDao 
{
	public Building saveOrUpdateBuilding(Building building);
	
	public void deleteBuilding(int idBuilding);
	
	public Building getBuildingById(int idBuilding);
	
	public List<Building> getAllBuilding();
}
