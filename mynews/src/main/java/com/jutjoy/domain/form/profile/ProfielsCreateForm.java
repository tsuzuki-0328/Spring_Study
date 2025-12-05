package com.jutjoy.domain.form.profile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProfielsCreateForm {
	
	@NotEmpty(message = "名前は必ず入力してください")
	@Size(max = 20,message = "20字以内です")
	private String name;
	
	@NotEmpty(message = "性別必ず入力してください")
	@Size(max = 20)
	private String gender;
	
	@NotEmpty(message = "趣味を入力してください")
	@Size(max = 150,message = "趣味欄は50字以内です")
	private String hobby;
	
	@NotEmpty(message = "自己紹介を入力してください")
	@Size(max = 500,message = "500字以内です")
	private String introduction;
	
	private MultipartFile image;

}
