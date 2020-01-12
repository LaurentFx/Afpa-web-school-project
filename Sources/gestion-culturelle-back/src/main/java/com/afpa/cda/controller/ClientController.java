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

import com.afpa.cda.dto.ClientDto;


import com.afpa.cda.service.IClientService;


@RestController
public class ClientController {

	@Autowired
	private IClientService clientService;

	@GetMapping(path = "/client")
	public List<ClientDto> getAll(){
		return this.clientService.findAll();
	}

	@GetMapping(path = "/client/{id}")
	public ClientDto getOne(@PathVariable int id){
		return this.clientService.findById(id);
	}
	@PostMapping(path = "/client")
	public ClientDto add(@RequestBody ClientDto client) {
		return this.clientService.add(client);
	}

	@PutMapping(path = "/client/{id}")
	public void update(@RequestBody ClientDto client, @PathVariable int id) {
		this.clientService.updateClient(client,id);
	}

	@DeleteMapping(path = "/client/{id}")
	public void delete(@PathVariable int id) {
		this.clientService.deleteClient(id);
	}

}



