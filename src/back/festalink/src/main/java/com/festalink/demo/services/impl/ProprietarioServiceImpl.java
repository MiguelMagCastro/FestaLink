package com.festalink.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festalink.demo.entities.Proprietario;
import com.festalink.demo.entities.SalaoDeFesta;
import com.festalink.demo.repositories.ProprietarioRepository;
import com.festalink.demo.services.ProprietarioService;
import com.festalink.demo.utils.EncryptionUtils;

@Service
public class ProprietarioServiceImpl implements ProprietarioService {
    @Autowired
    private ProprietarioRepository proprietarioRepository;

    public List<Proprietario> findAll() {
        return proprietarioRepository.findAll();
    }

    public Proprietario findById(int id) {
        return proprietarioRepository.findById(id).orElse(null);
    }

    public List<SalaoDeFesta> findSaloesByProprietarioId(int proprietarioId) {
        Proprietario proprietario = findById(proprietarioId);
        return proprietario != null ? new ArrayList<>(proprietario.getSaloesDeFesta()) : null;
    }

    public Proprietario saveWithEncryptedPassword(Proprietario proprietario) {
        String encryptedPassword = EncryptionUtils.encryptPassword(proprietario.getSenha());
        proprietario.setSenha(encryptedPassword);
        return proprietarioRepository.save(proprietario);
    }

    public void deleteById(int id) {
        proprietarioRepository.deleteById(id);
    }

    @Override
    public Proprietario findByEmail(String email) {
        return proprietarioRepository.findByEmail(email);
    }

    @Override
    public boolean existsById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    }


}
