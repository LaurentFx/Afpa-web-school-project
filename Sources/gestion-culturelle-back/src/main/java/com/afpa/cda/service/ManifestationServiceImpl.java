package com.afpa.cda.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.afpa.cda.dao.ManifestationRepository;
import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.entity.Manifestation;





@Service
public class ManifestationServiceImpl implements IManifestationService {

	@Autowired
	private ManifestationRepository manifestationRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ManifestationDto> findAll() {
		return this.manifestationRepository.findAll()
				.stream()
				.map(ts-> this.modelMapper.map(ts,ManifestationDto.class))
				.collect(Collectors.toList());	
	}


	@Override
	public ManifestationDto findById(int id) {
		Optional <Manifestation> manifOp = this.manifestationRepository.findById(id);
		ManifestationDto manif =null; 
		if(manifOp.isPresent()) {
			Manifestation man= manifOp.get();

			manif=this.modelMapper.map(man,ManifestationDto.class);
		}
		return manif;
	}

	@Override
	public ManifestationDto add(ManifestationDto mani) {
		Manifestation manif = this.manifestationRepository.save(this.modelMapper.map(mani,Manifestation.class));
		mani.setNom(manif.getNom());
		mani.setPrixBillet(manif.getPrixBillet());
		mani.setDateManifestation(manif.getDateManifestation());
		System.err.println("manifestation ajoutée");
		return mani;
	}

	@Override
	public boolean updateManifestation(ManifestationDto manif, int id) {

		Optional<Manifestation> manifOp = this.manifestationRepository.findById(id);
		if(manifOp.isPresent()) {
			Manifestation mani = manifOp.get();
			mani.setNom(manif.getNom());
			mani.setDateManifestation(manif.getDateManifestation());
			mani.setPrixBillet(manif.getPrixBillet());
			System.err.println("manifestation mise à jour");
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteManifestation(int id) {

		if(this.manifestationRepository.existsById(id)) {
			this.manifestationRepository.deleteById(id);
			System.err.println("manifestation supprimée");
			return true;
		}
		return false;
	}
}
