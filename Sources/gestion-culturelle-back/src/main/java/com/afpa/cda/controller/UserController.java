package com.afpa.cda.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.afpa.cda.constant.AdminUserDefaultConf;
import com.afpa.cda.dto.UserDto;
import com.afpa.cda.service.IUserService;

@RestController
public class UserController {

	@Autowired
	private IUserService userService;


	@Autowired
	private AdminUserDefaultConf adminUserDefaultConf;

	@PreAuthorize("hasAnyAuthority('RESP')")
	@GetMapping(path = "/users/list")
	public List<UserDto> getAll(){
		return this.userService.findAll();
	}

	@PreAuthorize("hasAnyAuthority('RESP','ADMIN','ANIM','VIP','CLIENT')")
	@GetMapping(path = "/users/show/{id}")
	public UserDto getOne(@PathVariable int id){
		return this.userService.findById(id);
	}

	@PreAuthorize("hasAnyAuthority('RESP','ADMIN','ANIM','VIP','CLIENT')")
	@GetMapping(path = "/users/role/{id}")
	public List<UserDto> findByRole(@PathVariable Integer id){
		return this.userService.findByRole(id);
	}
	
	@PreAuthorize("hasAnyAuthority('RESP')")
	@PostMapping(path = "/users/add")
	public int add(@RequestBody UserDto user, HttpServletResponse resp) throws IOException {
		if(user.getRole() == null) {
			resp.sendError(HttpStatus.BAD_REQUEST.value(),"le role est obligatoire à la création de la personne");
			return 0;
		} else if(user.getNom().equalsIgnoreCase(adminUserDefaultConf.getNom()) 
				|| user.getPrenom().equalsIgnoreCase(adminUserDefaultConf.getPrenom())) {
			resp.sendError(HttpStatus.NOT_ACCEPTABLE.value(),"prenom/nom 'resp1' sont déjà pris");
			return 0;
		} else {
			return this.userService.add(user);

		}
	}
	
	@PostMapping(path = "/users/new")
	public boolean addClient(@RequestBody UserDto user, HttpServletResponse resp) throws IOException {
		if(user.getNom().equalsIgnoreCase(adminUserDefaultConf.getNom()) 
				|| user.getPrenom().equalsIgnoreCase(adminUserDefaultConf.getPrenom())) {
			resp.sendError(HttpStatus.NOT_ACCEPTABLE.value(),"prenom/nom 'admin' sont déjà pris");
			return true;
		} else {
			return this.userService.addClient(user);
		}
	}

	
	// Pas utilisé
	@PreAuthorize("hasAnyAuthority('RESP','ADMIN','ANIM','VIP','CLIENT')")
	@GetMapping("/users/current")
	public UserDto getCurrentUser(Principal currentUser) {
		Integer userId = Integer.valueOf((String)((UsernamePasswordAuthenticationToken)currentUser).getPrincipal());
		return this.userService.findByCurrentUser(userId).get();

	}

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@GetMapping(path="/users/invites/{id}")
	public List<UserDto> getInvites(@PathVariable int id) {
		return this.userService.findVipsToInvite(id);
	}
	
	@PreAuthorize("hasAnyAuthority('RESP','ADMIN','ANIM','VIP','CLIENT')")
	@PutMapping(path = "/users/update/{id}")
	public boolean update(@RequestBody UserDto user,@PathVariable int id ) {
		return this.userService.update(user, id);
	}

	@PreAuthorize("hasAnyAuthority('RESP')")
	@DeleteMapping(path = "/users/delete/{id}")
	public boolean delete(@PathVariable int id) {
		return this.userService.delete(id);
	}



}
