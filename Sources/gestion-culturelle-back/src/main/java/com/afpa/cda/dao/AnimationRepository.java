package com.afpa.cda.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.afpa.cda.entity.Animation;


@Repository
public interface AnimationRepository extends CrudRepository<Animation, Integer> {

	List<Animation> findAll();

	Optional<Animation> findAnimationByLabel(String label);
	
	@Query(value = "SELECT * FROM t_animation a WHERE a.animateur_id= :id", 
			  nativeQuery = true)
	public List<Animation> findAnimationByUser(Integer id);
	
}
