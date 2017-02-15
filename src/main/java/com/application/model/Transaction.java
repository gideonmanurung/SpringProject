package com.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int idTransaction;
	
	private String idUser;
	private String idVendor;
	private int idBuilding;
	private String kategori;
	private int duration;
	private String status;
	
	public Transaction()
	{
		
	}
	
	public Transaction(String idUser, String idVendor, int idBuilding,String kategori, int duration, String status) {
		super();
		this.idUser = idUser;
		this.idVendor = idVendor;
		this.idBuilding = idBuilding;
		this.kategori = kategori;
		this.duration = duration;
		this.status = status;
	}
	public int getIdRentTransaction() {
		return idTransaction;
	}
	public void setIdRentTransaction(int idRentTransaction) {
		this.idTransaction = idRentTransaction;
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
	public String getKategori() {
		return kategori;
	}
	public void setKategori(String kategori) {
		this.kategori = kategori;
	}

	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
