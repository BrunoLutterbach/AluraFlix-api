package br.com.brunolutterbach.aluraflix.dto.form;

import br.com.brunolutterbach.aluraflix.model.Categoria;
import br.com.brunolutterbach.aluraflix.repository.CategoriaRepository;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoriaForm {

    @NotBlank
    private String titulo;
    @NotBlank
    private String cor;

    public Categoria converter(Categoria categoria) {
        categoria.setTitulo(titulo);
        categoria.setCor(cor);
        return categoria;
    }

    public Categoria atualizar(Long id, CategoriaRepository categoriaRepository) {
        Categoria categoria = categoriaRepository.getReferenceById(id);
        categoria.setTitulo(titulo);
        categoria.setCor(cor);
        return categoria;
    }

}
