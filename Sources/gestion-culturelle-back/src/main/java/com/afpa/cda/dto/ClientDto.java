package com.afpa.cda.dto;

import javax.persistence.Id;

public class ClientDto {
	
	@Id
	private int id;
	private String nom;
	private String prenom;	

}
