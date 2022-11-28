package br.com.brunolutterbach.aluraflix.dto.form;

import jakarta.validation.constraints.NotBlank;

public record CategoriaForm(@NotBlank String titulo, @NotBlank String cor) {
}

