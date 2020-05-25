package com.afpa.cda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.afpa.cda.dto.ReservationDto;
import com.afpa.cda.service.IReservationService;

@RestController
public class ReservationController {

	@Autowired
	private IReservationService reservationService;

	@GetMapping(path="/reservation")
	public List<ReservationDto> getAll(){
		return this.reservationService.findAll();
	}

	@GetMapping(path="/reservation/{id}")
	public ReservationDto getOne(@PathVariable int id) {
		return this.reservationService.findById(id);
	}

	@GetMapping(path="/reservation/user/{id}")
	public List<ReservationDto> getAllByVip(@PathVariable int id) {
		return this.reservationService.findReservationByUserId(id);
	}

	@GetMapping(path="/reservation/manifestation/{id}")
	public List<ReservationDto> getAllByManifestation(@PathVariable int id) {
		return this.reservationService.findReservationByManifestationId(id);
	}

	@PostMapping(path="/reservation")
	public boolean add(@RequestBody ReservationDto reservationDto) {
		return this.reservationService.add(reservationDto);
	}

	@PutMapping(path="/reservation/{id}")
	public void update(@RequestBody ReservationDto reservationDto ,@PathVariable int id) {
		this.reservationService.update(reservationDto, id);
	}
	

	@DeleteMapping(path="/reservation/{id}")
	public void delete(@PathVariable int id) {
		this.reservationService.delete(id);
	}


}