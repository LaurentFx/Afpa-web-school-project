package com.afpa.cda.service;

import java.util.List;
import java.util.Optional;

import com.afpa.cda.dto.CommandeDto;
import com.afpa.cda.dto.PanierDto;

public interface ICommandeService {

	List<CommandeDto> findByPanierId(int id);

	void delete(int id);

}
