package com.afpa.cda.dto;

import java.util.List;

import com.afpa.cda.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AdminDto {
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private String login;
	private String password;
	private String adresse;
	private List <RoleDto> roles;
	
	
}
