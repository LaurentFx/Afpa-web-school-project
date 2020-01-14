package com.afpa.cda.service;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.PersonneRepository;
import com.afpa.cda.dto.TypeSalleDto;
import com.afpa.cda.dto.VipDto;
@Service
public class VipServiceImpl implements IVipService {
    @Autowired
    private PersonneRepository vipRepository;
    @Autowired
    private ModelMapper modelMapper;
	@Override
	public List<VipDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public TypeSalleDto add(VipDto vip) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean updateTypeSalle(VipDto vip, int id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteTypeSalle(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public VipDto findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

    
}