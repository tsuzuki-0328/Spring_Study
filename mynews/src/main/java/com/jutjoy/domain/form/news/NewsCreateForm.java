package com.jutjoy.domain.form.news;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class NewsCreateForm {

    @NotEmpty(message = "＊タイトルは必ず入力してください。")
    @Size(max = 20, message = "＊タイトルは20文字以内で設定してください。")
    private String title;

    @NotEmpty(message = "＊本文は必ず入力してください。")
    private String content;

    private MultipartFile image;
}
