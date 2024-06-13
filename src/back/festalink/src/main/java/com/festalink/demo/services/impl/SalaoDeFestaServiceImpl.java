package com.festalink.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festalink.demo.entities.Reserva;
import com.festalink.demo.entities.SalaoDeFesta;
import com.festalink.demo.repositories.SalaoDeFestaRepository;
import com.festalink.demo.services.SalaoDeFestaService;

@Service
public class SalaoDeFestaServiceImpl implements SalaoDeFestaService {

	@Autowired
	private SalaoDeFestaRepository salaoDeFestaRepository;

	@Override
	public List<SalaoDeFesta> getSalaoDeFesta() {
		return salaoDeFestaRepository.findAll();
	}

	@Override
	public SalaoDeFesta findById(Integer id) {
		Optional<SalaoDeFesta> salaoDeFesta = salaoDeFestaRepository.findById(id);
		return salaoDeFesta.orElse(null);
	}

	@Override
	public SalaoDeFesta save(SalaoDeFesta salaoDeFesta) {
		return salaoDeFestaRepository.save(salaoDeFesta);
	}

	@Override
	public List<SalaoDeFesta> findByNome(String nome) {
		return salaoDeFestaRepository.findByNomeIgnoreCase(nome);
	}

	@Override
	public void update(SalaoDeFesta salaoDeFesta) {

		try {
			SalaoDeFesta atual = this.findById(salaoDeFesta.getId());
			atual.setNome(salaoDeFesta.getNome());
			atual.setLocalizacao(salaoDeFesta.getLocalizacao());
			atual.setCapacidade(salaoDeFesta.getCapacidade());
			atual.setDescricao(salaoDeFesta.getDescricao());
			atual.setPrecoBase(salaoDeFesta.getPrecoBase());
			atual.setProprietario(salaoDeFesta.getProprietario());

			salaoDeFestaRepository.save(atual);

		} catch (NullPointerException e) {
			throw new NullPointerException();
		}
	}

	@Override
	public void deleteById(Integer id) {
		salaoDeFestaRepository.deleteById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return salaoDeFestaRepository.existsById(id);
	}

	public void deleteByProprietarioId(int proprietarioId) {
		List<SalaoDeFesta> saloes = salaoDeFestaRepository.findByProprietarioId(proprietarioId);
		salaoDeFestaRepository.deleteAll(saloes);
	}

	public boolean registrarReserva(int salaoId, Reserva reserva){

		SalaoDeFesta salaoDeFesta = salaoDeFestaRepository.findById(salaoId);
		salaoDeFesta.registrarReserva(reserva);
		
		
		return false;
	}

	


}
