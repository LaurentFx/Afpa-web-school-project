package com.afpa.cda.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	@Column(nullable = true)
	private String organisation;
	
	@Column(nullable = true)
	private String qualification;
	
	@Column(nullable = true)
	private double salaire;
	
	//private Role role;

}
