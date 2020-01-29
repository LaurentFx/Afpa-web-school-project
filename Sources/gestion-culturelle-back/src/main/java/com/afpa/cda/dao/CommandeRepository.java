package com.afpa.cda.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.afpa.cda.entity.Commande;

@Repository
public interface CommandeRepository  extends CrudRepository<Commande, Integer> {

	List <Commande> findAll();
	
	@Modifying
	@Query(nativeQuery = true, value = "delete from t_panier_list_commandes where panier=:id")
	public void clearListCommandes(int id);
}


