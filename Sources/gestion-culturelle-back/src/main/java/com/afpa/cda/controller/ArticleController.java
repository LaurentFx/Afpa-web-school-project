package com.afpa.cda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.afpa.cda.dto.ArticleDto;
import com.afpa.cda.service.IArticleService;

@RestController
public class ArticleController {

	@Autowired
	private IArticleService articleService;
	
	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@GetMapping(path = "/article/panier/{id}")
	public List<ArticleDto> getArticles(@PathVariable int id){
		return this.articleService.findByPanierId(id);
	}

	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@DeleteMapping(path = "/article/{id}")
	public boolean delete(@PathVariable int id) {
		return this.articleService.delete(id);
	}

}