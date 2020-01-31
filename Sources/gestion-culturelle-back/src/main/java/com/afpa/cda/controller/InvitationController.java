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
import com.afpa.cda.dto.UserDto;
import com.afpa.cda.service.IInvitationService;

@RestController
public class InvitationController {
	
	@Autowired
	private IInvitationService invitationService;
	
	@GetMapping(path="/invitation")
	public List<UserDto> getAll(){
		return this.invitationService.findAll();
	}
	
	@GetMapping(path="/invitation/{id}")
	public List<UserDto> getOne(@PathVariable int id) {
		return this.invitationService.findByRole(id);
	}
	
	@GetMapping(path="/invitation/list/{id}")
	public List<UserDto> getListVips(@PathVariable int id) {
		return this.invitationService.findAllVipsByManifestation(id);
	}
	
	@PostMapping(path="/invitation")
	public UserDto add(@RequestBody UserDto invitation) {
		return this.invitationService.add(invitation);
	}
	
	@PutMapping(path="/invitation/add/{id}")
	public void updateAdd(@RequestBody ManifestationDto manifestation ,@PathVariable int id) {
		this.invitationService.updateAdd(manifestation, id);
	}
	
	@PutMapping(path="/invitation/sub/{id}")
	public void updateSub(@RequestBody ManifestationDto manifestation ,@PathVariable int id) {
		this.invitationService.updateSub(manifestation, id);
	}
	
	@DeleteMapping(path="/invitation/{id}")
	public void delete(@PathVariable int id) {
		this.invitationService.delete(id);
	}
	

}