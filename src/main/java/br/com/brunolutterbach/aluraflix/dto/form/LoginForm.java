package br.com.brunolutterbach.aluraflix.dto.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record LoginForm(@NotNull @Email String email, @NotNull String senha) {
}
