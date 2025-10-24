package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.model.UserForm;

@Controller
public class RegisterController {

    @GetMapping("/form")
    private String readForm(@ModelAttribute UserForm userForm) {
        return "form";
    }

    @PostMapping("/form")
    private String confirm(@Validated(UserForm.Groups.class) @ModelAttribute UserForm userForm, BindingResult result, Model model) {

        if (result.hasErrors()) {
            // エラーがある場合、form.htmlに戻る
            return "form";
        }

        return "confirm";
    }
}