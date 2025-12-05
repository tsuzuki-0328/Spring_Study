package com.jutjoy.controller.profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jutjoy.domain.entity.profile.ProfileNews;
import com.jutjoy.domain.form.profile.ProfielsCreateForm;
import com.jutjoy.domain.service.profile.ProfileCreateService;
import com.jutjoy.domain.service.profile.ProfileListService;

@Controller
public class ProfileController {
	
	@Autowired
	private ProfileCreateService profileCreateService;
	
    @Autowired
    private ProfileListService profileListService;
	
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
	
    @GetMapping("/profile/list")
    public String list(@RequestParam(name = "name",required = false) String name,Model model) {

        List<ProfileNews> profileList = profileListService.list(name);
        model.addAttribute("profileList", profileList);

        return "profile/profileList";
    }

}
