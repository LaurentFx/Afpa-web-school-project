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

import com.afpa.cda.dto.ArticleDto;
import com.afpa.cda.dto.PanierDto;
import com.afpa.cda.service.IPanierService;

@RestController
public class PanierController {

	@Autowired
	private IPanierService panierService;

	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@GetMapping(path = "/panier/list")
	public List<PanierDto> getAll(){
		return this.panierService.findAll();
	}

	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@GetMapping(path = "/panier/show/{id}")
	public PanierDto getOne(@PathVariable int id){
		return this.panierService.findById(id);
	}
	
	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@GetMapping(path = "/panier/user/{id}")
	public PanierDto getUser(@PathVariable int id){
		return this.panierService.findByUser(id);
	}
	
	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@PostMapping(path = "/panier/add")
	public boolean add(@RequestBody ArticleDto articleDto) {
		return this.panierService.addArticlePanier(articleDto);
	}

	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@PutMapping(path = "/panier/update/{id}")
	public boolean update(@RequestBody PanierDto panier, @PathVariable int id) {
		return this.panierService.updatePanier(panier, id);
	}

	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@DeleteMapping(path = "/panier/delete/{id}")
	public boolean deletePanier(@PathVariable int id) {
		return this.panierService.deletePanier(id);
	}

	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@DeleteMapping(path = "/panier/deleteArticles/{id}")
	public boolean deleteArticles(@PathVariable int id) {
		return this.panierService.deleteArticles(id);
	}
	
	
}



