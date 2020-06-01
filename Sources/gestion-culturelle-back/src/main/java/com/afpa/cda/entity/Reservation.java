package com.afpa.cda.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
		name="t_reservation"
)
public class Reservation {

	@Id
	@GeneratedValue(generator = "RESERVATION_SEQ", strategy = GenerationType.SEQUENCE)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "manifestation_id", nullable = false)
	Manifestation manifestation;
	
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	User client;
	
	String numClient;
	int quantite;
	
	Date dateReservation;
	
}
