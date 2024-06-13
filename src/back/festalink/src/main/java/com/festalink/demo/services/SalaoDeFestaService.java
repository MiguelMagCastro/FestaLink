package com.festalink.demo.services;

import java.util.List;

import com.festalink.demo.entities.SalaoDeFesta;

public interface SalaoDeFestaService {

	List<SalaoDeFesta> getSalaoDeFesta();

	public SalaoDeFesta findById(Integer id);

	public List<SalaoDeFesta> findByNome(String nome);

	public SalaoDeFesta save(SalaoDeFesta salaoDeFesta);
	
	public void update(SalaoDeFesta salaoDeFesta);

	public void deleteById(Integer id);

	public boolean existsById(Integer id);
}
