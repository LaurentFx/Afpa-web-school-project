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
public class ManifestationDto {
	private int id;
	private String label;
	private AnimationDto animation;
	private Date date;	
	private double cout;
	private SalleDto salle;
	private double prixBillet;
	private int reservations;
	private int reservationsVip;
	private double rentabilite;
	

}
