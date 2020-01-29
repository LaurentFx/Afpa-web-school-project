package com.afpa.cda.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.afpa.cda.entity.Commande;

@Repository
public interface CommandeRepository  extends CrudRepository<Commande, Integer> {

	List <Commande> findAll();
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "delete from t_commande where panier=:id")
	public void clearListCommandes(int id);
}


