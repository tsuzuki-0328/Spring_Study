package com.jutjoy.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jutjoy.domain.entity.news.ProfileNews;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileNews,Integer>{

}
