package com.afpa.cda.service;

import java.util.List;

import com.afpa.cda.dto.ResponsableDto;

public interface IResponsableService {
	
	List<ResponsableDto> findAll();

	ResponsableDto add(ResponsableDto responsable);

	boolean updateManager(ResponsableDto responsable, int id);

	boolean deleteManager(int id);

	ResponsableDto findById(int id);

}
