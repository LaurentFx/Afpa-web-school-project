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

import com.afpa.cda.dto.UserDto;
import com.afpa.cda.dto.VipDto;
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
	public UserDto getOne(@PathVariable int id) {
		return this.invitationService.findById(id);
	}
	
	@PostMapping(path="/invitation")
	public UserDto add(@RequestBody UserDto invitation) {
		return this.invitationService.add(invitation);
	}
	
	@PutMapping(path="/invitation/{id}")
	public void update(@RequestBody UserDto invitation ,@PathVariable int id) {
		this.invitationService.update(invitation, id);
	}
	
	@DeleteMapping(path="/invitation/{id}")
	public void delete(@PathVariable int id) {
		this.invitationService.delete(id);
	}
	

}