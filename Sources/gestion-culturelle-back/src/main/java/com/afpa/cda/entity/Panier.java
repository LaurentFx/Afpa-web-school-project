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
import javax.persistence.Table;

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
@Table(
		name="t_panier"
)
public class Panier {
	@Id
	@GeneratedValue(generator = "PANIER_SEQ", strategy = GenerationType.SEQUENCE)
	private int id;
	
	private Date dateValidation;
	private int numClient;
		
	@ManyToMany
	@JoinTable(name = "t_manifestation_panier",
	joinColumns = { @JoinColumn(name = "id_panier") },
	inverseJoinColumns = { @JoinColumn(name = "id_manifestation") })
	private List<Manifestation> manifestations;
	
	private int nbreBillets;
	private double total;

}
