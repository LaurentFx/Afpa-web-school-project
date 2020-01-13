package com.afpa.cda.dto;
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

public class SalleDto {

	private int id;
	private String label;
	private int capacite;
	private int placesVip;
	private double fraisJournalier;
	private TypeSalleDto typeSalle;


}
