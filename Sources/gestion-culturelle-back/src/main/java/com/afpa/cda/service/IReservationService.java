package com.afpa.cda.service;

import java.util.List;

import com.afpa.cda.dto.ReservationDto;


public interface IReservationService {

	List<ReservationDto> findAll();

	boolean add(ReservationDto reservationDto);

	ReservationDto findById(int id);

	boolean delete(int id);

	List<ReservationDto> findReservationByManifestationId(int id);
	
	List<ReservationDto> findReservationByUserId(int id);

	boolean update(ReservationDto reservationDto, int id);

	
}
