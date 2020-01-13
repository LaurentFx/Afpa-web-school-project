package com.afpa.cda.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	private String label_Manifestation;
	
	@Column(nullable = true)
	private Animation animation;	
	private Date dateManifestation;
	private double charges;
	private String typeManifestation;
	private double prixBillet;	
	private int reservations;
	private int reservationsVip;
	private double rentabilité;
	
	@ManyToOne
	@JoinColumn(name = "salle")
	private Salle salle;
	
	

}
