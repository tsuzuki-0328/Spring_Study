package com.jutjoy.domain.service.news;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jutjoy.common.CommonConstant;
import com.jutjoy.domain.entity.news.ProfileNews;
import com.jutjoy.domain.form.news.ProfielsCreateForm;
import com.jutjoy.domain.repository.ProfileRepository;

@Service
public class ProfileCreateService {
	
    @Autowired
    private ProfileRepository profileRepository;

    public void create(ProfielsCreateForm form) {

        MultipartFile image = form.getImage();

        // ニュース登録
        ProfileNews profile = createProfile(form);

        // 画像保存
        if (!image.getOriginalFilename().isEmpty()) {

            // フォルダ作成
            String dirPath = CommonConstant.FILE_PATH + File.separator + profile.getId();
            File uploadDir = new File(dirPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            try {
                // ファイル作成
                String fullPath = uploadDir.getPath() + File.separator + image.getOriginalFilename();
                File imageFullPath = new File(fullPath);
                try (FileOutputStream fileOutputStream = new FileOutputStream(imageFullPath);
                        BufferedOutputStream uploadFileStream = new BufferedOutputStream(fileOutputStream)) {

                    byte[] bytes = image.getBytes();
                    uploadFileStream.write(bytes);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ProfileNews createProfile(ProfielsCreateForm form) {

        ProfileNews entity = new ProfileNews();
        entity.setName(form.getName());
        entity.setGender(form.getGender());
        entity.setHobby(form.getHobby());
        entity.setIntroduction(form.getIntroduction());
        entity.setImageName(Objects.isNull(form.getImage()) ? null : form.getImage().getOriginalFilename());

        return profileRepository.save(entity);
    }

}
