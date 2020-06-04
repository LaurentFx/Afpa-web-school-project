package com.afpa.cda.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
		name="t_manifestation"
		)
public class Manifestation {

	@Id
	@GeneratedValue(generator = "MANIFESTATION_SEQ", strategy = GenerationType.SEQUENCE)
	private int id;

	private String label;
	@Temporal(TemporalType.DATE)
	private Date dateValidation;
	@ManyToOne
	@JoinColumn(name = "validateur_id")
	private User validateur;

	@OneToOne
	private Animation animation;	

	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	@Temporal(TemporalType.DATE)
	private Date dateFin;

	@Column(precision=6,scale=2)
	private double cout;

	@ManyToOne
	@JoinColumn(name = "salle_id")
	private Salle salle;

	@Column(precision=6,scale=2)
	private double prixBillet;	
	private int reservations;
	private int reservationsVip;


}
