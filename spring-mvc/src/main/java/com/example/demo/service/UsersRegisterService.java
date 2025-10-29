package com.example.demo.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.demo.domain.model.UserForm;
import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UsersRegisterService {

    @Autowired
    private UsersRepository usersRepository;
    
 // 🔸 入力チェック（E-Mail重複確認）
    public boolean isValid(UserForm userForm, BindingResult bindingResult) {

        // すでに登録されているメールアドレスならエラーとする
        if (usersRepository.existsByEmail(userForm.getEmail())) {

            // フィールドエラーを追加
            FieldError error = new FieldError(
                    "userForm",                   // 対象オブジェクト名（@ModelAttribute名）
                    "email",                      // エラー対象フィールド名
                    "既に登録されているE-Mailです。"); // 表示するメッセージ

            bindingResult.addError(error);
            return true; // true = エラーあり
        }

        return false; // false = エラーなし
    }

    public void register(final UserForm userForm) {
        Users entity = new Users();
        entity.setName(userForm.getName());
        entity.setEmail(userForm.getEmail());
        entity.setAge(userForm.getAge());
        entity.setNote(userForm.getNote());
     // 新規登録の場合、registeredDateをセット
        entity.setRegisteredDate(OffsetDateTime.now());
        // 更新日時もセット
        entity.setUpdatedDate(OffsetDateTime.now());
        usersRepository.save(entity);
    }
}