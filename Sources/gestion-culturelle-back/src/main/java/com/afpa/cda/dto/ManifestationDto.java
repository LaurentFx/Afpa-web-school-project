package com.afpa.cda.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManifestationDto {
	private int id;
	private String nom;
	private Date dateManifestation;	
	private String typeManifestation;
	private double prixBillet;
	private SalleDto salle_ManifestationDto;
	private int reservations;
	private int reservationsVip;
	private double rentabilité;
	

}
