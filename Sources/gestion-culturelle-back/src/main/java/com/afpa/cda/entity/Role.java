package com.afpa.cda.entity;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Role {
	
	@Id
	@GeneratedValue(generator = "ROLE_SEQ", strategy = GenerationType.SEQUENCE)
	private int id;	
	private String label;
	
	@OneToMany (mappedBy = "role")
	private List <Personne> personnes;

}
