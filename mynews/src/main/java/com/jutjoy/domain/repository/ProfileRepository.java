package com.jutjoy.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jutjoy.domain.entity.profile.ProfileNews;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileNews,Integer>{
	public List<ProfileNews> findAllByOrderById();
	public List<ProfileNews> findByNameLike(String name);
}
