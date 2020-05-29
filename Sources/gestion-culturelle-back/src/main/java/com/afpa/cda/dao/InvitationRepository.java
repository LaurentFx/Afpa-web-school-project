package com.afpa.cda.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.afpa.cda.entity.Invitation;

@Repository
public interface InvitationRepository  extends CrudRepository<Invitation, Integer> {
	
	List <Invitation> findAll();
	
	@Query(value = "SELECT * FROM t_invitation i WHERE i.vip_id= :id", 
			  nativeQuery = true)
	public List<Invitation> findInvitationByUser(Integer id);
	
	@Query(value = "SELECT * FROM t_invitation i WHERE i.manifestation_id= :id", 
			  nativeQuery = true)
	public List<Invitation> findInvitationByManifestation(Integer id);

	@Query(value = "SELECT * FROM t_invitation i WHERE i.vip_id= :userId and i.manifestation_id= :manifestationId",
			nativeQuery = true)
	Optional<Invitation> findInvitationByUserAndManifestation(Integer userId, Integer manifestationId);
	
	
}


