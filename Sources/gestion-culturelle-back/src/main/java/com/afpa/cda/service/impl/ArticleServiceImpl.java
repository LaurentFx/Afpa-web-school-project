package com.afpa.cda.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.ArticleRepository;
import com.afpa.cda.dao.ManifestationRepository;
import com.afpa.cda.dao.PanierRepository;
import com.afpa.cda.dto.ArticleDto;
import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.dto.PanierDto;
import com.afpa.cda.entity.Article;
import com.afpa.cda.entity.Manifestation;
import com.afpa.cda.entity.Panier;
import com.afpa.cda.service.IArticleService;

@Service
public class ArticleServiceImpl implements IArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private PanierRepository panierRepository;
	@Autowired
	private ManifestationRepository manifestationRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ArticleDto> findByPanierId(int id) {
		List<Article> listArticles = this.articleRepository.findAll();

		List <ArticleDto> listByPanier = new ArrayList<ArticleDto>();

		for (Article article : listArticles) {
			if (article.getPanier().getId() == id) {
				ArticleDto articleDto = new ArticleDto ();
				articleDto.setId(article.getId());

				ManifestationDto manifestationDto = new ManifestationDto();
				manifestationDto.setId(article.getManifestation().getId());
				manifestationDto.setLabel(article.getManifestation().getLabel());
				manifestationDto.setPrixBillet(article.getManifestation().getPrixBillet());
				articleDto.setManifestation(manifestationDto);

				PanierDto panierDto = new PanierDto();	
				panierDto.setId(article.getManifestation().getId());
				articleDto.setPanier(panierDto);

				articleDto.setQuantite(article.getQuantite());

				listByPanier.add(articleDto);
			}

		}
		return listByPanier;

	}

	@Override
	public boolean delete(int id) {
		Optional<Article> articleOp =	this.articleRepository.findById(id);
		if(this.articleRepository.existsById(id)) {

			Panier panier = articleOp.get().getPanier();
			PanierDto panierDto = modelMapper.map(panier,PanierDto.class);

			Manifestation manifestation = articleOp.get().getManifestation();
			ManifestationDto manifestationDto = modelMapper.map(manifestation,ManifestationDto.class);

			manifestationDto.setReservations(manifestationDto.getReservations()+articleOp.get().getQuantite());
			panierDto.setTotal(panierDto.getTotal()-(manifestationDto.getPrixBillet()*articleOp.get().getQuantite()));

			this.manifestationRepository.save(this.modelMapper.map(manifestationDto,Manifestation.class));
			this.panierRepository.save(this.modelMapper.map(panierDto,Panier.class));

			this.articleRepository.deleteById(id);
			return true;

		}
		return false;

	}

}
