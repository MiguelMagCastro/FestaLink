package com.festalink.demo.services;

import java.util.List;

import com.festalink.demo.entities.Proprietario;
import com.festalink.demo.entities.SalaoDeFesta;

public interface ProprietarioService {
    public List<Proprietario> findAll();

    public Proprietario findById(int id);

    public List<SalaoDeFesta> findSaloesByProprietarioId(int proprietarioId);

    public Proprietario findByEmail(String email);

    public Proprietario saveWithEncryptedPassword(Proprietario proprietario);

    public void deleteById(int id);

    public boolean existsById(int id);
    
}
