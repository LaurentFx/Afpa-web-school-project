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

import com.afpa.cda.dto.InvitationDto;
import com.afpa.cda.service.IInvitationService;

@RestController
public class InvitationController {

	@Autowired
	private IInvitationService invitationService;

	@GetMapping(path="/invitation")
	public List<InvitationDto> getAll(){
		return this.invitationService.findAll();
	}

	@GetMapping(path="/invitation/{id}")
	public InvitationDto getOne(@PathVariable int id) {
		return this.invitationService.findById(id);
	}

	@GetMapping(path="/invitation/user/{id}")
	public List<InvitationDto> getAllByVip(@PathVariable int id) {
		return this.invitationService.findInvitationByUserId(id);
	}
	
	@GetMapping(path="/invitation/new/{id}")
	public List<InvitationDto> getNewByVip(@PathVariable int id) {
		return this.invitationService.findNewInvitationByUserId(id);
	}

	@GetMapping(path="/invitation/manifestation/{id}")
	public List<InvitationDto> getAllByManifestation(@PathVariable int id) {
		return this.invitationService.findInvitationByManifestationId(id);
	}
		
	@PostMapping(path="/invitation")
	public boolean add(@RequestBody InvitationDto invitationDto) {
		return this.invitationService.add(invitationDto);
	}

	@PutMapping(path="/invitation/{id}")
	public boolean update(@RequestBody InvitationDto invitationDto ,@PathVariable int id) {
		return this.invitationService.update(invitationDto, id);
	}
	
	@PutMapping(path="/invitation/reponse/{id}")
	public boolean updateReponse(@RequestBody String reponse ,@PathVariable int id) {
		return this.invitationService.updateReponse(reponse, id);
	}

	@DeleteMapping(path="/invitation/{id}")
	public boolean delete(@PathVariable int id) {
		return this.invitationService.delete(id);
	}

	@DeleteMapping(path="/invitation/manifestation/{id}")
	public boolean deleteAll(@PathVariable int id) {
		return this.invitationService.deleteAll(id);
	}


}