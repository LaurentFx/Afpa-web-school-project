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
	private double salaire;
	private int nbreSpectateurs;
	

}
