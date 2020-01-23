package com.afpa.cda.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.afpa.cda.entity.Commande;

@Repository
public interface CommandeRepository  extends CrudRepository<Commande, Integer> {

	List <Commande> findAll();
	
}


