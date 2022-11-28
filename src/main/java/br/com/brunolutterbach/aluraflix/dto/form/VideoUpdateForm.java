package br.com.brunolutterbach.aluraflix.dto.form;

import org.hibernate.validator.constraints.URL;

public record VideoUpdateForm(

        String titulo, String descricao,
        @URL(message = "URL inválida, padrão aceito: https://www.exemplo.com") String url, Long categoriaId) {
}
