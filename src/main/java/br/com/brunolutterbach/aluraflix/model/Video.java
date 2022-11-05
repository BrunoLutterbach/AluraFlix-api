package br.com.brunolutterbach.aluraflix.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "videos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String url;
    @ManyToOne
    private Categoria categoria;

}
