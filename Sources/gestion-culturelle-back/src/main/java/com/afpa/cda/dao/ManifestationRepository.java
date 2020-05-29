package com.afpa.cda.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.afpa.cda.entity.Manifestation;


@Repository
public interface ManifestationRepository extends CrudRepository<Manifestation, Integer> {

	List<Manifestation> findAll();
	
	@Query(value = "SELECT * FROM t_manifestation m WHERE m.salle_id= :id", 
			  nativeQuery = true)
	public List<Manifestation> findManifestationBySalle(Integer id);
	
	@Query(value = "SELECT * FROM t_manifestation m WHERE m.animation_id= :id", 
			  nativeQuery = true)
	public List<Manifestation> findManifestationByAnimation(Integer id);
	
	Optional<Manifestation> findManifestationByLabel(String label);
	
}
