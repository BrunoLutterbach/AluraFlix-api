package br.com.brunolutterbach.aluraflix.model;

import br.com.brunolutterbach.aluraflix.dto.CategoriaDto;
import br.com.brunolutterbach.aluraflix.dto.form.CategoriaForm;
import br.com.brunolutterbach.aluraflix.dto.form.CategoriaUpdateForm;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Categoria")
@Table(name = "categorias")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String cor;
    @OneToMany(mappedBy = "categoria")
    private List<Video> video = new ArrayList<>();

    public Categoria(CategoriaForm categoriaForm) {
        this.titulo = categoriaForm.titulo();
        this.cor = categoriaForm.cor();
    }

    public CategoriaDto atualizar(CategoriaUpdateForm categoriaUpdateForm) {
        if (categoriaUpdateForm.titulo() != null) {
            this.titulo = categoriaUpdateForm.titulo();
        }
        if (categoriaUpdateForm.cor() != null) {
            this.cor = categoriaUpdateForm.cor();
        }
        return new CategoriaDto(this);
    }
}
