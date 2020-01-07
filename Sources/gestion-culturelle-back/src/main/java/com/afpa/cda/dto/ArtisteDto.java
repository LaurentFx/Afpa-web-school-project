package com.afpa.cda.dto;

import javax.persistence.Id;

public class ArtisteDto {
	
	@Id
	private int id;
	private String nom;
	private String prenom;
	private double salaire;
	private int nbreSpectateurs;
	

}
