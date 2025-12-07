package com.jutjoy.domain.service.news;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jutjoy.domain.entity.news.News;
import com.jutjoy.domain.form.news.NewsEditForm;
import com.jutjoy.domain.repository.NewsRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional
@Service
public class NewsEditService {

    @Autowired
    private NewsRepository newsRepository;

    private final String FILE_PATH = "/upload_file/news";

    public void edit(int id, NewsEditForm form) {

        MultipartFile image = form.getImage();

        News entity = newsRepository.findById(id).get();
        String beforeImageName = entity.getImageName();

        // ニュース更新処理
        News news = editNews(entity, form);

        try {

            String dirPath = FILE_PATH + File.separator + news.getId();
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

    public News findNews(int id) {

        // ニュース、編集履歴参照
        News news = newsRepository.findById(id).get();
        return news;
    }

    private void deleteFile(String imageName, File uploadDir) {

        if (!Objects.isNull(imageName) && uploadDir.exists()) {
            File imageFullPath = new File(uploadDir.getPath() + File.separator + imageName);
            imageFullPath.delete();
        }
    }

    private News editNews(News entity, NewsEditForm form) {

        entity.setTitle(form.getTitle());
        entity.setContent(form.getContent());
        if (!Objects.isNull(form.getImage())) {
            entity.setImageName(form.getImage().getOriginalFilename());
        } else if (form.isImageRemove()) {
            entity.setImageName(null);
        }
        return newsRepository.save(entity);
    }

}