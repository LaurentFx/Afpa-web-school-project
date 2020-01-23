package com.afpa.cda.service;

import java.util.List;
import java.util.Optional;

import com.afpa.cda.dto.CommandeDto;

public interface ICommandeService {
	
	List<CommandeDto> findAll();

	CommandeDto add(CommandeDto commande);

	CommandeDto findById(Integer commandeId);

	CommandeDto findOne(Integer commandeId);

	boolean delete(int id);

	boolean update(CommandeDto commande, int id);

}
