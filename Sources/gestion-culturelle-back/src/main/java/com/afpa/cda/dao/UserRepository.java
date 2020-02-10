package com.afpa.cda.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.afpa.cda.dto.UserDto;
import com.afpa.cda.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findAll();

	Optional<User> findByNom(String nom);

	//	@Query("SELECT * FROM t_user u WHERE u.role = :roleStr", 
	//	  nativeQuery = true))
	//List<UserDto> findByRole(Integer roleStr);

	Optional<User> findByPrenom(String prenom);


	@Query(value = "SELECT * FROM User u WHERE u.role = :id",  nativeQuery = true)
	List <UserDto> findByRoleId(int id);




}
