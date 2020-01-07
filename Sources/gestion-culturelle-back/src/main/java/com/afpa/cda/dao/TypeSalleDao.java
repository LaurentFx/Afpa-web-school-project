package com.afpa.cda.dao;

import java.util.List;
import java.util.Optional;

import com.afpa.cda.entity.TypeSalle;

public class TypeSalleDao implements TypeSalleRepository  {

	@Override
	public <S extends TypeSalle> S save(S entity) {
	
		return null;
	}

	@Override
	public <S extends TypeSalle> Iterable<S> saveAll(Iterable<S> entities) {
		
		return null;
	}

	@Override
	public Optional<TypeSalle> findById(Integer id) {
	
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		
		return false;
	}

	@Override
	public Iterable<TypeSalle> findAllById(Iterable<Integer> ids) {
	
		return null;
	}

	@Override
	public long count() {
		
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
	
		
	}

	@Override
	public void delete(TypeSalle entity) {
				
	}

	@Override
	public void deleteAll(Iterable<? extends TypeSalle> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TypeSalle> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
