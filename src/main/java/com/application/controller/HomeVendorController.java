package com.application.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.application.model.Building;
import com.application.model.Transaction;
import com.application.model.User;
import com.application.service.BuildingService;
import com.application.service.TransactionService;
import com.application.service.UserService;

@Controller
public class HomeVendorController {

	@Autowired
	private BuildingService bService;
	
	@Autowired
	private TransactionService tService;
	
	@Autowired
	private UserService uService;
	
	@RequestMapping("/HomeVendor")
	public String homeVendor(Model model, HttpServletRequest request)
	{
		List<Building> buildings= new ArrayList<Building>();
		List<Building> bs= new ArrayList<Building>();
		bs = bService.getAllBuilding();
		for(Building b : bs)
		{
			if(b.getUsernamepemilik().matches(request.getSession().getAttribute("user").toString()))
			{
				buildings.add(b);
			}
		}
		model.addAttribute("buildings", buildings);
		return "HomeVendor";
	}
	
	@RequestMapping("/formAddRent")
	public String formAddRent()
	{
		return "AddRent";
	}
	
	@RequestMapping("/formAddSell")
	public String formAddSell()
	{
		return "AddSell";
	}
	
	@RequestMapping("/addRent")
	public String addRent(HttpServletRequest request)
	{
		String namaBangunan = request.getParameter("namabangunan");
		String alamatBangunan = request.getParameter("alamatbangunan");
		String kategori = request.getParameter("kategori");
		String ukuran = request.getParameter("ukuran");
		String deskripsi = request.getParameter("deskripsi");
		String harga = request.getParameter("harga");
		String image = request.getParameter("image");
		String userPemilik = request.getSession().getAttribute("user").toString();
		String status = "Rent";
		
		Building b = new Building(0,image,namaBangunan,alamatBangunan,ukuran,deskripsi,userPemilik,harga,kategori,status,"Free");
		bService.saveOrUpdateBuilding(b);
		return "redirect:/HomeVendor";
	}
	
	@RequestMapping("/addSell")
	public String addSell(HttpServletRequest request)
	{
		String namaBangunan = request.getParameter("namabangunan");
		String alamatBangunan = request.getParameter("alamatbangunan");
		String kategori = request.getParameter("kategori");
		String ukuran = request.getParameter("ukuran");
		String deskripsi = request.getParameter("deskripsi");
		String harga = request.getParameter("harga");
		String image = request.getParameter("image");
		String userPemilik = request.getSession().getAttribute("user").toString();
		String status = "Sell";
		
		Building b = new Building(0,image,namaBangunan,alamatBangunan,ukuran,deskripsi,userPemilik,harga,kategori,status,"Free");
		bService.saveOrUpdateBuilding(b);
		return "redirect:/HomeVendor";
	}
	
	@RequestMapping("/requestList")
	public String requestList(Model model, HttpServletRequest request)
	{
		List<Building> bApprove = new ArrayList<Building>();
		List<Building> bNApprove = new ArrayList<Building>();
		List<Transaction> tApprove = new ArrayList<Transaction>();
		List<Transaction> tNApprove = new ArrayList<Transaction>();
		List<Transaction> ts = new ArrayList<Transaction>();
		ts = tService.getAllTransaction();
		for(Transaction t : ts)
		{
			if(t.getIdVendor().matches(request.getSession().getAttribute("user").toString())&&t.getStatus().equals("Waiting"))
			{
				bNApprove.add(bService.getBuildingById(t.getIdBuilding()));
				tNApprove.add(t);
			}
			if(t.getIdVendor().matches(request.getSession().getAttribute("user").toString())&&t.getStatus().equals("Approve"))
			{
				bApprove.add(bService.getBuildingById(t.getIdBuilding()));
				tApprove.add(t);
			}
		}
		model.addAttribute("bApprove" , bApprove);
		model.addAttribute("bNApprove" , bNApprove);
		model.addAttribute("tApprove" , tApprove);
		model.addAttribute("tNApprove" , tNApprove);
		return "RequestList";
	}
	
	@RequestMapping(value="/detailRequestRent" , method = RequestMethod.POST)
	public String detailRequestRent(HttpServletRequest request,Model model)
	{
		int idTransaction = Integer.parseInt(request.getParameter("idTransaction").toString());
		Transaction t = tService.getTransactionById(idTransaction);
		Building b = bService.getBuildingById(t.getIdBuilding());
		User u = uService.getUserByUsername(t.getIdUser());
		model.addAttribute("buildings" , b);
		model.addAttribute("transactions" , t);
		model.addAttribute("users" , u);
		return "DetailRequestRent";
	}
	
	@RequestMapping(value="/detailRequestSell" , method = RequestMethod.POST)
	public String detailRequestSell(HttpServletRequest request,Model model)
	{
		int idTransaction = Integer.parseInt(request.getParameter("idTransaction").toString());
		Transaction t = tService.getTransactionById(idTransaction);
		Building b = bService.getBuildingById(t.getIdBuilding());
		User u = uService.getUserByUsername(t.getIdUser());
		model.addAttribute("buildings" , b);
		model.addAttribute("transactions" , t);
		model.addAttribute("users" , u);
		return "DetailRequestSell";
	}
	
	@RequestMapping(value="/formEditBuilding" , method = RequestMethod.POST)
	public String formEditBuilding(HttpServletRequest request, Model model)
	{
		int idBuilding = Integer.parseInt(request.getParameter("idBuilding").toString());
		Building buildings = bService.getBuildingById(idBuilding);
		model.addAttribute("buildings" , buildings);
		return "EditBuilding";
	}
	
	@RequestMapping("editProfilVendor")
	public String editProfil(Model model, HttpServletRequest request)
	{
		model.addAttribute("users" , uService.getUserByUsername(request.getSession().getAttribute("user").toString()));
		return "ProfileVendor";
	}
	
	@RequestMapping(value="/editBuilding" , method = RequestMethod.POST)
	public String editBuilding(HttpServletRequest request)
	{
		int idBuilding = Integer.parseInt(request.getParameter("idBuilding").toString());
		Building b = bService.getBuildingById(idBuilding);
		String namaBangunan = request.getParameter("namabangunan");
		String alamatBangunan = request.getParameter("alamatbangunan");
		String kategori = request.getParameter("kategori");
		String ukuran = request.getParameter("ukuran");
		String deskripsi = request.getParameter("deskripsi");
		String harga = request.getParameter("harga");
		String image = request.getParameter("image");
		b.setNamabangunan(namaBangunan);
		b.setAlamatbangunan(alamatBangunan);
		b.setKategori(kategori);
		b.setUkuran(ukuran);
		b.setDeskripsi(deskripsi);
		b.setHarga(harga);
		b.setImage(image);
		bService.saveOrUpdateBuilding(b);
		return "redirect:/HomeVendor";
	}
	
	@RequestMapping(value="/approvedRequest" , method = RequestMethod.POST)
	public String approveRequest(HttpServletRequest request, Model model)
	{
		int idTransaction = Integer.parseInt(request.getParameter("idTransaction").toString());
		Transaction t = tService.getTransactionById(idTransaction);
		t.setStatus("Approve");
		tService.saveOrUpdateTransaction(t);
		return "redirect:/HomeVendor";
	}
}
