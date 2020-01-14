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

import com.afpa.cda.dto.PanierDto;
import com.afpa.cda.dto.RoleDto;
import com.afpa.cda.service.IPanierService;

@RestController
public class PanierController {

	@Autowired
	private IPanierService panierService;

	@GetMapping(path = "/panier")
	public List<PanierDto> getAll(){
		return this.panierService.findAll();
	}

	@GetMapping(path = "/panier/{id}")
	public PanierDto getOne(@PathVariable int id){
		return this.panierService.findById(id);
	}
	@PostMapping(path = "/panier")
	public PanierDto add(@RequestBody PanierDto panier) {
		return this.panierService.add(panier);
	}

	@PutMapping(path = "/panier/{id}")
	public void update(@RequestBody PanierDto panier, @PathVariable int id) {
		this.panierService.updatePanier(panier, id);
	}

	@DeleteMapping(path = "/panier/{id}")
	public void delete(@PathVariable int id) {
		this.panierService.deletePanier(id);
	}

}



