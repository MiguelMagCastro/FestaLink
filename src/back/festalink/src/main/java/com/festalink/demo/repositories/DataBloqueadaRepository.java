package com.festalink.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.festalink.demo.entities.DataBloqueada;

public interface DataBloqueadaRepository extends JpaRepository<DataBloqueada, Integer> { 
    
    public List<DataBloqueada> getDataBloqueadaBysalaoDeFestasId(int id);

}
