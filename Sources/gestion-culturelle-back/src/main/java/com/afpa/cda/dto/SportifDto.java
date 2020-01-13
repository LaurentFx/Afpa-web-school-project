package com.afpa.cda.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SportifDto  {
	
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private String login;
	private String password;
	private String adresse;
	private RoleDto role;
	private PanierDto panier;
	private String nom_Entreprise_Sportif;
	private AnimationDto animation_Sportif;

}
