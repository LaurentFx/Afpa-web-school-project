package com.afpa.cda.entity;

import javax.persistence.Column;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Panier {
	@Id
	@GeneratedValue(generator = "PANIER_SEQ", strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(nullable = false)	
	private int num_Client;
	
	
	@Column(nullable = false)	
	private Manifestation manifestation;
	
	@Column(nullable = true)	
	private int nbre_Billets;
	

}
