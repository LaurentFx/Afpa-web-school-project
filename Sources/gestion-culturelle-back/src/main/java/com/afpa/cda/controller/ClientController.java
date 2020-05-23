package com.afpa.cda.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afpa.cda.dto.ClientDto;
import com.afpa.cda.service.IClientService;


@RestController
public class ClientController {
	
		@Autowired
		private IClientService clientService;
		
		@GetMapping(path = "/public/inscription")
		public List<ClientDto> getAll(){
			return this.clientService.findAll();
		}
		
		/*@GetMapping(path = "/users/{id}")
		public UserDto getOne(@PathVariable int id){
			return this.clientService.findOne(id);
		}*/
		
//		@PostMapping(path = "/public/inscription")
//		public ClientDto add(@RequestBody ClientDto client, HttpServletResponse resp) throws IOException {
//			if(client.getRole() == null) {
//				resp.sendError(HttpStatus.BAD_REQUEST.value(),"le role est obligatoire à la création de la personne");
//				return null;
//			} else if(client.getNom().equalsIgnoreCase(adminUserDefaultConf.getNom()) 
//					|| client.getPrenom().equalsIgnoreCase(adminUserDefaultConf.getPrenom())) {
//				resp.sendError(HttpStatus.NOT_ACCEPTABLE.value(),"prenom/nom 'admin' sont déjà pris");
//				return null;
//			} else {
//				return this.clientService.add(client);
//			}
//		}
		
		/*@GetMapping("/users/current")
		public UserDto getCurrentUser(Principal currentUser) {
			Integer userId = Integer.valueOf((String)((UsernamePasswordAuthenticationToken)currentUser).getPrincipal());
			return this.userService.findById(userId).get();*/
			
		}


