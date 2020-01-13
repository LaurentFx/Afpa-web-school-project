package com.afpa.cda.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	private String adresse;
	private String nom_Entreprise;


//	@OneToOne
//	@Column(nullable = true)
//	private Panier panier_client;

	@Column(nullable = true)
	private int num_Client;

	//	@OneToMany
//	@Column(nullable = true)	
//	private Animation animation;	

//	@ManyToMany
//	@JoinTable(name = "PERSONNE_ROLE",joinColumns = @JoinColumn(name = "PERSON_ID"),
//	inverseJoinColumns= @JoinColumn(name = "ROLE_ID"))
//	Set<Role> roles;

	//	@OneToMany
	//(mappedBy = "client_Panier")
	//private Set<Panier> paniers;


}
