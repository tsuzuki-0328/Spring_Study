package com.jutjoy.controller.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jutjoy.domain.form.news.ProfielsCreateForm;
import com.jutjoy.domain.service.news.ProfileCreateService;

import ch.qos.logback.core.model.Model;

@Controller
public class ProfileController {
	
	@Autowired
	private ProfileCreateService profileCreateService;
	
	@GetMapping("/profile/create")
	public String create(@ModelAttribute("form") ProfielsCreateForm profielsCreateForm) {
		return "profile/create";
	}
	
	@PostMapping("/profile/create")
	public String create(@Validated @ModelAttribute("form") ProfielsCreateForm profielsCreateForm,
			BindingResult result,Model model) {
		
		if(result.hasErrors()) {
			 return "profile/create";
		}
	 
		profileCreateService.create(profielsCreateForm);
		
		return "redirect:/profile/create/complete";
	}
	
	@GetMapping("/profile/create/complete")
	public String complete() {
		return "profile/complete";
	}

}
