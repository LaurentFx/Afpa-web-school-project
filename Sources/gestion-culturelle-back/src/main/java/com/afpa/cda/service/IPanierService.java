package com.afpa.cda.service;

import java.util.List;

import com.afpa.cda.dto.ArticleDto;
import com.afpa.cda.dto.PanierDto;



public interface IPanierService {
	
	List<PanierDto> findAll();

	//PanierDto add(PanierDto panier);

	boolean updatePanier(PanierDto panier, int id);

	PanierDto findById(int id);

	PanierDto findByUser(int id);

	boolean addArticlePanier(ArticleDto articleDto);

	boolean deletePanier(int id);

	boolean deleteArticles(int id);


}
