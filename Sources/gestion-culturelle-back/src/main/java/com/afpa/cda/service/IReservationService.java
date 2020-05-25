package com.afpa.cda.service;

import java.util.List;

import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.dto.ReservationDto;


public interface IReservationService {

	List<ReservationDto> findAll();

	boolean add(ReservationDto reservationDto);

	ReservationDto findById(int id);

	boolean delete(int id);

	List<ReservationDto> findReservationByManifestationId(int id);
	
	List<ReservationDto> findReservationByUserId(int id);
	
	void addManifestationReservation (ManifestationDto manifestationDto);

	boolean update(ReservationDto reservationDto, int id);

	
}
