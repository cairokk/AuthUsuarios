package br.unifor.usuario.DTO;

public class RegistrarRequestDTO {
    private String email;
    private String senha;
    private String role;

    public RegistrarRequestDTO(String email, String senha, String role) {
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    public RegistrarRequestDTO() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

