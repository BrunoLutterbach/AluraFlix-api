package br.com.brunolutterbach.aluraflix.model;

import br.com.brunolutterbach.aluraflix.dto.VideoDto;
import br.com.brunolutterbach.aluraflix.dto.form.VideoForm;
import br.com.brunolutterbach.aluraflix.dto.form.VideoUpdateForm;
import br.com.brunolutterbach.aluraflix.repository.CategoriaRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;


@Entity(name = "Video")
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
    private Boolean ativo;

    public Video(VideoForm videoForm, CategoriaRepository categoriaRepository) {
        this.ativo = true;
        this.titulo = videoForm.titulo();
        this.descricao = videoForm.descricao();
        this.url = videoForm.url();
        this.categoria = categoriaRepository.getReferenceById(Objects.requireNonNullElse(videoForm.categoriaId(), 1L));
    }

    public Video(Video video) {
        this.id = video.id;
        this.titulo = video.titulo;
        this.descricao = video.descricao;
        this.url = video.url;
        this.categoria = video.categoria;
        this.ativo = video.ativo;
    }

    public VideoDto atualizar(VideoUpdateForm videoUpdate, Categoria categoria) {
        if (videoUpdate.titulo() != null) {
            this.titulo = videoUpdate.titulo();
        }
        if (videoUpdate.descricao() != null) {
            this.descricao = videoUpdate.descricao();
        }
        if (videoUpdate.url() != null) {
            this.url = videoUpdate.url();
        }
        if (videoUpdate.categoriaId() != null) {
            this.categoria = categoria;
        }
        return new VideoDto(this);
    }

    public void inativar() {
        this.ativo = false;
    }
}
