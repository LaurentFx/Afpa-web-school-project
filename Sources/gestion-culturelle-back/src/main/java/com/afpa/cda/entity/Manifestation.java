package com.afpa.cda.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class Manifestation {

	@Id
	@GeneratedValue(generator = "MANIFESTATION_SEQ", strategy = GenerationType.SEQUENCE)
	private int id;
	
	private String label;

	@OneToOne
	private Animation animation;	
	
	private Date date;
	private double cout;
	
	@ManyToOne
	private Salle salle;

	private double prixBillet;	
	private int reservations;
	private int reservationsVip;
	private double rentabilite;
	
	@ManyToMany
	private List <Panier> paniers;
		

}
