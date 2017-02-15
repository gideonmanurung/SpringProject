package com.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Building 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idBuilding;
	
	private String image;
	private String namabangunan;
	private String alamatbangunan;
	private String ukuran;
	private String deskripsi;
	private String usernamepemilik;
	private String harga;
	private String kategori;
	private String status;
	private String progress;
	
	public Building()
	{	
	}

	public Building(int idBuilding, String image, String namabangunan, String alamatbangunan, String ukuran,
			String deskripsi, String usernamepemilik, String harga, String kategori, String status,String progress) {
		super();
		this.idBuilding = idBuilding;
		this.image = image;
		this.namabangunan = namabangunan;
		this.alamatbangunan = alamatbangunan;
		this.ukuran = ukuran;
		this.deskripsi = deskripsi;
		this.usernamepemilik = usernamepemilik;
		this.harga = harga;
		this.kategori = kategori;
		this.status = status;
		this.progress = progress;
	}



	public int getIdBuilding() {
		return idBuilding;
	}

	public void setIdBuilding(int id) {
		this.idBuilding = id;
	}

	public String getNamabangunan() {
		return namabangunan;
	}

	public void setNamabangunan(String namabangunan) {
		this.namabangunan = namabangunan;
	}

	public String getAlamatbangunan() {
		return alamatbangunan;
	}

	public void setAlamatbangunan(String alamatbangunan) {
		this.alamatbangunan = alamatbangunan;
	}
	
	public String getUkuran() {
		return ukuran;
	}

	public void setUkuran(String ukuran) {
		this.ukuran = ukuran;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public String getUsernamepemilik() {
		return usernamepemilik;
	}

	public void setUsernamepemilik(String usernamepemilik) {
		this.usernamepemilik = usernamepemilik;
	}

	public String getHarga() {
		return harga;
	}

	public void setHarga(String harga) {
		this.harga = harga;
	}

	public String getKategori() {
		return kategori;
	}

	public void setKategori(String kategori) {
		this.kategori = kategori;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}
}
