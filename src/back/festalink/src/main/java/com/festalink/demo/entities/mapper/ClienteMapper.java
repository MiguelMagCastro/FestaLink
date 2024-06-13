package com.festalink.demo.entities.mapper;

import org.springframework.stereotype.Service;

import com.festalink.demo.entities.Cliente;
import com.festalink.demo.entities.dto.ClienteDTO;

import jakarta.validation.Valid;

@Service
public class ClienteMapper {
    
        public Cliente mapClienteDtoToCliente(@Valid ClienteDTO dto) {
		
            Cliente cliente = new Cliente(dto.getNome(),dto.getEmail(),dto.getSenha(),dto.getTelefone());
		
		return cliente;
	}


}
