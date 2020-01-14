package com.afpa.cda.service;

import java.util.List;

import com.afpa.cda.dto.TypeSalleDto;
import com.afpa.cda.dto.VipDto;


public interface IVipService {

	List<VipDto> findAll();

	TypeSalleDto add(VipDto vip);

	boolean updateTypeSalle(VipDto vip, int id);

	boolean deleteTypeSalle(int id);

	VipDto findById(int id);
	
}
