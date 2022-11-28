package br.com.brunolutterbach.aluraflix.dto.form;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record VideoForm(

        @NotBlank String titulo, @NotBlank String descricao,
        @NotBlank @URL(message = "URL inválida, padrão aceito: https://www.exemplo.com") String url, Long categoriaId) {
}
