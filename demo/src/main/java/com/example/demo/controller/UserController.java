package com.example.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.SearchNameForm;
import com.example.demo.form.UserForm;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("users")
public class UserController {

	private boolean updatePrev = false;

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

	/**
	 * 一覧表示
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("")
	public String index(Model model, Pageable pageable) {
		Page<User> page = service.findAll(pageable);
		System.out.println(page);
		model.addAttribute("page", page);
		model.addAttribute("url", "");
		return "users/index";
	}

	/**
	 * 削除確認
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@PostMapping("/delete/confirm/{id}")
	public String deleteConfirm(@PathVariable Long id, Model model) {
		Optional<User> optional = service.findById(id);
		if (optional.isPresent()) {
			model.addAttribute("user", optional.get());
			return "users/delete/confirm";
		} else {
			return "redirect:/users/";
		}
	}

	/**
	 * 削除処理
	 *
	 * @param id
	 * @return
	 */
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		service.deleteById(id);
		return "redirect:/users/";
	}

	/**
	 * 新規作成フォーム
	 *
	 * @param form
	 * @param model
	 * @return
	 */
	@PostMapping("/create/form/")
	public String createForm(@ModelAttribute UserForm form, Model model) {
		model.addAttribute("radioItems", RADIO_ITEMS);
		return "users/create/form";
	}

	/**
	 * 新規作成確認
	 *
	 * @param form
	 * @param model
	 * @return
	 */
	@PostMapping("/create/confirm/")
	public String createConfirm(@Validated @ModelAttribute UserForm form, BindingResult result, Model model) {
		if (result.hasErrors())
			return createForm(form, model);
		return "users/create/confirm";
	}

	/**
	 * 新規作成登録
	 *
	 * @param form
	 * @param model
	 * @return
	 */
	@PostMapping("/create/")
	public String create(@ModelAttribute UserForm form, Model model) {
		User user = new User();
		user.setName(form.getName());
		user.setGender(form.isGender());
		user.setBirth(LocalDate.parse(form.getBirth()));
		service.save(user);
		return "redirect:/users/";
	}

	/**
	 * 更新フォーム
	 *
	 * @param id
	 * @param form
	 * @param model
	 * @return
	 */
	@PostMapping("/update/form/{id}")
	public String updateForm(@PathVariable Long id, @ModelAttribute UserForm form, Model model) {
		model.addAttribute("radioItems", RADIO_ITEMS);
		if (!updatePrev) {
			Optional<User> optional = service.findById(id);
			if (optional.isPresent()) {
				User user = optional.get();
				form.setId(user.getId());
				form.setName(user.getName());
				form.setGender(user.isGender());
				form.setBirth(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(user.getBirth()));
			} else {
				return "redirect:/users/";
			}
		}
		return "users/update/form";
	}

	/**
	 * 更新確認
	 *
	 * @param form
	 * @param model
	 * @return
	 */
	@PostMapping("/update/confirm/")
	public String updateConfirm(@Validated @ModelAttribute UserForm form, BindingResult result, Model model) {
		if (result.hasErrors())
			return updateForm(form.getId(), form, model);
		updatePrev = true;
		return "users/update/confirm";
	}

	/**
	 * 更新処理
	 *
	 * @param form
	 * @param model
	 * @return
	 */
	@PostMapping("/update/")
	public String update(@ModelAttribute UserForm form, Model model) {
		User user = new User();
		user.setId(form.getId());
		user.setName(form.getName());
		user.setGender(form.isGender());
		user.setBirth(LocalDate.parse(form.getBirth()));
		service.save(user);
		return "redirect:/users/";
	}

	/**
	 * 検索フォーム表示
	 * @param form
	 * @param model
	 * @return
	 */
	@GetMapping("/search")
	public String greetingForm(@ModelAttribute SearchNameForm form, Model model) {
		return "users/search/index";
	}

	/**
	 * 検索結果表示
	 * @param form
	 * @param model
	 * @return
	 */
	@PostMapping("/search")
	public String greetingSubmit(@ModelAttribute SearchNameForm form, Model model) {
		List<User> list = service.findByNameContaining(form.getName());
		model.addAttribute("list", list);
		return "users/search/result";
	}
}
