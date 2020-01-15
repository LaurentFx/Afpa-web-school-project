package com.afpa.cda.dto;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AnimateurDto  {
	
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private String login;
	private String password;
	private String adresse;
	private RoleDto role;
	
	private String entreprise;
private List <AnimationDto> animations;
	
}