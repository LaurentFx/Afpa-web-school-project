package com.afpa.cda.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping(path = "/users")
	public List<UserDto> getAll(){
		return this.userService.findAll();
	}
	
	@PostMapping(path = "/users")
	public UserDto add(@RequestBody UserDto user, HttpServletResponse resp) throws IOException {
		if(user.getRole() == null) {
			resp.sendError(HttpStatus.BAD_REQUEST.value(),"le role est obligatoire à la création du personne");
			return null;
		} else if(user.getNom().equalsIgnoreCase(adminUserDefaultConf.getNom()) 
				|| user.getPrenom().equalsIgnoreCase(adminUserDefaultConf.getPrenom())) {
			resp.sendError(HttpStatus.NOT_ACCEPTABLE.value(),"prenom/nom 'admin' sont déjà pris");
			return null;
		} else {
			return this.userService.add(user);
		}
	}
	
	@GetMapping("/users/current")
	public UserDto getCurrentUser(Principal currentUser) {
		Integer userId = Integer.valueOf((String)((UsernamePasswordAuthenticationToken)currentUser).getPrincipal());
		return this.userService.findById(userId).get();
	}
	
}
