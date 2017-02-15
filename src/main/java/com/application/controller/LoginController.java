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
import com.application.model.User;
import com.application.service.BuildingService;
import com.application.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService uService;
	@Autowired
	private BuildingService bService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = (User) uService.getUserByUsername(username);
		if (user != null && user.getPassword().matches(password)) {
			request.getSession().setAttribute("user", user.getUsername());
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

	@RequestMapping("/formRegister")
	public String register() {
		return "FormRegister";
	}
}
