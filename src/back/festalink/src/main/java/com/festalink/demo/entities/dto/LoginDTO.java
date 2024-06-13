package com.festalink.demo.entities.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginDTO {

    @NotNull(message = "O campo nome nâo pode ser nulo")
    @NotEmpty(message = "O campo nome nâo pode estar vazio")
    @NotBlank(message = "O campo nome nâo pode estar em branco")
    @Size(max = 255)
    private String email;

    @NotNull(message = "O campo senha nâo pode ser nulo")
    @NotEmpty(message = "O campo senha nâo pode estar vazio")
    @NotBlank(message = "O campo senha nâo pode estar em branco")
    @Size(max = 255, min = 8)
    private String senha;

    private String tipoUsuario;

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return this.tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
