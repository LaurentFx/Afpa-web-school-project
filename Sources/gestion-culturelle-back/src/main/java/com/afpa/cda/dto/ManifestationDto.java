package com.afpa.cda.dto;

import java.util.Date;

import javax.persistence.Id;

import com.afpa.cda.entity.Salle;
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
	
	@Id
	private String nom;
	private Date dateManifestation;
	private Salle salle;
	private double prixBillet;
	
	

}
