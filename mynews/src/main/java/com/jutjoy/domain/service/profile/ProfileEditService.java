	package com.jutjoy.domain.service.profile;

	import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jutjoy.domain.entity.profile.ProfileNews;
import com.jutjoy.domain.form.profile.ProfileEditForm;
import com.jutjoy.domain.repository.ProfileRepository;

import lombok.AllArgsConstructor;

	@AllArgsConstructor
	@Transactional
	@Service
	public class ProfileEditService {

	    @Autowired
	    private ProfileRepository profileRepository;

	    private final String FILE_PATH = "/upload_file/profile";

	    public void edit(int id, ProfileEditForm form) {

	        MultipartFile image = form.getImage();

	        ProfileNews entity = profileRepository.findById(id).get();
	        String beforeImageName = entity.getImageName();

	        // ニュース更新処理
	        ProfileNews profile = editProfile(entity, form);

	        try {

	            String dirPath = FILE_PATH + File.separator + profile.getId();
	            File uploadDir = new File(dirPath);

	            // 画像削除チェック有りの場合、ファイルを削除
	            if (form.isImageRemove()) {
	                deleteFile(beforeImageName, uploadDir);
	            }

	            // 画像変更/保存
	            if (!image.getOriginalFilename().isEmpty()) {

	                // 既存のファイルを削除
	                deleteFile(beforeImageName, uploadDir);

	                // フォルダ作成
	                if (!uploadDir.exists()) {
	                    uploadDir.mkdirs();
	                }

	                String fullPath = uploadDir.getPath() + File.separator + image.getOriginalFilename();
	                File afterImageFullPath = new File(fullPath);
	                try (FileOutputStream fileOutputStream = new FileOutputStream(afterImageFullPath);
	                        BufferedOutputStream uploadFileStream = new BufferedOutputStream(fileOutputStream)) {

	                    // 画像保存
	                    byte[] bytes = image.getBytes();
	                    uploadFileStream.write(bytes);
	                }
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public ProfileNews findProfile(int id) {

	        // ニュース、編集履歴参照
	        ProfileNews profile = profileRepository.findById(id).get();
	        return profile;
	    }

	    private void deleteFile(String imageName, File uploadDir) {

	        if (!Objects.isNull(imageName) && uploadDir.exists()) {
	            File imageFullPath = new File(uploadDir.getPath() + File.separator + imageName);
	            imageFullPath.delete();
	        }
	    }

	    private ProfileNews editProfile(ProfileNews entity, ProfileEditForm form) {

	        entity.setName(form.getName());
	        entity.setGender(form.getGender());
	        entity.setHobby(form.getHobby());
	        entity.setIntroduction(form.getIntroduction());
	        if (!Objects.isNull(form.getImage())) {
	            entity.setImageName(form.getImage().getOriginalFilename());
	        } else if (form.isImageRemove()) {
	            entity.setImageName(null);
	        }
	        return profileRepository.save(entity);
	    }

	}