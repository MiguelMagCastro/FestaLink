package com.festalink.demo.services;

import java.util.List;

import com.festalink.demo.entities.Usuario;

public interface UsuarioService {

	 public List<Usuario> getUsuario();

	 public Usuario save(Usuario usuario);

	 public Usuario findById(Integer id);

	 public Usuario findByEmail(String email);

	 public void update(Usuario usuario);

	 public void deleteById(Integer id);

	 public boolean existsById(Integer id);
}
