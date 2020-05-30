package com.afpa.cda.service;

import java.util.List;

import com.afpa.cda.dto.ArticleDto;

public interface IArticleService {

	List<ArticleDto> findByPanierId(int id);

	boolean delete(int id);

}
