package com.afpa.cda.dto;
import java.util.Date;
import javax.persistence.Id;

public class ManagerDto {
	
	@Id
	private int id;
	private String nom;
	private String prenom;
	private Date dateAnimation;
	private String typeAnimation;	

}
