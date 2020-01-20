package com.afpa.cda.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Personne {	

	@Id
	@GeneratedValue(generator = "PERSONNE_SEQ", strategy = GenerationType.SEQUENCE)
	private int id;

	private String nom;
	private String prenom;
	private String email;
	private String login;
	private String password;
	private String tokenSecret;
	private String adresse;

	@ManyToOne
	private Role role;

	// Pour un client
	@OneToOne
	private Panier panier;
	private int numClient;

	// Pour un animateur
	@OneToMany
	private List <Animation> animations;
	
	// Pour un animateur et un VIP
	private String entreprise;



}
