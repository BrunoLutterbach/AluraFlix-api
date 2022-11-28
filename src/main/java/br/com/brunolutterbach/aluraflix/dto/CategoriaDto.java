package br.com.brunolutterbach.aluraflix.dto;

import br.com.brunolutterbach.aluraflix.model.Categoria;

public record CategoriaDto(Long id, String titulo, String cor) {

    public CategoriaDto(Categoria categoria) {
        this(categoria.getId(), categoria.getTitulo(), categoria.getCor());
    }

}
