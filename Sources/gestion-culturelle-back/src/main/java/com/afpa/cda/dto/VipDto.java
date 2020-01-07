package com.afpa.cda.dto;

import javax.persistence.Id;

public class VipDto {
	
	@Id
	private int id;
	private String nom;
	private String prenom;	
	private String organisation;

}
