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
public class HomeController 
{
	@Autowired
	private BuildingService bService;
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private TransactionService tService;
	
	@RequestMapping("/Home")
	public String home(Model model, HttpServletRequest request)
	{
		List<Building> buildings= new ArrayList<Building>();
		List<Building> bs= new ArrayList<Building>();
		bs = bService.getAllBuilding();
		for(Building b : bs)
		{
			if(b.getProgress().equals("Free"))
			{
				buildings.add(b);
			}
		}
		model.addAttribute("buildings", buildings);
		return "Home";
	}
	
	@RequestMapping("/logOut")
	public String logOut(HttpServletRequest request)
	{
		request.getSession().removeAttribute("user");
		return "redirect:/";
	}
	
	@RequestMapping(value="/login" , method=RequestMethod.GET)
	public String homeLogin()
	{
		return"redirect:/";
	}
	
	@RequestMapping("editProfilUser")
	public String editProfil(Model model, HttpServletRequest request)
	{
		model.addAttribute("users" , uService.getUserByUsername(request.getSession().getAttribute("user").toString()));
		return "ProfileUser";
	}
	
	@RequestMapping("listRentHome")
	public String listRentHome(Model model)
	{
		List<Building> buildings= new ArrayList<Building>();
		List<Building> bs= new ArrayList<Building>();
		bs = bService.getAllBuilding();
		for(Building b : bs)
		{
			if(b.getProgress().equals("Free")&&b.getKategori().equals("Rumah")&&b.getStatus().equals("Rent"))
			{
				buildings.add(b);
			}
		}
		model.addAttribute("buildings" , buildings);
		return "ListRentHome";
	}
	
	@RequestMapping("listBuyHome")
	public String listBuyHome(Model model)
	{
		List<Building> buildings= new ArrayList<Building>();
		List<Building> bs= new ArrayList<Building>();
		bs = bService.getAllBuilding();
		for(Building b : bs)
		{
			if(b.getProgress().equals("Free")&&b.getKategori().equals("Rumah")&&b.getStatus().equals("Sell"))
			{
				buildings.add(b);
			}
		}
		model.addAttribute("buildings" , buildings);
		return "ListBuyHome";
	}
	
	@RequestMapping("listBuyBuilding")
	public String listBuyBuilding(Model model)
	{
		List<Building> buildings= new ArrayList<Building>();
		List<Building> bs = new ArrayList<Building>();
		bs = bService.getAllBuilding();
		for(Building b : bs)
		{
			if(b.getProgress().equals("Free")&&b.getKategori().equals("Gedung")&&b.getStatus().equals("Sell"))
			{
				buildings.add(b);
			}
		}
		model.addAttribute("buildings" , buildings);
		return "ListBuyBuilding";
	}
	
	@RequestMapping("listRentBuilding")
	public String listRentBuilding(Model model)
	{
		List<Building> buildings= new ArrayList<Building>();
		List<Building> bs = new ArrayList<Building>();
		bs = bService.getAllBuilding();
		for(Building b : bs)
		{
			if(b.getProgress().equals("Free")&&b.getKategori().equals("Gedung")&&b.getStatus().equals("Rent"))
			{
				buildings.add(b);
			}
		}
		model.addAttribute("buildings" , buildings);
		return "ListRentBuilding";
	}
	
	@RequestMapping(value="/buy" , method = RequestMethod.POST)
	public String buy(HttpServletRequest request)
	{
		String idUser = request.getSession().getAttribute("user").toString();
		int idBuilding = Integer.parseInt(request.getParameter("idBuilding").toString());
		Building b = bService.getBuildingById(idBuilding);
		b.setProgress("Booked");
		Transaction ts = new Transaction(idUser,b.getUsernamepemilik(),idBuilding,b.getKategori(),0,"Waiting");
		tService.saveOrUpdateTransaction(ts);
		bService.saveOrUpdateBuilding(b);
		return "redirect:/";
	}
	
	@RequestMapping(value="/editProfile" , method = RequestMethod.POST)
	public String editProfile(HttpServletRequest request)
	{
		String username = request.getParameter("username");
		String fullName = request.getParameter("fullname");
		String email = request.getParameter("email");
		String noTelp = request.getParameter("notelp");
		String alamat = request.getParameter("alamat");
		User u = uService.getUserByUsername(username);
		u.setFullname(fullName);
		u.setEmail(email);
		u.setNotelp(noTelp);
		u.setAlamat(alamat);
		uService.saveOrUpdateUser(u);
		if(u.getRole().equals("User"))
		{
			return "redirect:/Home";
		}
		return "redirect:/HomeVendor";
	}
	
	@RequestMapping("/approveList")
	public String approveList(Model model, HttpServletRequest request)
	{
		List<Building> bApprove = new ArrayList<Building>();
		List<Building> bNApprove = new ArrayList<Building>();
		List<Transaction> tApprove = new ArrayList<Transaction>();
		List<Transaction> tNApprove = new ArrayList<Transaction>();
		List<Transaction> ts = new ArrayList<Transaction>();
		ts = tService.getAllTransaction();
		for(Transaction t : ts)
		{
			if(t.getIdUser().matches(request.getSession().getAttribute("user").toString())&&t.getStatus().equals("Approve"))
			{
				bApprove.add(bService.getBuildingById(t.getIdBuilding()));
				tApprove.add(t);
			}
		}
		model.addAttribute("bApprove" , bApprove);
		model.addAttribute("bNApprove" , bNApprove);
		model.addAttribute("tApprove" , tApprove);
		model.addAttribute("tNApprove" , tNApprove);
		return "ApproveList";
	}
}
