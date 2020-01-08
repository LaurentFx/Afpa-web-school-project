package com.afpa.cda.entity;

import java.util.Date;
import javax.persistence.Entity;
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

public class Manifestation {
	
	@Id
	private int id;
	private String nom;
	private Date dateManifestation;
	private String typeManifestation;
	private int capacite;	
	//private Salle salle;
	private double prixBillet;

}
