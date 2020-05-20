package com.afpa.cda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.afpa.cda.dto.CommandeDto;
import com.afpa.cda.service.ICommandeService;

@RestController
public class CommandeController {

	@Autowired
	private ICommandeService commandeService;
	
	@GetMapping(path = "/commande/panier/{id}")
	public List<CommandeDto> getCommandes(@PathVariable int id){
		return this.commandeService.findByPanierId(id);
	}

	@DeleteMapping(path = "/commande/{id}")
	public void delete(@PathVariable int id) {
		this.commandeService.delete(id);
	}

}