package br.com.brunolutterbach.aluraflix.dto;

import br.com.brunolutterbach.aluraflix.model.Usuario;

public record UsuarioDTO(String nome, String email, String senha) {
    public UsuarioDTO(Usuario usuario) {
        this(usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }
}
