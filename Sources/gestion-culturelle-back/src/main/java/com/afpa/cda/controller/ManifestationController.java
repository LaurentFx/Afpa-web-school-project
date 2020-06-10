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

import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.service.IManifestationService;


@RestController
public class ManifestationController {

	@Autowired
	private IManifestationService manifestationService;

	@GetMapping(path = "/public/manifestation/list")
	public List<ManifestationDto> getAll(){
		return this.manifestationService.findAll();
	}

	@PreAuthorize("hasAnyAuthority('RESP','ADMIN')")
	@PostMapping(path = "/manifestation/availability")
	public boolean getAvailability(@RequestBody ManifestationDto manifestationDto) {
		return this.manifestationService.findAvailability(manifestationDto);
	}
	
	@PreAuthorize("hasAnyAuthority('RESP','ADMIN','ANIM','VIP','CLIENT')")
	@GetMapping(path = "/manifestation/show/{id}")
	public ManifestationDto getOne(@PathVariable int id){
		return this.manifestationService.findById(id);
	}
	
	@PreAuthorize("hasAnyAuthority('RESP')")
	@PostMapping(path = "/manifestation/add")
	public int add(@RequestBody ManifestationDto manifestationDto) {
		return this.manifestationService.add(manifestationDto);
	}

	@PreAuthorize("hasAnyAuthority('RESP','ADMIN')")
	@PutMapping(path = "/manifestation/update/{id}")
	public boolean update(@RequestBody ManifestationDto manif, @PathVariable int id) {
		return this.manifestationService.update(manif,id);
	}

	@PreAuthorize("hasAnyAuthority('RESP','ADMIN')")
	@DeleteMapping(path = "/manifestation/delete/{id}")
	public boolean delete(@PathVariable int id) {
	return this.manifestationService.delete(id);
	}

}



