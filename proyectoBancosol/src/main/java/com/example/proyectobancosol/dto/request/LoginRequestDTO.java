package com.example.proyectobancosol.dto.request;

/**
 * recibe los datos del login (se me paso usar @data)
 *
 * Autores:
 * - Jesus Moreno Carmona: 100%
 *
 */

public class LoginRequestDTO {    private String email;
    private String password;

    public LoginRequestDTO() {
    }

    public LoginRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
