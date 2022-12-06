package br.com.brunolutterbach.aluraflix.dto.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UsuarioForm(@NotNull String nome, @Email @NotNull String email, @NotNull String senha) {
}
