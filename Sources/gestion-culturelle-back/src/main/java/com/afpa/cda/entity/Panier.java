package com.afpa.cda.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
public class Panier {
	@Id
	@GeneratedValue(generator = "PANIER_SEQ", strategy = GenerationType.SEQUENCE)
	private int id;
	
	private int numClient;
		
	@ManyToMany
	@JoinTable(name = "Manifestation_Panier",
	joinColumns = { @JoinColumn(name = "id_panier") },
	inverseJoinColumns = { @JoinColumn(name = "id_manifestation") })
	private List <Manifestation> manifestations;
	
	private int nbreBillets;

}
