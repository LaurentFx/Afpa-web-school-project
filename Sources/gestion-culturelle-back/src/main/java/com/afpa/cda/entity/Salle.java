package com.afpa.cda.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Salle {
	
	@Id
	@GeneratedValue(generator = "SALLE_SEQ", strategy = GenerationType.SEQUENCE)
	private int id;
	private String label;
	private int placesReservees;
	private int placesReserveesVIP;
	private double fraisjournalier;

	//private TypeSalle typesalle;


}
