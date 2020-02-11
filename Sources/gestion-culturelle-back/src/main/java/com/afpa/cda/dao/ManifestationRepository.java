package com.afpa.cda.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.afpa.cda.entity.Animation;
import com.afpa.cda.entity.Manifestation;
import com.afpa.cda.entity.TypeSalle;


@Repository
public interface ManifestationRepository extends CrudRepository<Manifestation, Integer> {

	List<Manifestation> findAll();
	
	@Query(value = "SELECT * FROM t_manifestation m WHERE m.salle= :id", 
			  nativeQuery = true)
	public List<Manifestation> findManifestationBySalleId(Integer id);
	
	@Query(value = "SELECT * FROM t_manifestation m WHERE m.animation_id= :id", 
			  nativeQuery = true)
	public List<Manifestation> findManifestationByAnimationId(Integer id);
	
	Optional<Manifestation> findByLabel(String label);
	
	
}
