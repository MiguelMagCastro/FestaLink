package com.festalink.demo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festalink.demo.entities.DataBloqueada;
import com.festalink.demo.repositories.DataBloqueadaRepository;
import com.festalink.demo.services.DataBloqueadaService;

@Service
public class DataBloqueadaServiceImpl implements DataBloqueadaService{

 @Autowired
    private DataBloqueadaRepository dataBloqueadaRepository;

    @Override
    public void saveDataBloqueada(DataBloqueada dataBloqueada) {
        dataBloqueadaRepository.save(dataBloqueada);
    }

    @Override
    public List<DataBloqueada> getDataBloqueadaByidSalaoDeFesta(int id) {
       return dataBloqueadaRepository.getDataBloqueadaBysalaoDeFestasId(id);
    }

    @Override
    public void liberarData(DataBloqueada dataBloqueada) {
        dataBloqueadaRepository.delete(dataBloqueada);
    }

    
    
}
