package com.festalink.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.festalink.demo.entities.Proprietario;

public interface ProprietarioRepository extends JpaRepository<Proprietario, Integer> {

    Proprietario findByEmail(String email);


    
}

