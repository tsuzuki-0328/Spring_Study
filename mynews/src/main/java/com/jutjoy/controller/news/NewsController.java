package com.jutjoy.controller.news;

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

import com.jutjoy.domain.entity.news.News;
import com.jutjoy.domain.form.news.NewsCreateForm;
import com.jutjoy.domain.form.news.NewsEditForm;
import com.jutjoy.domain.service.news.NewsCreateService;
import com.jutjoy.domain.service.news.NewsDeleteService;
import com.jutjoy.domain.service.news.NewsEditService;
import com.jutjoy.domain.service.news.NewsListService;


@Controller
public class NewsController {
	
    @Autowired
    private NewsCreateService newsCreateService;
    
    @Autowired
    private NewsListService newsListService;
    
    @Autowired
    private NewsEditService newsEditService;
    
    @Autowired
    private NewsDeleteService newsDeleteService;

    @GetMapping("/news/create")
    public String create(@ModelAttribute("form") NewsCreateForm newsCreateForm) {
        return "news/create";
    }
    
    @PostMapping("/news/create")
    public String create(@Validated @ModelAttribute("form") NewsCreateForm newsCreateForm,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "news/create";
        }

        newsCreateService.create(newsCreateForm);

        return "redirect:/news/create/complete";
    }
    
    @GetMapping("/news/{action}/complete")
    public String complete(@PathVariable(name = "action") String action, Model model) {
    	
        return "news/complete";
    }
    
    @GetMapping("/news/list")
    public String list(@RequestParam(name = "title", required = false) String title, Model model) {

        List<News> newsList = newsListService.list(title);
        model.addAttribute("newsList", newsList);

        return "news/list";
    }
    
    @GetMapping("/news/edit/{id}")
    public String edit(@ModelAttribute("form") NewsEditForm newsEditForm,
            @PathVariable(name = "id") int id, Model model) {

        News news = newsEditService.findNews(id);
        model.addAttribute("news", news);

        return "news/edit";
    }

    @PostMapping("/news/edit/{id}")
    public String edit(@PathVariable(name = "id") int id,
            @Validated @ModelAttribute("form") NewsEditForm newsEditForm, BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return edit(newsEditForm, id, model);
        }
        newsEditService.edit(id, newsEditForm);

        return "redirect:/news/edit/complete";
    }
    
    @PostMapping("/news/delete")
    public String delete(@RequestParam(name = "id", required = true) int id, Model model) {
        newsDeleteService.delete(id);
        return "redirect:/news/list";
    }
    
}