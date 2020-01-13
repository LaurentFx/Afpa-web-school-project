package com.afpa.cda.service;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.PanierRepository;
import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.dto.PanierDto;
@Service
public class PanierServiceImpl implements IPanierService {
    @Autowired
    private PanierRepository panierRepository;
    @Autowired
    private ModelMapper modelMapper;
	@Override
	public List<PanierDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ManifestationDto add(PanierDto panier) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean updateManifestation(PanierDto panier, int id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteManifestation(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public PanierDto findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

    
    
}