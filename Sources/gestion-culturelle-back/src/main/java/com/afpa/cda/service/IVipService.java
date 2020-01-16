package com.afpa.cda.service;

import java.util.List;


import com.afpa.cda.dto.VipDto;


public interface IVipService {

	List<VipDto> findAll();

	VipDto add(VipDto vip);

	boolean updateVip(VipDto vip, int id);

	boolean deleteVop(int id);

	VipDto findById(int id);
	
}
