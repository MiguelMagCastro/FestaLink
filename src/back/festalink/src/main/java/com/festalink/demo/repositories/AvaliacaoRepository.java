package com.festalink.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.festalink.demo.entities.Avaliacao;
import com.festalink.demo.entities.SalaoDeFesta;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {

    public List<Avaliacao> findBySalaoDeFesta(SalaoDeFesta salaoDeFesta);

}
