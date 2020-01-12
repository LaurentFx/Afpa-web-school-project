package com.afpa.cda.entity;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	@GeneratedValue(generator = "PERSONNE_SEQ", strategy = GenerationType.SEQUENCE)
	private int id;
	private String nom;
	private String prenom;
	
	@Column(nullable = true)
	private String organisation;
	
	@Column(nullable = true)
	private String qualification;
	
	@Column(nullable = true)
	private double salaire;
	
	@ManyToMany
	@JoinTable(name = "PERSONNE_ROLE",joinColumns = @JoinColumn(name = "PERSON_ID"),
	inverseJoinColumns= @JoinColumn(name = "ROLE_ID"))

	List<Role> roles;
	


}
