package com.afpa.cda.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class ArtisteDto {
	
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private String login;
	private String password;
	private String adresse;
	private RoleDto roleArtisteDto;
	private PanierDto panierArtisteDto;
	private String nom_Entreprise_Artiste;
	private AnimationDto animation_ArtisteDto;

}
