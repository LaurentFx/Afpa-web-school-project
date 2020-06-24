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

import com.afpa.cda.dto.InvitationDto;
import com.afpa.cda.service.IInvitationService;

@RestController
public class InvitationController {

	@Autowired
	private IInvitationService invitationService;

	@PreAuthorize("hasAnyAuthority('RESP','ADMIN')")
	@GetMapping(path="/invitation/list")
	public List<InvitationDto> getAll(){
		return this.invitationService.findAll();
	}

	@PreAuthorize("hasAnyAuthority('VIP')")
	@GetMapping(path="/invitation/show/{id}")
	public InvitationDto getOne(@PathVariable int id) {
		return this.invitationService.findById(id);
	}

	@PreAuthorize("hasAnyAuthority('VIP')")
	@GetMapping(path="/invitation/user/{id}")
	public List<InvitationDto> getAllByVip(@PathVariable int id) {
		return this.invitationService.findInvitationByUserId(id);
	}
	
	@PreAuthorize("hasAnyAuthority('VIP')")
	@GetMapping(path="/invitation/new/{id}")
	public List<InvitationDto> getNewByVip(@PathVariable int id) {
		return this.invitationService.findNewInvitationByUserId(id);
	}

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@GetMapping(path="/invitation/manifestation/{id}")
	public List<InvitationDto> getAllByManifestation(@PathVariable int id) {
		return this.invitationService.findInvitationByManifestationId(id);
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@PostMapping(path="/invitation/add")
	public int add(@RequestBody InvitationDto invitationDto) {
		return this.invitationService.add(invitationDto);
	}

	@PreAuthorize("hasAnyAuthority('RESP','ADMIN')")
	@PutMapping(path="/invitation/update/{id}")
	public boolean update(@RequestBody InvitationDto invitationDto ,@PathVariable int id) {
		return this.invitationService.update(invitationDto, id);
	}
	
	@PreAuthorize("hasAnyAuthority('VIP')")
	@PutMapping(path="/invitation/answer/{id}")
	public boolean updateReponse(@RequestBody String reponse ,@PathVariable int id) {
		return this.invitationService.updateReponse(reponse, id);
	}

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@DeleteMapping(path="/invitation/delete/{id}")
	public boolean delete(@PathVariable int id) {
		return this.invitationService.delete(id);
	}

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@DeleteMapping(path="/invitation/deleteAll/{id}")
	public boolean deleteAll(@PathVariable int id) {
		return this.invitationService.deleteAll(id);
	}


}