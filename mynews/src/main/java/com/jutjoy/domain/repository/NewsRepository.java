package com.jutjoy.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jutjoy.domain.entity.news.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
}