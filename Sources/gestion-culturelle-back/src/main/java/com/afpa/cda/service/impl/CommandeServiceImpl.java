package com.afpa.cda.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.CommandeRepository;
import com.afpa.cda.dao.ManifestationRepository;
import com.afpa.cda.dao.PanierRepository;
import com.afpa.cda.dto.CommandeDto;
import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.dto.PanierDto;
import com.afpa.cda.entity.Commande;
import com.afpa.cda.entity.Manifestation;
import com.afpa.cda.entity.Panier;
import com.afpa.cda.service.ICommandeService;

@Service
public class CommandeServiceImpl implements ICommandeService {

	@Autowired
	private CommandeRepository commandeRepository;
	@Autowired
	private PanierRepository panierRepository;
	@Autowired
	private ManifestationRepository manifestationRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<CommandeDto> findByPanierId(int id) {
		List<Commande> listCommandes = this.commandeRepository.findAll();

		List <CommandeDto> listByPanier = new ArrayList<CommandeDto>();

		for (Commande commande : listCommandes) {
			if (commande.getPanier().getId() == id) {
				CommandeDto commandeDto = new CommandeDto ();
				commandeDto.setId(commande.getId());

				ManifestationDto manifestationDto = new ManifestationDto();
				manifestationDto.setId(commande.getManifestation().getId());
				manifestationDto.setLabel(commande.getManifestation().getLabel());
				manifestationDto.setPrixBillet(commande.getManifestation().getPrixBillet());
				commandeDto.setManifestation(manifestationDto);

				PanierDto panierDto = new PanierDto();	
				panierDto.setId(commande.getManifestation().getId());
				commandeDto.setPanier(panierDto);

				commandeDto.setQuantite(commande.getQuantite());

				listByPanier.add(commandeDto);
			}

		}
		return listByPanier;

	}

	@Override
	public void delete(int id) {
		Optional<Commande> commandeOp =	this.commandeRepository.findById(id);
		if(this.commandeRepository.existsById(id)) {

			Panier panier = commandeOp.get().getPanier();
			PanierDto panierDto = modelMapper.map(panier,PanierDto.class);

			Manifestation manifestation = commandeOp.get().getManifestation();
			ManifestationDto manifestationDto = modelMapper.map(manifestation,ManifestationDto.class);

			manifestationDto.setReservations(manifestationDto.getReservations()+commandeOp.get().getQuantite());
			panierDto.setTotal(panierDto.getTotal()-(manifestationDto.getPrixBillet()*commandeOp.get().getQuantite()));

			this.manifestationRepository.save(this.modelMapper.map(manifestationDto,Manifestation.class));
			this.panierRepository.save(this.modelMapper.map(panierDto,Panier.class));

			this.commandeRepository.deleteById(id);

		}

	}

}
