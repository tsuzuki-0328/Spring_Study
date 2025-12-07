package com.jutjoy.domain.service.news;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import com.jutjoy.domain.repository.NewsRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NewsDeleteService {

    @Autowired
    private NewsRepository newsRepository;

    private final String FILE_PATH = "/upload_file/news";

    public void delete(Integer id) {

        // ニュース削除処理
        newsRepository.deleteById(id);

        // フォルダ・画像削除
        String dirPath = FILE_PATH + File.separator + id;
        File uploadDir = new File(dirPath);
        if (uploadDir.exists()) {
            // フォルダ削除
            FileSystemUtils.deleteRecursively(uploadDir);
        }
    }
}