package com.jutjoy.domain.service.news;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jutjoy.domain.entity.news.News;
import com.jutjoy.domain.repository.NewsRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NewsListService {

    @Autowired
    private NewsRepository newsRepository;

    public List<News> list(String title) {

        List<News> newsList = new ArrayList<>();

        if (Objects.isNull(title) || title.isEmpty()) {
            // 一覧取得
            newsList = newsRepository.findAllByOrderById();
        } else {
            // 検索
            newsList = newsRepository.findByTitleLike(createLikeParam(title));
        }

        return newsList;
    }

    private String createLikeParam(String param) {
        return "%" + param + "%";
    }
}