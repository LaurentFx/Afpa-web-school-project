package com.afpa.cda.entity;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

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
public class Personne {	
	@Id
	private int id;
	private String nom;
	private String prenom;
	private String organisation;
	private String qualification;
	private Date dateAnimation;
	private String typeAnimation;
	private double salaire;
	private int nbreSpectateurs;

}
