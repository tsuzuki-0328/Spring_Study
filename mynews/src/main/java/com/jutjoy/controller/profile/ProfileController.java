package com.jutjoy.controller.profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jutjoy.domain.entity.profile.ProfileNews;
import com.jutjoy.domain.form.profile.ProfielsCreateForm;
import com.jutjoy.domain.form.profile.ProfileEditForm;
import com.jutjoy.domain.service.profile.ProfileCreateService;
import com.jutjoy.domain.service.profile.ProfileDeleteService;
import com.jutjoy.domain.service.profile.ProfileEditService;
import com.jutjoy.domain.service.profile.ProfileListService;

@Controller
public class ProfileController {
	
	@Autowired
	private ProfileCreateService profileCreateService;
	
    @Autowired
    private ProfileListService profileListService;
    
    @Autowired
    private ProfileEditService profileEditService;
    
    @Autowired
    private ProfileDeleteService profileDeleteService;
	
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
    
    @GetMapping("/profile/edit/{id}")
    public String edit(@ModelAttribute("form") ProfileEditForm profileEditForm,
            @PathVariable(name = "id") int id, Model model) {

        ProfileNews profile = profileEditService.findProfile(id);
        model.addAttribute("profile",profile);

        return "profile/edit";
    }

    @PostMapping("/profile/edit/{id}")
    public String edit(@PathVariable(name = "id") int id,
            @Validated @ModelAttribute("form") ProfileEditForm profileEditForm, BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return edit(profileEditForm, id, model);
        }
        profileEditService.edit(id, profileEditForm);

        return "redirect:/profile/edit/complete";
    }
    
    @PostMapping("/profile/delete")
    public String delete(@RequestParam(name = "id", required = true) int id, Model model) {
        profileDeleteService.delete(id);
        return "redirect:/profile/list";
    }

}
