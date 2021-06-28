package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("users")
public class UserContoroller {

	@Autowired
	private UserService service;

	@GetMapping("")
	public String index(Model model) {
		List<User> list = service.findAll();
		System.out.println(list);
		model.addAttribute("list", list);
		return "users/index";
	}
}
