package com.afpa.cda.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationDto {

	private int id;
	
	private ManifestationDto manifestation;
	private UserDto client;
	
	String numClient;
	int quantite;
	Date dateReservation;
	
	double total;
	
}
