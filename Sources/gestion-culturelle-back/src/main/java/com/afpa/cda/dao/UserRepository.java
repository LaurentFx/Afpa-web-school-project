package com.afpa.cda.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.afpa.cda.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	List<User> findAll();

	Optional<User> findByNom(String nom);
	
	@Query(value = "SELECT * FROM User u WHERE u.role = :roleStr", 
			  nativeQuery = true)
	Optional<User> findByRole(Integer roleStr);
	
	
}
