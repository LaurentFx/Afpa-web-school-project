package com.afpa.cda.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.afpa.cda.entity.Animation;


@Repository
public interface AnimationRepository extends CrudRepository<Animation, Integer> {

	List<Animation> findAll();
	
}
