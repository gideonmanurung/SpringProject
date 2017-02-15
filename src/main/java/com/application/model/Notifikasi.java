package com.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notifikasi 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idNotif;
	
	private String idUser;
	private String idVendor;
	private int idBuilding;
	
	public Notifikasi()
	{
		
	}
	
	public Notifikasi(int idNotif, String idUser, String idVendor, int idBuilding) {
		super();
		this.idNotif = idNotif;
		this.idUser = idUser;
		this.idVendor = idVendor;
		this.idBuilding = idBuilding;
	}
	public int getIdNotif() {
		return idNotif;
	}
	public void setIdNotif(int idNotif) {
		this.idNotif = idNotif;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getIdVendor() {
		return idVendor;
	}
	public void setIdVendor(String idVendor) {
		this.idVendor = idVendor;
	}
	public int getIdBuilding() {
		return idBuilding;
	}
	public void setIdBuilding(int idBuilding) {
		this.idBuilding = idBuilding;
	}
}
