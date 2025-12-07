package com.jutjoy.domain.service.profile;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import com.jutjoy.domain.repository.ProfileRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProfileDeleteService {

    @Autowired
    private ProfileRepository profileRepository;

    private final String FILE_PATH = "/upload_file/profile";

    public void delete(Integer id) {

        // プロフィール削除処理
        profileRepository.deleteById(id);

        // フォルダ・画像削除
        String dirPath = FILE_PATH + File.separator + id;
        File uploadDir = new File(dirPath);
        if (uploadDir.exists()) {
            // フォルダ削除
            FileSystemUtils.deleteRecursively(uploadDir);
        }
    }
}