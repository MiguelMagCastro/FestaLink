package com.festalink.demo.services;

import java.util.List;

import com.festalink.demo.entities.DataBloqueada;

public interface DataBloqueadaService {
    
    public void saveDataBloqueada(DataBloqueada dataBloqueada);

    public List<DataBloqueada> getDataBloqueadaByidSalaoDeFesta(int id);

    public void liberarData(DataBloqueada a);
}
