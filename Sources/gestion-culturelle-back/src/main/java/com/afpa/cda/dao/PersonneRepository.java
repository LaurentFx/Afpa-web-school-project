package com.afpa.cda.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.afpa.cda.entity.Personne;

@Repository
public interface PersonneRepository extends CrudRepository<Personne, Integer> {
	List<Personne> findAll();
	
	Optional<Personne> findByNom(String nom);
}
