package com.afpa.cda.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.CommandeRepository;
import com.afpa.cda.dao.UserRepository;
import com.afpa.cda.dto.CommandeDto;
import com.afpa.cda.dto.PanierDto;
import com.afpa.cda.entity.Commande;
import com.afpa.cda.entity.Panier;
import com.afpa.cda.entity.User;
import com.afpa.cda.service.ICommandeService;

@Service
public class CommandeServiceImpl implements ICommandeService {

	@Autowired
	private CommandeRepository commandeRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public List<CommandeDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandeDto add(CommandeDto commandeDto) {
		Commande commande = this.commandeRepository.save(this.modelMapper.map(commandeDto, Commande.class));
		commandeDto.setId(commande.getId());
			
		return commandeDto;
	}
	
	@Override
	public PanierDto findByUser(int id) {
		Optional <User> userOp=this.userRepository.findById(id);
		PanierDto panierDto = new PanierDto ();
		if (userOp.isPresent()) {
			Panier panier = userOp.get().getPanier();
		panierDto = modelMapper.map(panier,PanierDto.class);
		}
		
		return panierDto;
	}
	@Override
	public CommandeDto findById(Integer commandeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandeDto findOne(Integer commandeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(CommandeDto commande, int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
