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
public class WelcomeController 
{
	@Autowired
	private BuildingService bService;
	@Autowired
	private UserService uService;
	@Autowired
	private TransactionService tService;
	
	@RequestMapping("/")
	public String welcome(HttpServletRequest request, Model model)
	{
		List<Building> buildings= new ArrayList<Building>();
		List<Building> bs = new ArrayList<Building>();
		bs = bService.getAllBuilding();
		if(request.getSession().getAttribute("user")!=null)
		{
			User u = uService.getUserByUsername(request.getSession().getAttribute("user").toString());
			if(u.getRole().equals("User"))
			{
				for(Building b : bs)
				{
					if(b.getProgress().equals("Free"))
					{
						buildings.add(b);
					}
				}
				model.addAttribute("buildings" , buildings);
				return "Home";
			}
			else
			{
				for(Building b : bs)
				{
					if(b.getProgress().equals("Free")&&b.getUsernamepemilik().matches(request.getSession().getAttribute("user").toString()))
					{
						buildings.add(b);
					}
				}
				model.addAttribute("buildings" , buildings);
				return "HomeVendor";
			}
		}
		else
		{
			for(Building b : bs)
			{
				if(b.getProgress().equals("Free"))
				{
					buildings.add(b);
				}
			}
			model.addAttribute("buildings" , buildings);
		}
		return "Welcome";
	}
	
	@RequestMapping("/formLogin")
	public String formLogin()
	{
		return "FormLogin";
	}
	
	@RequestMapping(value="/detailBuilding" , method = RequestMethod.POST)
	public String detailBuilding(HttpServletRequest request,Model model)
	{
		int idBuilding = Integer.parseInt(request.getParameter("idBuilding").toString());
		model.addAttribute("buildings", bService.getBuildingById(idBuilding));
		if(request.getSession().getAttribute("user")==null)
		{
			return "DetailBuilding";
		}
		return "DetailBuildingLogin";
	}
	
	@RequestMapping(value="/rent" , method = RequestMethod.POST)
	public String rent(HttpServletRequest request)
	{
		if(request.getSession().getAttribute("user")!=null)
		{
			String idUser = request.getSession().getAttribute("user").toString();
			int idBuilding = Integer.parseInt(request.getParameter("idBuilding").toString());
			int duration = Integer.parseInt(request.getParameter("duration").toString());
			Building b = bService.getBuildingById(idBuilding);
			b.setProgress("Booked");
			Transaction ts = new Transaction(idUser,b.getUsernamepemilik(),idBuilding,b.getKategori(),duration,"Waiting");
			tService.saveOrUpdateTransaction(ts);
			bService.saveOrUpdateBuilding(b);
			return "redirect:/";
		}
		return "FormLogin";
	}
	
	@RequestMapping(value="/detailBuy" , method = RequestMethod.POST)
	public String buyBeforeConfirm(HttpServletRequest request,Model model)
	{
		if(request.getSession().getAttribute("user")!=null)
		{
			int idBuilding = Integer.parseInt(request.getParameter("idBuilding").toString());
			User users = uService.getUserByUsername(request.getSession().getAttribute("user").toString());
			model.addAttribute("users" , users);
			model.addAttribute("buildings" , bService.getBuildingById(idBuilding));
			return "FormConfirm";
		}
		return "FormLogin";
	}
	
	@RequestMapping("/aboutUs")
	public String aboutUs()
	{
		return "AboutUs";
	}
}
