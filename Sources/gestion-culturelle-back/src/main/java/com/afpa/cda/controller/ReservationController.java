package com.afpa.cda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@GetMapping(path="/reservation/list")
	public List<ReservationDto> getAll(){
		return this.reservationService.findAll();
	}

	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@GetMapping(path="/reservation/show/{id}")
	public ReservationDto getOne(@PathVariable int id) {
		return this.reservationService.findById(id);
	}

	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@GetMapping(path="/reservation/user/{id}")
	public List<ReservationDto> getAllByVip(@PathVariable int id) {
		return this.reservationService.findReservationByUserId(id);
	}

	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@GetMapping(path="/reservation/manifestation/{id}")
	public List<ReservationDto> getAllByManifestation(@PathVariable int id) {
		return this.reservationService.findReservationByManifestationId(id);
	}

	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@PostMapping(path="/reservation/add")
	public int add(@RequestBody ReservationDto reservationDto) {
		return this.reservationService.add(reservationDto);
	}

	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@PutMapping(path="/reservation/update/{id}")
	public boolean update(@RequestBody ReservationDto reservationDto ,@PathVariable int id) {
		return this.reservationService.update(reservationDto, id);
	}
	
	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@DeleteMapping(path="/reservation/delete/{id}")
	public boolean delete(@PathVariable int id) {
		return this.reservationService.delete(id);
	}


}