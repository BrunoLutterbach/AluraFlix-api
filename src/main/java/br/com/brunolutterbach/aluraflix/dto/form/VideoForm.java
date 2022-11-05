package br.com.brunolutterbach.aluraflix.dto.form;

import br.com.brunolutterbach.aluraflix.model.Video;
import br.com.brunolutterbach.aluraflix.repository.CategoriaRepository;
import br.com.brunolutterbach.aluraflix.repository.VideoRepository;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
public class VideoForm {

    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotBlank
    @URL(message = "URL inválida, padrão aceito: https://www.exemplo.com")
    private String url;
    private Long categoriaId;

    public Video converter(Video video, CategoriaRepository categoriaRepository) {
        video.setTitulo(titulo);
        video.setDescricao(descricao);
        video.setUrl(url);
        video.setCategoria(categoriaRepository.getReferenceById(Objects.requireNonNullElse(categoriaId, 1L)));
        return video;
    }

    public Video atualizar(Long id, VideoRepository videoRepository) {
        Video video = videoRepository.getReferenceById(id);
        video.setTitulo(titulo);
        video.setDescricao(descricao);
        video.setUrl(url);
        return video;
    }

}
