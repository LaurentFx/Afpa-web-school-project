package com.afpa.cda.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.afpa.cda.entity.Reservation;

@Repository
public interface ReservationRepository  extends CrudRepository<Reservation, Integer> {

	List <Reservation> findAll();
	
	@Query(value = "SELECT * FROM t_reservation r WHERE r.client_id= :id", 
			  nativeQuery = true)
	public List<Reservation> findReservationByUserId(Integer id);
	
	@Query(value = "SELECT * FROM t_reservation r WHERE r.manifestation_id= :id", 
			  nativeQuery = true)
	public List<Reservation> findReservationByManifestationId(Integer id);
	
	@Query(value = "SELECT * FROM t_reservation r WHERE r.client_id= :userId and r.manifestation_id= :manifestationId",
			nativeQuery = true)
	Optional<Reservation> findByUserAndManifestation(Integer userId, Integer manifestationId);

}
