package com.festalink.demo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festalink.demo.entities.Avaliacao;
import com.festalink.demo.repositories.AvaliacaoRepository;
import com.festalink.demo.repositories.SalaoDeFestaRepository;
import com.festalink.demo.services.AvaliacaoService;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService{

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private SalaoDeFestaRepository salaoDeFestaRepository;

    @Override
    public List<Avaliacao> findBySalaoDeFesta(int id) {
        return avaliacaoRepository.findBySalaoDeFesta(salaoDeFestaRepository.findById(id));
    }

    @Override
    public Avaliacao saveAvaliacao(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }
    
}
