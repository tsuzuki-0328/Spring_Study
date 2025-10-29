package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.model.UserForm;
import com.example.demo.service.UsersRegisterService;

@Controller
public class RegisterController {
	
	@Autowired
    private UsersRegisterService usersRegisterService;

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

    
        // ② 重複チェック（サービス内 isValid）
        boolean hasError = usersRegisterService.isValid(userForm, result);
        if (hasError) {
            return "form"; // メール重複時はフォーム画面に戻る
        }

        // ③ 問題なければ登録処理へ
        usersRegisterService.register(userForm);

        // ④ 登録完了画面へ遷移
        return "confirm"; // or "success"
  }
}
