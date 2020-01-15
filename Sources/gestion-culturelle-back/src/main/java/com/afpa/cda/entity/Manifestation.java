package com.afpa.cda.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	@JoinColumn(name = "salle")
	private Salle salle;
	

	private double prixBillet;	
	private int reservations;
	private int reservationsVip;
	private double rentabilite;

	@ManyToMany
	@JoinTable(name = "Manifestation_Panier",
			joinColumns = { @JoinColumn(name = "id_manifestation") },
			inverseJoinColumns = { @JoinColumn(name = "id_panier") })
	private List <Panier> paniers;


}
