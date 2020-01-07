package com.afpa.cda.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

import com.afpa.cda.entity.TypeSalle;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalleDto {
	private int id;
	private int nbrePlaces;
	private int nbrePlacesVIP;
	private double fraisjournalier;
	private TypeSalle typesalle;
}
