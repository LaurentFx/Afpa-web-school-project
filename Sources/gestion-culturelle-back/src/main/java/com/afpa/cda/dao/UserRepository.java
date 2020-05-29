package com.afpa.cda.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.afpa.cda.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findAll();

	Optional<User> findUserByNom(String nom);

	Optional<User> findUserByPrenom(String prenom);

	@Query(value = "SELECT * FROM t_user u WHERE u.role_id = :id",  nativeQuery = true)
	List <User> findUserByRole(Integer id);

	@Query(value = "SELECT * FROM t_user u WHERE u.nom = :name and u.prenom = :fname",  nativeQuery = true)
	Optional<User> findUserByNomAndPrenom(String name, String fname);


}
