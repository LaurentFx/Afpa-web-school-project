package com.afpa.cda.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.CommandeRepository;
import com.afpa.cda.dao.PanierRepository;
import com.afpa.cda.dao.UserRepository;
import com.afpa.cda.dto.CommandeDto;
import com.afpa.cda.dto.ManifestationDto;
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
	private PanierRepository panierRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;


	@Override
	public List<CommandeDto> findAll() {

		return this.commandeRepository.findAll()
				.stream()
				.map(c-> {
					CommandeDto commandeDto = new CommandeDto ();
					commandeDto.setId(c.getId());
					commandeDto.setQuantite(c.getQuantite());

					ManifestationDto manifDto = new ManifestationDto();
					manifDto.setId(c.getManifestation().getId());
					manifDto.setLabel(c.getManifestation().getLabel());
					commandeDto.setManifestation(manifDto);

					PanierDto panierDto = new PanierDto();
					panierDto.setId(c.getPanier().getId());
					panierDto.setTotal(c.getPanier().getTotal());
					commandeDto.setPanier(panierDto);

					return commandeDto;
				})
				.collect(Collectors.toList());

		//		return this.commandeRepository.findAll()
		//				.stream()
		//				.map(m-> this.modelMapper.map(m,CommandeDto.class))
		//				.collect(Collectors.toList());


		//				.stream()
		//				.map(c->  CommandeDto.builder()
		//						.id(c.getId())
		//						.manifestation(ManifestationDto.builder()
		//								.id(c.getManifestation().getId())
		//								.label(c.getManifestation().getLabel())
		//								.build())
		//						.quantite(c.getQuantite())
		//						.build())
		//				.collect(Collectors.toList());
		//		return null;

	}	

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

		//	Optional <Panier> panierOp = this.panierRepository.findById(id);
		//	System.out.println("test methode");
		//	PanierDto panierDto = new PanierDto ();
		//	List<CommandeDto> list = new ArrayList<CommandeDto>();
		//		if (panierOp.isPresent()) {
		//			panierDto = modelMapper.map(panierOp.get(),PanierDto.class);
		//			panierDto.setListCommandes(list);
		//			
		//			for (Commande c : panierOp.get().getListCommandes()) {
		//				panierDto.getListCommandes()
		//				.add(CommandeDto
		//						.builder()
		//						.manifestation(ManifestationDto.builder()
		//								.id(c.getManifestation().getId())
		//								.label(c.getManifestation().getLabel())
		//								.build())
		//						.build());
		//			}
		//		}
		//		System.out.println(list.toString());
		//		return list;
	}

	@Override
	public void add(CommandeDto commandeDto) {

		//		Commande commande = new Commande ();
		//		commande.setQuantite(commandeDto.getQuantite());
		//
		//		Manifestation manifestation = new Manifestation ();
		//manifestation.setLabel(commandeDto.getManifestation().);
		//manifestation = modelMapper.map(commandeDto.)
		//		commande.se
		this.commandeRepository.save(this.modelMapper.map(commandeDto, Commande.class));
		//				commandeDto.setId(commande.getId());
		//		return null;
		//		return commandeDto;
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
	public void delete(int id) {
		Optional<Commande> commandeOp =	this.commandeRepository.findById(id);
		Panier panier = commandeOp.get().getPanier();
		PanierDto panierDto = modelMapper.map(panier,PanierDto.class);
		
		if(this.commandeRepository.existsById(id)) {
			this.commandeRepository.deleteById(id);
			panierDto.setTotal(0);
			panierRepository.save(this.modelMapper.map(panierDto,Panier.class));
		}
		
	}

	@Override
	public boolean update(CommandeDto commande, int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
