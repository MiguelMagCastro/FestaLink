package com.festalink.demo.services;

import java.util.List;

import com.festalink.demo.entities.Cliente;
import com.festalink.demo.entities.Reserva;

public interface ClienteService {

    public List<Cliente> findAll();

    public Cliente findById(int id);

    public Cliente findByEmail(String email);

    public List<Reserva> findReservasByClienteId(int clienteId);

    public Cliente saveWithEncryptedPassword(Cliente cliente);

    public void deleteById(int id);
}
