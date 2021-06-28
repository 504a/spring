package com.example.demo.controller;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.UserForm;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("users")
public class UserContoroller {

	final static Map<String, Boolean> RADIO_ITEMS = Collections.unmodifiableMap(new LinkedHashMap<String, Boolean>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("男", true);
			put("女", false);

		};
	});

	@Autowired
	private UserService service;

	@GetMapping("")
	public String index(Model model) {
		List<User> list = service.findAll();
		System.out.println(list);
		model.addAttribute("list", list);
		return "users/index";
	}

	@PostMapping("/create/form/")
	public String createForm(@ModelAttribute UserForm form, Model model) {
		model.addAttribute("radioItems", RADIO_ITEMS);
		return "student/create/form";
	}
}
