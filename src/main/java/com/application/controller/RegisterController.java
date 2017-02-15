package com.application.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.service.BuildingService;
import com.application.service.UserService;
import com.application.model.Building;
import com.application.model.User;
@Controller
public class RegisterController 
{
	@Autowired
	private UserService uService;
	
	@Autowired
	private BuildingService bService;
	
	@RequestMapping("/register")
	public String register(Model model, HttpServletRequest request)
	{
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String noTelp = request.getParameter("noTelp");
		String alamat = request.getParameter("alamat");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		
		User user = new User(username,password,fullName,email,noTelp,alamat,role);
		uService.saveOrUpdateUser(user);
		request.getSession().setAttribute("user", user.getUsername());
		if(user.getRole().equals("User"))
		{
			model.addAttribute("user", user);
			List<Building> b= new ArrayList<Building>();
			List<Building> buildings= new ArrayList<Building>();
			if(user.getRole().equals("User"))
			{
				b = bService.getAllBuilding();
				for(Building bs : b)
				{
					if(bs.getProgress().equals("Free"))
					{
						buildings.add(bs);
					}
				}
			model.addAttribute("buildings", buildings);
			return "Home";
		}
		else
		{
			b = bService.getAllBuilding();
			for(Building bs : b)
			{
				if(bs.getUsernamepemilik().matches(request.getSession().getAttribute("user").toString()))
				{
					buildings.add(bs);
				}
			}
			model.addAttribute("buildings", buildings);
			return "HomeVendor";
		}
		}
		return "redirect:/formLogin";
	}
	
	@RequestMapping("/rules")
	public String rules()
	{
		return "Rules";
	}
}
