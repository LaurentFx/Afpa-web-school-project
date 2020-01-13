package com.afpa.cda.dto;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClientDto {
	@Id
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private String login;
	private String password;
	private String adresse;
	private RoleDto roleClientDto;
	private PanierDto panierClientDto;
	private int num_Client;

}
