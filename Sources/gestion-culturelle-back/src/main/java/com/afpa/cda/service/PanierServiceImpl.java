package com.afpa.cda.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.ManifestationRepository;
import com.afpa.cda.dao.PanierRepository;
import com.afpa.cda.dto.PanierDto;
import com.afpa.cda.entity.Manifestation;
import com.afpa.cda.entity.Panier;
@Service
public class PanierServiceImpl implements IPanierService {
    @Autowired
    private PanierRepository panierRepository;
    @Autowired
    private ManifestationRepository manifestationRepository;
    @Autowired
    private ModelMapper modelMapper;
	@Override
	public List<PanierDto> findAll() {
		
		return this.panierRepository.findAll()
				.stream()
				.map(p-> this.modelMapper.map(p,PanierDto.class))
				.collect(Collectors.toList());
	}
	@Override
	public PanierDto add(PanierDto panier) {
		Panier panE = this.modelMapper.map(panier,Panier.class);
		Manifestation manifE = this.manifestationRepository.findAll(panier.getManifestations().);
		Panier entityPan = this.panierRepository.save(panE);
		panier.setId(entityPan.getId());
		return panier;
	}
	@Override
	public boolean updatePanier(PanierDto panier, int id) {
		Optional<Panier> panUp = this.panierRepository.findById(id);
		if (panUp.isPresent()) {
			Panier pr= panUp.get();
			pr.setNbreBillets(panier.getNbreBillets());
			pr.setNumClient(panier.getNumClient());
			this.panierRepository.save(pr);
			return true;
		}
		return false;
	}
	@Override
	public boolean deletePanier(int id) {
		if (this.panierRepository.existsById(id)) {
			this.panierRepository.deleteById(id);
			return true;
		}
		return false;
	}
	@Override
	public PanierDto findById(int id) {
		Optional<Panier> panE =  this.panierRepository.findById(id);
		PanierDto panDto = null;
		if (panE.isPresent()) {
			Panier pn =panE.get();
			panDto = this.modelMapper.map(pn,PanierDto.class);
		}
		return panDto;
	}

    
    
}