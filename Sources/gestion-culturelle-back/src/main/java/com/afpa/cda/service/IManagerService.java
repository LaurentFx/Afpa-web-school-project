package com.afpa.cda.service;

import java.util.List;
import com.afpa.cda.dto.ManagerDto;

public interface IManagerService {
	
	List<ManagerDto> findAll();

	ManagerDto add(ManagerDto manager);

	boolean updateClient(ManagerDto manager, int id);

	boolean deleteManager(int id);

	ManagerDto findById(int id);

}
