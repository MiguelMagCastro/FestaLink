package com.festalink.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.festalink.demo.entities.SalaoDeFesta;

public interface SalaoDeFestaRepository extends JpaRepository<SalaoDeFesta, Integer> {

	List<SalaoDeFesta> findSalaoDeFestaoByNome(String nome);

	SalaoDeFesta findById(int id);

	List<SalaoDeFesta> findByNomeIgnoreCase(String nome);

	List<SalaoDeFesta> findByProprietarioId(int proprietarioId);


}
