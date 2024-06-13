package com.festalink.demo.services;

import java.util.List;

import com.festalink.demo.entities.Avaliacao;

public interface AvaliacaoService {
    
    public List<Avaliacao> findBySalaoDeFesta(int id);

    public Avaliacao saveAvaliacao(Avaliacao avaliacao);

}
