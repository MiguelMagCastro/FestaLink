package com.festalink.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festalink.demo.entities.Cliente;
import com.festalink.demo.entities.Reserva;
import com.festalink.demo.repositories.ClienteRepository;
import com.festalink.demo.services.ClienteService;
import com.festalink.demo.utils.EncryptionUtils;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(int id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente findByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    public List<Reserva> findReservasByClienteId(int clienteId) {
        Cliente cliente = findById(clienteId);
        return cliente != null ? new ArrayList<>(cliente.getReservas()) : null;
    }

    public Cliente saveWithEncryptedPassword(Cliente cliente) {
        String encryptedPassword = EncryptionUtils.encryptPassword(cliente.getSenha());
        cliente.setSenha(encryptedPassword);
        return clienteRepository.save(cliente);
    }

    public void deleteById(int id) {
        clienteRepository.deleteById(id);
    }

}
