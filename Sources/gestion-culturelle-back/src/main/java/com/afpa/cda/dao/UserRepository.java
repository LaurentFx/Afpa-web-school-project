package com.afpa.cda.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.afpa.cda.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	List<User> findAll();

	Optional<User> findByNom(String nom);
}
