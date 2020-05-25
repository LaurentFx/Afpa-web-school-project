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

import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.service.IManifestationService;


@RestController
public class ManifestationController {

	@Autowired
	private IManifestationService manifesationService;

	@GetMapping(path = "/public/manifestation")
	public List<ManifestationDto> getAll(){
		return this.manifesationService.findAll();
	}

	@PostMapping(path = "/manifestation/availability")
	public boolean getAvailability(@RequestBody ManifestationDto manifestationDto) {
		return this.manifesationService.findAvailability(manifestationDto);
	}
	
	@GetMapping(path = "/manifestation/{id}")
	public ManifestationDto getOne(@PathVariable int id){
		return this.manifesationService.findById(id);
	}
	
	//@PreAuthorize("hasAnyAuthority('RESP','ADMIN')")
	@PostMapping(path = "/manifestation")
	public ManifestationDto add(@RequestBody ManifestationDto manifestationDto) {
		return this.manifesationService.add(manifestationDto);
	}

	@PutMapping(path = "/manifestation/{id}")
	public void update(@RequestBody ManifestationDto manif, @PathVariable int id) {
		this.manifesationService.updateManifestation(manif,id);
	}

	@DeleteMapping(path = "/manifestation/{id}")
	public void delete(@PathVariable int id) {
		this.manifesationService.deleteManifestation(id);
	}

}



