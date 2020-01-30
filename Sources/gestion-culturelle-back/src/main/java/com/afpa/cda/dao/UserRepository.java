package com.afpa.cda.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afpa.cda.dto.UserDto;
import com.afpa.cda.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//	List<UserDto> findAll();

	Optional<User> findByNom(String nom);

	//	@Query("SELECT * FROM t_user u WHERE u.role = :roleStr")
	List<UserDto> findByRole(Integer roleStr);

	Optional<User> findByPrenom(String prenom);

	List <UserDto> findByRoleId(int id);


}
