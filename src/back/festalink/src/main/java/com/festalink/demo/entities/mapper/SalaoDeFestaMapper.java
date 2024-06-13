package com.festalink.demo.entities.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festalink.demo.entities.SalaoDeFesta;
import com.festalink.demo.entities.dto.SalaoDeFestaDTO;
import com.festalink.demo.entities.dto.SalaoDeFestaResponseDTO;
import com.festalink.demo.entities.dto.UsuarioResponseDTO;

import jakarta.validation.Valid;

@Service
public class SalaoDeFestaMapper {

	@Autowired
	ProprietarioMapper proprietarioMapper;


	public SalaoDeFesta mapSalaoDeFestaDtoToSalaoDeFesta(@Valid SalaoDeFestaDTO dto) {
		
		SalaoDeFesta salaoDeFesta = new SalaoDeFesta(dto.getNome(),dto.getLocalizacao(),dto.getCapacidade(),dto.getDescricao(),dto.getPrecoBase(),dto.getProprietario());
		
		return salaoDeFesta;
	}

	public SalaoDeFestaResponseDTO mapSalaoDeFestaToSalaoDeFestaResponseDTO(SalaoDeFesta salao){

		UsuarioResponseDTO userDto = proprietarioMapper.mapProprietarioToProprietarioResponseDTO(salao.getProprietario());
		SalaoDeFestaResponseDTO  salaoDeFesta = new SalaoDeFestaResponseDTO(salao.getId(), salao.getNome(), salao.getLocalizacao(),salao.getCapacidade(), salao.getDescricao(),salao.getPrecoBase(), userDto);

		return salaoDeFesta;

	}


}
