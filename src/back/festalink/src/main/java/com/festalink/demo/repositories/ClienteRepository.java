package com.festalink.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.festalink.demo.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Cliente findByEmail(String email);
    
    Cliente findById(int id);

}
