package com.afpa.cda.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
		name="t_user",
		uniqueConstraints={@UniqueConstraint(columnNames={"nom","prenom"})}
		)
public class User {
	@Id
	@GeneratedValue(generator = "USER_SEQ", strategy = GenerationType.SEQUENCE)
	private int id;
	private String nom;
	private String prenom;
	private String password;
	private String email;
	private String tokenSecret;

	@ManyToOne
	private Role role;

	private String adresse;

	// Pour un client
	@OneToOne
	private Panier panier;
	private String numClient;

	// Pour un animateur
	@OneToMany
	private List <Animation> animations;

	// Pour un animateur et un VIP
	private String entreprise;


}
